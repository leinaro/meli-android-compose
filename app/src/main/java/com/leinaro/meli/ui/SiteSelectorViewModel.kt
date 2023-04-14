package com.leinaro.meli.ui

import androidx.lifecycle.viewModelScope
import com.leinaro.meli.domain.entities.Site
import com.leinaro.meli.domain.interactors.GetSitesInteractor
import com.leinaro.meli.domain.interactors.SelectSiteInteractor
import com.leinaro.meli.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SiteSelectorViewModel  @Inject constructor(
    private val getSitesInteractor: GetSitesInteractor,
    //private val selectSiteInteractor : SelectSiteInteractor,
): BaseViewModel<SiteSelectorUiState>() {
    override fun getDefault(): SiteSelectorUiState {
        return SiteSelectorUiState(
            listOf()
        )
    }

    //init {
        //getSites()
    //}

    fun onSiteSelected(site: Site) {
        viewModelScope.launch {
            //selectSiteInteractor.execute(site)

        }
    }

    fun getSites(){
        viewModelScope.launch {
            getSitesInteractor.execute()
                .collect { sites ->
                    _uiState.update {
                        it.copy(sites = sites)
                    }
                }
        }
    }
}

data class SiteSelectorUiState(
    val sites: List<Site>,
)