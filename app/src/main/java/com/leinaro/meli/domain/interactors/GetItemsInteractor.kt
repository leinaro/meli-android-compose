package com.leinaro.meli.domain.interactors

import com.leinaro.meli.data.repositories.CategoryRepository
import com.leinaro.meli.domain.entities.Product
import com.leinaro.meli.domain.entities.Site
import com.leinaro.meli.domain.entities.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetItemsInteractor {
    fun execute(categoryId: String): Flow<List<Product>>
}

class GetItemsDomainInteractor @Inject constructor(
    private val repository: CategoryRepository,
) : GetItemsInteractor {
    override fun execute(categoryId: String): Flow<List<Product>> {
        return repository.searchItems(categoryId)
            .map { productsDTO ->
                productsDTO.toUiModel()
            }
    }
}

interface GetItemDetailsInteractor {
    fun execute(categoryId: String): Flow<List<Product>>
}

class GetItemDetailsDomainInteractor @Inject constructor(
    private val repository: CategoryRepository,
) : GetItemDetailsInteractor {
    override fun execute(categoryId: String): Flow<List<Product>> {
        return repository.searchItems(categoryId)
            .map { productsDTO ->
                productsDTO.toUiModel()
            }
    }
}
