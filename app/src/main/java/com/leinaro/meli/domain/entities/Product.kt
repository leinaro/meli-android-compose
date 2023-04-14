package com.leinaro.meli.domain.entities

import com.leinaro.meli.data.repositories.SiteDTO

data class Product(
    val name: String,
    val price: String,
    val imageUrl: String,
)