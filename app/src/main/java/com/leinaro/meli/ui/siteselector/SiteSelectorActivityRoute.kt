package com.leinaro.meli.ui.siteselector

sealed class SiteSelectorActivityRoute(val route: String) {
    object SiteSelectorScreen: SiteSelectorActivityRoute(route = "SiteSelectorScreen")
}

