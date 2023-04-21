package com.leinaro.meli.domain.entities

import com.leinaro.meli.data.repositories.CategoryResponse

data class Category(
    val id: String,
    val name: String,
)

fun CategoryResponse.toUiModel(): Category = Category(
    id = id,
    name = name,
)

fun List<CategoryResponse>.toUiModel() = map { category ->
    category.toUiModel()
}
