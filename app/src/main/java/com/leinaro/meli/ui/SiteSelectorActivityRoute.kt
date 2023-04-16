package com.leinaro.meli.ui

sealed class SiteSelectorActivityRoute(val route: String) {
    object SiteSelectorScreen: SiteSelectorActivityRoute(route = "SiteSelectorScreen")
    object MainActivity: SiteSelectorActivityRoute(route = "MainActivity")
}