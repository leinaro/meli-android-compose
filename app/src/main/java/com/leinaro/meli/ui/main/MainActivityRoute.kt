package com.leinaro.meli.ui.main

sealed class MainActivityRoute(val route: String){
    object MainScreen: MainActivityRoute(route = "MainScreen")
    object ProductDetailScreen: MainActivityRoute(route = "ProductDetailScreen/{productId}")
    object SearchScreen: MainActivityRoute(route = "SearchScreen")
}