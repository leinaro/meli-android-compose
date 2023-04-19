package com.leinaro.meli.ui.main

import androidx.lifecycle.viewModelScope
import com.leinaro.meli.domain.entities.Category
import com.leinaro.meli.domain.entities.Product
import com.leinaro.meli.domain.interactors.GetCategoriesInteractor
import com.leinaro.meli.domain.interactors.GetItemsInteractor
import com.leinaro.meli.domain.interactors.GetSelectedSiteInteractor
import com.leinaro.meli.ui.common.Action
import com.leinaro.meli.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCategoriesInteractor: GetCategoriesInteractor,
    private val getSelectedSiteInteractor: GetSelectedSiteInteractor,
    private val getItemsInteractor: GetItemsInteractor,
    //private val getItemDetailsInteractor: GetItemDetailsInteractor,
) : BaseViewModel<MainUiState>() {

    init {
        getSelectedSite()
        getCategories()
    }
    // region public methods
    // endregion

    // region override methods
    override fun getDefault(): MainUiState {
        return MainUiState()
    }
    // endregion

    // region private methods
    private fun getSelectedSite() {
        viewModelScope.launch {
            getSelectedSiteInteractor.execute()
                .collect { site ->
                    if (site?.name != null) {
                        _uiState.update {
                            it.copy(
                                siteName = site.name
                                //isLoading = false,
                                //productsByCategory = productsByCategory
                            )
                        }
                    } else {
                        _action.postValue(Action.NavigateToActivity.SiteSelector)
                    }
                }
        }
    }

    fun getItemsByCategory(categoryId: String) {
        viewModelScope.launch {
            getItemsInteractor.execute(categoryId)
                .collect { productList ->
                    _uiState.update {
                        val productsByCategory = it.productsByCategory.toMutableMap().apply {
                            this[categoryId] = productList
                        }
                        it.copy(
                            productsByCategory = productsByCategory
                        )
                    }
                }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesInteractor.execute()
                .collect { categories ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            categories = categories
                        )
                    }
                }
        }
    }

    fun findProduct(productId: String): Product? {
        return _uiState.value.productsByCategory.values.flatten().firstOrNull {
            it.id == productId
        }
    }

    fun searchProduct(query: String) {
        viewModelScope.launch {
            getItemsInteractor.execute(query = query)
                .collect { products ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            lastQuery = query,
                            productsByQuery = products,
                        )
                }
            }
        }
    }

    fun onChageSiteClick() {
            viewModelScope.launch {
                _action.postValue(
                    Action.NavigateToActivity.SiteSelector
                )
            }

    }
    // endregion

}

data class MainUiState(
    val isLoading: Boolean = true,
    val siteName: String = "",
    val categories: List<Category> = listOf(),
    val productsByCategory: Map<String, List<Product>> = mapOf(),
    val lastQuery: String? = null,
    val productsByQuery: List<Product> = listOf(),
)
