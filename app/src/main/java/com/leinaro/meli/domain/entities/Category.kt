package com.leinaro.meli.domain.entities

import com.leinaro.meli.data.repositories.CategoryDTO

data class Category (
    val name: String,
)

fun CategoryDTO.toUiModel() : Category = Category(name)

fun List<CategoryDTO>.toUiModel() = map { category ->
        category.toUiModel()
    }
