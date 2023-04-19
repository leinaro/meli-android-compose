package com.leinaro.meli.data.di

import android.content.Context
import com.leinaro.meli.data.remote.MeliClient
import com.leinaro.meli.data.remote.MeliServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideMercadoLibreServices(
        @ApplicationContext context: Context,
    ): MeliServices = MeliClient(context).services
}