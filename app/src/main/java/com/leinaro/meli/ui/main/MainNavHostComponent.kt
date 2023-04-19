package com.leinaro.meli.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.leinaro.meli.ui.common.ScaffoldViewState
import com.leinaro.meli.ui.components.ProductDetailScreen
import com.leinaro.meli.ui.search.SearchScreen

@Composable
internal fun MainNavHostComponent(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = hiltViewModel(),
    updateScaffoldViewState: (ScaffoldViewState?) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = MainActivityRoute.MainScreen.route,
    ) {
        composable(MainActivityRoute.MainScreen.route) {
            updateScaffoldViewState(
                ScaffoldViewState(
                    actions = {
                        IconButton(
                            onClick = {
                                viewModel.navigateTo(
                                    MainActivityRoute.SearchScreen.route
                                )
                        }
                        ) {
                            Icon(
                                imageVector = Filled.Search,
                                contentDescription = "Search items"
                            )
                        }
                    }
                )
            )
            MainScreen(viewModel)
        }
        composable(
            MainActivityRoute.ProductDetailScreen.route,
            arguments = listOf(
                navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            productId?.let {
                updateScaffoldViewState(
                    ScaffoldViewState(
                        navigationIcon = {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    imageVector = Filled.ArrowBack,
                                    contentDescription = "Go back"
                                )
                            }
                        }
                    )
                )

                val product = viewModel.findProduct(productId)
                product?.let {
                    ProductDetailScreen(product)
                } ?: run {
                    // show error
                }
            } ?: run {
                // show error
            }
        }
        composable(
            MainActivityRoute.SearchScreen.route,
            //arguments = listOf(
            //    navArgument("query") { type = NavType.StringType }
            //)
        ) { backStackEntry ->
            //val query = backStackEntry.arguments?.getString("query")
            updateScaffoldViewState(null)

            SearchScreen(
                query = uiState.lastQuery,
                productList = uiState.productsByQuery,
                onQueryChange = {
                    viewModel.searchProduct(it)
                },
                navigateTo = {
                    navController.navigate(it)
                },
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}

@Preview
@Composable
fun MainNavHostComponentPreview() {
    MainNavHostComponent()
}
