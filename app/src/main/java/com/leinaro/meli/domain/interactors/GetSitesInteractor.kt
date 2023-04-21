package com.leinaro.meli.domain.interactors

import com.leinaro.meli.data.repositories.MeliRepository
import com.leinaro.meli.domain.entities.Site
import com.leinaro.meli.domain.entities.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetSitesInteractor{
    fun execute(): Flow<List<Site>>
}

class GetSitesDomainInteractor @Inject constructor(
    private val repository: MeliRepository,
) : GetSitesInteractor {
    override fun execute(): Flow<List<Site>> {
        return repository.getSites()
            .map { sites ->
                sites.toUiModel()
            }
    }
}