package com.example.pokemonapp.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pokemonapp.data.local.PokemonDatabase
import com.example.pokemonapp.data.local.PokemonListEntity
import com.example.pokemonapp.data.remote.ApiRepository
import com.example.pokemonapp.data.remote.PokemonRemoteMediator
import com.example.pokemonapp.data.models.Pokemon
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object PagerModule {

    @Provides
    @Singleton
    fun providePager(
        pokemonDb: PokemonDatabase,
        apiRepository: ApiRepository
    ): Pager<Int, PokemonListEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PokemonRemoteMediator(
                apiRepository = apiRepository,
                pokemonDb = pokemonDb
            ),
            pagingSourceFactory = {
                pokemonDb.dao.pagingSource()
            }
        )
    }
}