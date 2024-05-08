package com.example.pokemonapp.di

import com.example.pokemonapp.api.ApiRepository
import com.example.pokemonapp.api.ApiRepositoryImpl
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