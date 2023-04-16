package com.leinaro.meli.domain.entities

import com.leinaro.meli.data.repositories.ProductDTO
import com.leinaro.meli.data.repositories.SiteDTO

data class Product(
    val id: String,
    val name: String,
    val price: String,
    val imageUrl: String,
)


fun List<ProductDTO>.toUiModel() = map { productDto ->
    productDto.toUiModel()
}
fun ProductDTO.toUiModel() = Product(
    id = id,
    name = title,
    price = price,
    imageUrl = thumbnail,
)