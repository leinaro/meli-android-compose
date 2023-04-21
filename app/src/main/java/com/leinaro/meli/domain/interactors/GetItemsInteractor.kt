package com.leinaro.meli.domain.interactors

import com.leinaro.meli.data.repositories.MeliRepository
import com.leinaro.meli.domain.entities.Product
import com.leinaro.meli.domain.entities.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetItemsInteractor {
    fun execute(
        categoryId: String? = null,
        query: String? = null,
    ): Flow<List<Product>>
}

class GetItemsDomainInteractor @Inject constructor(
    private val repository: MeliRepository,
) : GetItemsInteractor {
    override fun execute(categoryId: String?, query: String?): Flow<List<Product>> {
        return repository.searchItems(categoryId, query)
            .map { productsDTO ->
                productsDTO.toUiModel()
            }
    }
}