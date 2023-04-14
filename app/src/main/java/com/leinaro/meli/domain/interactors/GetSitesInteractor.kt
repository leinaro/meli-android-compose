package com.leinaro.meli.domain.interactors

import com.leinaro.meli.data.repositories.CategoryRepository
import com.leinaro.meli.domain.entities.Site
import com.leinaro.meli.domain.entities.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetSitesInteractor{
    fun execute(): Flow<List<Site>>
}

class GetSitesDomainInteractor @Inject constructor(
    val repository: CategoryRepository,
) : GetSitesInteractor {
    override fun execute(): Flow<List<Site>> {
        return repository.getSites()
            .map { sites ->
                sites.toUiModel()
            }
    }
}