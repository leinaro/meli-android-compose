package com.leinaro.meli.domain.entities

import com.leinaro.meli.data.repositories.SiteResponse

data class Site (
    val id: String,
    val name: String,
)

fun SiteResponse.toUiModel(): Site = Site(
    id = id,
    name = name,
)

fun List<SiteResponse>.toUiModel() = map { site ->
    site.toUiModel()
}