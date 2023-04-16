package com.leinaro.meli.ui

sealed class SiteSelectorActivityRoute(val route: String) {
    object SiteSelectorScreen: SiteSelectorActivityRoute(route = "SiteSelectorScreen")
}

sealed class MainActivityRoute(val route: String){
    object MainScreen: MainActivityRoute(route = "MainScreen")
    object ProductDetailScreen: MainActivityRoute(route = "ProductDetailScree/{productId}")

}