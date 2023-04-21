package com.leinaro.meli.data.repositories

import com.google.gson.annotations.SerializedName

data class SiteResponse(
    @SerializedName("default_currency_id")
    val defaultCurrencyId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
)