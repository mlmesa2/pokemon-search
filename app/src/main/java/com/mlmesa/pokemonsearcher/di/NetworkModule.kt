package com.mlmesa.pokemonsearcher.di

import com.mlmesa.pokemonsearcher.BuildConfig
import com.mlmesa.pokemonsearcher.data.remote.PokemonRemoteDataSource
import com.mlmesa.pokemonsearcher.data.remote.retrofit.RetrofitPokemonNetwork
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json{
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun pokemonCallFactory(
    ): Call.Factory = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                },
        ).build()

}

@Module
@InstallIn(SingletonComponent::class)
interface RetrofitModule {

    @Binds
    fun bindPokemonNetworkDataSource(retrofitPokemon: RetrofitPokemonNetwork): PokemonRemoteDataSource

}