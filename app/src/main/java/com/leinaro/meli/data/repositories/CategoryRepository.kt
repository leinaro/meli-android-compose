package com.leinaro.meli.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.leinaro.meli.data.local.UserDataStore
import com.leinaro.meli.data.local.dataStore
import com.leinaro.meli.domain.entities.Site
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CategoryRepository {
    suspend fun setSite(siteId: String)
    fun getSites(): Flow<List<SiteDTO>>
    fun getSelectedSited() : Flow<String?>
    fun getCategories(): Flow<List<CategoryDTO>>
    fun getCategory(categoryId: String): Flow<CategoryDTO>
}

class Repository @Inject constructor(
   // private val userDataStore: UserDataStore,
): CategoryRepository {
    override suspend fun setSite(siteId: String) {
       //  userDataStore.setSelectedSite(siteId)
    }

    override fun getSites(): Flow<List<SiteDTO>> {
        return flow {
            emit(
                listOf(
                    SiteDTO("ARG","ARG","Argentina"),
                    SiteDTO("MXN", "MXN","Mexico"),
                    SiteDTO("COP", "COP","Colombia"),
                )
            )
        }
    }

    override fun getSelectedSited() : Flow<String?>{
        return flow{}//userDataStore.getSelectedSite()
    }

    override fun getCategories(): Flow<List<CategoryDTO>> {
        return flow{}
        /*return flow {
            /*emit(
                Result.Loading(
                    ListMapperImpl(categoryWithProductsLocalMapper).map(
                        categoryDao.getCategoryWithProducts()
                    )
                )
            )*/

            try {
                val categories = mercadolibreServices.getCategories()
                insertRemoteCategoriesToDataBase(categories)
                    .onEach { news -> saveInCache(news) }
                    // If an error happens, emit the last cached values
                    .catch { exception -> emit(lastCachedNews()) }

                emit(
                    Result.Loading(
                        ListMapperImpl(categoryWithProductsLocalMapper).map(
                            categoryDao.getCategoryWithProducts()
                        )
                    )
                )

                categories.map {
                    val productsByCategory =
                        mercadolibreServices.getProductsByCategory(it.id).results
                            .map { product ->
                                product.category_id = it.id
                                product
                            }

                    insertRemoteProductsToDatabase(productsByCategory)
                }
                emit(
                    Result.Success(
                        ListMapperImpl(categoryWithProductsLocalMapper).map(
                            categoryDao.getCategoryWithProducts()
                        )
                    )
                )
            } catch (exception: IOException) {
                emit(
                    Failure(
                        exception,
                        ListMapperImpl(categoryWithProductsLocalMapper).map(
                            categoryDao.getCategoryWithProducts()
                        )
                    )
                )
            }
        }*/
    }

    override fun getCategory(categoryId: String): Flow<CategoryDTO> {
        return flow {  }
    }
}