package com.leinaro.meli.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leinaro.meli.domain.entities.Product
import com.leinaro.meli.domain.interactors.GetCategoriesInteractor
import com.leinaro.meli.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCategoriesInteractor: GetCategoriesInteractor,
): BaseViewModel<MainUiState>() {

    fun getCategories(){
        viewModelScope.launch {
            getCategoriesInteractor.execute()
                .collect { categories ->
                    _uiState.update {
                        val productsByCategory = categories.map { it.name to it.products }.toMap()
                        it.copy(productsByCategory = productsByCategory)
                    }
                }
        }
    }

    override fun getDefault(): MainUiState {
        return MainUiState(
            productsByCategory = mapOf(),
        )
    }
}

data class MainUiState(
    val productsByCategory: Map<String, List<Product>> = mapOf()
)
