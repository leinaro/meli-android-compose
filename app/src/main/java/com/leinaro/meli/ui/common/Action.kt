package com.leinaro.meli.ui.common

sealed class Action {
    sealed class NavigateToActivity : Action(){
        object Main : NavigateToActivity()
        object SiteSelector : NavigateToActivity()
    }
    data class NavigateTo(val route: String) : Action()
}