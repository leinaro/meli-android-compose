package com.leinaro.meli.ui.common

import androidx.lifecycle.ViewModel
import com.leinaro.meli.ui.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<UiState>: ViewModel() {
    protected val _uiState by lazy {
        MutableStateFlow(
            getDefault()
        )
    }
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

   // protected val _action = MutableStateFlow<Action?>(null)

   // val action: StateFlow<Action?> = _action.asStateFlow()



    protected abstract fun getDefault() : UiState

}

sealed class Action {
    object NavigateToMainActivity : Action()
}
