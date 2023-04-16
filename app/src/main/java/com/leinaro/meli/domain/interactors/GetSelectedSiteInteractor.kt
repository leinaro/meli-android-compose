package com.leinaro.meli.domain.interactors

import com.leinaro.meli.data.repositories.CategoryRepository
import com.leinaro.meli.domain.entities.Site
import com.leinaro.meli.domain.entities.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetSelectedSiteInteractor {
    fun execute(): Flow<Site?>
}

class GetSelectedSiteDomainInteractor @Inject constructor(
    private val repository: CategoryRepository,
) : GetSelectedSiteInteractor {
    override fun execute(): Flow<Site?> {
        return repository.getSelectedSite()
            .map { siteDTO ->
                siteDTO?.toUiModel()
            }
    }
}
