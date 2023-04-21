package com.leinaro.meli.domain.interactors

import com.leinaro.meli.data.repositories.MeliRepository
import com.leinaro.meli.domain.entities.Site
import javax.inject.Inject

interface SelectSiteInteractor {
    suspend fun execute(site: Site)
}

class SelectSiteDomainInteractor @Inject constructor(
    private val repository: MeliRepository,
) : SelectSiteInteractor {
    override suspend fun execute(site: Site) {
        repository.setSite(site.id)
    }
}