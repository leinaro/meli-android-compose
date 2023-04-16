package com.leinaro.meli.domain.entities

import com.leinaro.meli.data.repositories.CategoryDTO

data class Category(
    val id: String,
    val name: String,
)

fun CategoryDTO.toUiModel(): Category = Category(
    id = id,
    name = name,
)

fun List<CategoryDTO>.toUiModel() = map { category ->
    category.toUiModel()
}
