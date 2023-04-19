package com.leinaro.meli.data.repositories

import com.leinaro.meli.data.local.UserDataStore
import com.leinaro.meli.data.remote.MeliServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.http.Query
import javax.inject.Inject

interface CategoryRepository {
    suspend fun setSite(siteId: String)
    fun getSites(): Flow<List<SiteResponse>>
    fun getSelectedSite(): Flow<SiteResponse?>
    fun getCategories(): Flow<List<CategoryDTO>>
    fun searchItems(categoryId: String?, query: String?): Flow<List<ProductResponse>>
}

class Repository @Inject constructor(
    private val userDataStore: UserDataStore,
    private val meliServices: MeliServices,
) : CategoryRepository {
    override suspend fun setSite(siteId: String) {
        userDataStore.setSelectedSite(siteId)
    }

    override fun getSites(): Flow<List<SiteResponse>> {
        return flow {
            emit(
                meliServices.getSites()
            )
        }
    }

    override fun getSelectedSite(): Flow<SiteResponse?> {
        return combine(
            userDataStore.getSelectedSiteId(),
            getSites(),
        ) { selectedSiteId, sitesDTO ->
            sitesDTO.firstOrNull { siteDTO ->
                siteDTO.id == selectedSiteId
            }
        }
    }

    override fun getCategories(): Flow<List<CategoryDTO>> {
        return userDataStore.getSelectedSiteId()
            .map { selectedId ->
                selectedId?.let {
                    meliServices.getCategories(
                        siteId = selectedId,
                        limit = 6
                    ).take(5)
                } ?: run {
                    listOf()
                }
            }
     }

    override fun searchItems(
        categoryId: String?,
        query: String?,
    ): Flow<List<ProductResponse>> {
        return userDataStore.getSelectedSiteId()
            .map { selectedId ->
                selectedId?.let {
                    meliServices.searchItems(
                        siteId = it,
                        categoryId = categoryId,
                        query = query
                    ).results.take(6)
                } ?: run {
                    listOf()
                }
            }
    }
}