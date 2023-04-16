package com.leinaro.meli.data.repositories

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeliServices {
    //https://api.mercadolibre.com/sites
    @GET("sites")
    suspend fun getSites(): List<SiteDTO>

    //https://api.mercadolibre.com/sites/MLA/categories
    @GET("sites/{siteId}/categories")
    suspend fun getCategories(
        @Path("siteId") siteId: String,
    ): List<CategoryDTO>

    ///sites/$SITE_ID/search?category=$CATEGORY_ID
    @GET("sites/{siteId}/search")
    suspend fun searchItems(
        @Path("siteId") siteId: String,
        @Query("category") categoryId: String,
        @Query("limit") limit: Int? = null,
    ): List<SearchItemResponse>

    ///items?ids=$ITEM_ID1,$ITEM_ID2
/*    @GET("sites/{siteId}/search")
    suspend fun searchItems(
        @Path("siteId") siteId: String,
        @Query("category") categoryId: String,
        @Query("limit") limit: Int? = null,
    ): List<SearchItemResponse>*/
}

data class SiteDTO(
    @SerializedName("default_currency_id")
    val defaultCurrencyDd: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
)

class CategoryDTO(
    val id: String,
    val name: String,
)


class ProductDTO(
    val id: String,
    val title: String,
    val price: String,
    val thumbnail: String,
    var category_id: String,
  //  var pictures: List<PicturesRemote>?,
)
data class SearchItemResponse(
    //site_id
    //country_default_time_zone
    //paging
    val results: List<ProductDTO>,
)