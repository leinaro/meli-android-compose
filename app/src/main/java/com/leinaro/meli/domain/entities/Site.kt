package com.leinaro.meli.domain.entities

import com.leinaro.meli.data.repositories.SiteDTO

data class Site (
    val id: String,
    val name: String,
)

fun SiteDTO.toUiModel(): Site = Site(
    id = id,
    name = name,
)

fun List<SiteDTO>.toUiModel() = map { site ->
    site.toUiModel()
}