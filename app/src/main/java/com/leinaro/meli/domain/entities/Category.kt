package com.leinaro.meli.domain.entities

import com.leinaro.meli.data.repositories.CategoryDTO

data class Category (
    val name: String,
    val products: List<Product>
)

fun CategoryDTO.toUiModel() : Category = Category(name, emptyList())

fun List<CategoryDTO>.toUiModel() = map { category ->
        category.toUiModel()
    }
