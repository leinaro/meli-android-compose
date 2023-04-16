package com.leinaro.meli.data.repositories

import com.leinaro.meli.data.local.UserDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CategoryRepository {
    suspend fun setSite(siteId: String)
    fun getSites(): Flow<List<SiteDTO>>
    fun getSelectedSite(): Flow<SiteDTO?>
    fun getCategories(): Flow<List<CategoryDTO>>
    fun searchItems(categoryId: String):  Flow<List<ProductDTO>>
}

class Repository @Inject constructor(
    private val userDataStore: UserDataStore,
) : CategoryRepository {
    override suspend fun setSite(siteId: String) {
        userDataStore.setSelectedSite(siteId)
    }

    override fun getSites(): Flow<List<SiteDTO>> {
        return flow {
            emit(
                listOf(
                    SiteDTO("ARG", "ARG", "Argentina"),
                    SiteDTO("MXN", "MXN", "Mexico"),
                    SiteDTO("COP", "COP", "Colombia"),
                )
            )
        }
    }

    override fun getSelectedSite(): Flow<SiteDTO?> {
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
        return flow {
            val categories = listOf(
                CategoryDTO(
                    "Categoria 1",
                    "Categoria 1",

                    ),
                CategoryDTO(
                    "Categoria 2",
                    "Categoria 2",
                ),
            )

            emit(
                categories
            )
        }
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

    override fun searchItems(categoryId: String): Flow<List<ProductDTO>> {
        return flow {
            emit(
               // SearchItemResponse(results =
            listOf(
                ProductDTO(
                    "producto 1",
                    "15000",
                    "producto 3",
                    //"https://picsum.photos/200/300",
                    "https://upload.wikimedia.org/wikipedia/commons/1/16/HDRI_Sample_Scene_Balls_%28JPEG-HDR%29.jpg",
                    "producto 3",
                ),
                ProductDTO(
                    "producto 1",
                    "15000",
                    "producto 3",
                    "https://picsum.photos/200/300",
                    "producto 3",
                ),
                ProductDTO(
                    "producto 1",
                    "15000",
                    "producto 3",
                    "https://picsum.photos/200/300",
                    "producto 3",
                ),
                ProductDTO(
                    "producto 1",
                    "15000",
                    "producto 3",
                    "https://picsum.photos/200/300",
                    "producto 3",
                ),
            ))//)
        }
    }
}