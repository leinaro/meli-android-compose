package com.leinaro.meli.domain.interactors

import com.leinaro.meli.data.repositories.MeliRepository
import com.leinaro.meli.domain.entities.Category
import com.leinaro.meli.domain.entities.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetCategoriesInteractor {
    fun execute(): Flow<List<Category>>
}

class GetCategoriesDomainInteractor @Inject constructor(
    private val repository: MeliRepository,
) : GetCategoriesInteractor {
    override fun execute(): Flow<List<Category>> {
        return repository.getCategories()
            .map { categories ->
                categories.toUiModel()
            }
    }
}



