package com.leinaro.meli.ui

import androidx.lifecycle.viewModelScope
import com.leinaro.meli.domain.interactors.GetSelectedSiteInteractor
import com.leinaro.meli.ui.common.Action
import com.leinaro.meli.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarthubViewModel @Inject constructor(
    private val getSelectedSiteInteractor: GetSelectedSiteInteractor,
) : BaseViewModel<StarthubUiState>() {

    fun getSelectedSite() {
        viewModelScope.launch {
            getSelectedSiteInteractor.execute()
                .collect { site ->
                    if (site?.name != null) {
                        _action.postValue(Action.NavigateToActivity.Main)
                    } else {
                        _action.postValue(Action.NavigateToActivity.SiteSelector)
                    }
                }
        }
    }

    override fun getDefault(): StarthubUiState = StarthubUiState()
}

data class StarthubUiState(val author: String = "Inés Rojas")

