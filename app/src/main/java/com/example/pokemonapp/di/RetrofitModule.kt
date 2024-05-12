package com.example.pokemonapp.di

import com.example.pokemonapp.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideBaseUrl() : String = "https://pokeapi.co/"

    @Provides
    @Singleton
    fun provideRetrofitInstance(baseUrl : String) : Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    @Singleton
    fun provideUsersApi(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)
}