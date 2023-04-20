package com.leinaro.meli.data.remote

import com.leinaro.meli.data.repositories.CategoryDTO
import com.leinaro.meli.data.repositories.ProductResponse
import com.leinaro.meli.data.repositories.SiteResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeliServices {
    @GET("sites")
    suspend fun getSites(): List<SiteResponse>

    @GET("sites/{siteId}/categories")
    suspend fun getCategories(
        @Path("siteId") siteId: String,
        @Query("limit") limit: Int? = null,
    ): List<CategoryDTO>

    @GET("sites/{siteId}/search")
    suspend fun searchItems(
        @Path("siteId") siteId: String,
        @Query("category") categoryId: String? = null,
        @Query("q") query: String? = null,
        @Query("limit") limit: Int? = null,
    ): SearchItemResponse

    @GET("items/{itemId}/description")
    suspend fun getItemDetails(
        @Path("itemId") itemId: String,
    ): ProductResponse

}

data class SearchItemResponse(
    val results: List<ProductResponse>,
)