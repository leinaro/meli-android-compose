package com.leinaro.meli.ui

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

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesInteractor.execute()
                .collect { categories ->
                     categories.onEach { category ->
                        }

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            categories = categories
                        )
                    }
                }
        }
    }
    // endregion

}

data class MainUiState(
    val isLoading: Boolean = true,
    val siteName: String = "",
    val categories: List<Category> = listOf(),
    val productsByCategory: Map<String, List<Product>> = mapOf(),
)
