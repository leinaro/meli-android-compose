package com.leinaro.meli.data.repositories

import com.google.gson.annotations.SerializedName

class ProductResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("original_price")
    val originalPrice :Double? = null,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("currency_id")
    val currencyId:String,
    @SerializedName("sold_quantity")
    val soldQuantity: Int,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
)