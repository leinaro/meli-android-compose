package com.leinaro.meli.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<UiState> : ViewModel() {
    protected val _uiState by lazy {
        MutableStateFlow(
            getDefault()
        )
    }
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    val _action = SingleLiveEvent<Action>()
    val action: LiveData<Action> get() = _action

    protected abstract fun getDefault(): UiState

    fun navigateTo(route: String) {
        _action.postValue(Action.NavigateTo(route))
    }
}

