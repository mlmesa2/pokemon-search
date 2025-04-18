package com.mlmesa.pokemonsearcher.di

import android.content.Context
import com.mlmesa.pokemonsearcher.data.connectivity.ConnectionMonitor
import com.mlmesa.pokemonsearcher.data.connectivity.ConnectivityManagerMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConnectivityModule {

    @Provides
    @Singleton
    fun provideConnectionMonitor(
        @ApplicationContext context: Context
    ): ConnectionMonitor = ConnectivityManagerMonitor(context)

}