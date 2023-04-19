package com.leinaro.meli.ui.siteselector

import androidx.lifecycle.viewModelScope
import com.leinaro.meli.domain.entities.Site
import com.leinaro.meli.domain.interactors.GetSitesInteractor
import com.leinaro.meli.domain.interactors.SelectSiteInteractor
import com.leinaro.meli.ui.common.Action
import com.leinaro.meli.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SiteSelectorViewModel @Inject constructor(
    private val getSitesInteractor: GetSitesInteractor,
    private val selectSiteInteractor: SelectSiteInteractor,
) : BaseViewModel<SiteSelectorUiState>() {
    init {
        getSites()
    }

    // region public methods
    fun onSiteSelected(site: Site) {
        viewModelScope.launch {
            selectSiteInteractor.execute(site)
            _action.postValue(
                Action.NavigateToActivity.Main
            )
        }
    }
    // endregion

    // region override methods
    override fun getDefault(): SiteSelectorUiState {
        return SiteSelectorUiState()
    }
    // endregion

    // region private methods
    private fun getSites() {
        viewModelScope.launch {
            getSitesInteractor.execute()
                .collect { sites ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            sites = sites
                        )
                    }
                }
        }
    }
    // endregion
}

data class SiteSelectorUiState(
    val isLoading: Boolean = true,
    val sites: List<Site> = listOf(),
)