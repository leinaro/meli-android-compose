package com.leinaro.meli.domain.di

import android.content.Context
import com.leinaro.meli.data.local.UserDataStore
import com.leinaro.meli.data.repositories.CategoryRepository
import com.leinaro.meli.data.repositories.Repository
import com.leinaro.meli.domain.interactors.GetCategoriesDomainInteractor
import com.leinaro.meli.domain.interactors.GetCategoriesInteractor
import com.leinaro.meli.domain.interactors.GetSitesDomainInteractor
import com.leinaro.meli.domain.interactors.GetSitesInteractor
import com.leinaro.meli.domain.interactors.SelectSiteDomainInteractor
import com.leinaro.meli.domain.interactors.SelectSiteInteractor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsDomainModule {

    @Singleton
    @Binds
    abstract fun bindsGetCategoriesInteractor(
        getCategoriesDomainInteractor: GetCategoriesDomainInteractor
    ): GetCategoriesInteractor

    @Singleton
    @Binds
    abstract fun bindGetSitesInteractor(
        getSitesDomainInteractor: GetSitesDomainInteractor
    ): GetSitesInteractor

    @Singleton
    @Binds
    abstract fun bindsSelectSiteInteractor(
        selectSiteInteractor: SelectSiteDomainInteractor
    ): SelectSiteInteractor
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsDataModule {

    @Singleton
    @Binds
    abstract fun bindGetCategoriesInteractor(
        repository: Repository
    ): CategoryRepository
}

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): UserDataStore {
        return UserDataStore(appContext)
    }
}

