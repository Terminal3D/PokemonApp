package com.example.pokemonapp.di

import com.example.pokemonapp.data.remote.ApiRepository
import com.example.pokemonapp.data.remote.ApiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiRepositoryModule {
    @Binds
    abstract fun bindApiRepository(
        impl: ApiRepositoryImpl
    ): ApiRepository

}