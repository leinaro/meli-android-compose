package com.leinaro.meli.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.leinaro.meli.ui.common.Action.NavigateTo
import com.leinaro.meli.ui.common.Action.NavigateToActivity
import com.leinaro.meli.ui.components.MainScreen
import com.leinaro.meli.ui.components.ProductDetailScreen
import com.leinaro.meli.ui.theme.MeliTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeliTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<MainViewModel>()
                    val navController = rememberNavController()

                    LaunchedEffect(viewModel.action) {
                        observeAction(
                            viewModel = viewModel,
                            lifecycleOwner = this@MainActivity,
                            navController = navController,
                            activity = this@MainActivity
                        )
                    }
                    NavHost(
                        navController = navController,
                        startDestination = MainActivityRoute.MainScreen.route,
                    ) {
                        composable(MainActivityRoute.MainScreen.route) {
                            MainScreen(viewModel)
                        }
                        composable(
                            MainActivityRoute.ProductDetailScreen.route,
                            arguments = listOf(
                                navArgument("productId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val productId = backStackEntry.arguments?.getString("productId")
                            productId?.let {
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
                    }

                }
            }
        }
    }
}

private fun observeAction(
    viewModel: MainViewModel,
    activity: ComponentActivity,
    lifecycleOwner: LifecycleOwner,
    navController: NavController,
) {
    viewModel.action.observe(lifecycleOwner) { action ->
        when (action) {
            is NavigateToActivity.Main -> {
                activity.startActivity(Intent(activity, MainActivity::class.java))
            }
            is NavigateToActivity.SiteSelector -> {
                activity.startActivity(Intent(activity, SiteSelectorActivity::class.java))
            }
            is NavigateTo -> {
                navController.navigate(action.route)
            }

        }
    }
}