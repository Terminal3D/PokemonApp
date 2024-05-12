package com.example.pokemonapp.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil.network.HttpException
import com.example.pokemonapp.data.local.PokemonDatabase
import com.example.pokemonapp.data.local.PokemonListEntity
import com.example.pokemonapp.data.mappers.toEntity
import com.example.pokemonapp.data.models.Pokemon
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonDb : PokemonDatabase,
    private val apiRepository : ApiRepository
) : RemoteMediator<Int, PokemonListEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonListEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        0
                    } else {
                        (lastItem.id / state.config.pageSize)
                    }
                }
            }
            val pokemons = apiRepository.getPokemonListItems(
                page = loadKey,
                pageSize = state.config.pageSize
            )
            pokemonDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    pokemonDb.dao.clearAll()
                }
                val beerEntities = pokemons.map { it.toEntity() }
                pokemonDb.dao.upsertAll(beerEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = pokemons.isEmpty()
            )
        } catch(e : IOException) {
            MediatorResult.Error(e)
        } catch (e : HttpException) {
            MediatorResult.Error(e)
        }
    }
}