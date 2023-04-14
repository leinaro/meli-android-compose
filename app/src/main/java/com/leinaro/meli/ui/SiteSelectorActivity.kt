package com.leinaro.meli.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leinaro.meli.ui.common.Action.NavigateToMainActivity
import com.leinaro.meli.ui.components.SiteSelectorComponent
import com.leinaro.meli.ui.ui.theme.MeliTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SiteSelectorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeliTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    /*LaunchedEffect(Unit) {
                        // observeAction(viewModel.action)
                        // Show snackbar using a coroutine, when the coroutine is cancelled the
                        // snackbar will automatically dismiss. This coroutine will cancel whenever
                        // `state.hasError` is false, and only start when `state.hasError` is true
                        // (due to the above if-check), or if `scaffoldState.snackbarHostState` changes.

                    }*//*
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = SiteSelectorActivityRoute.SiteSelectorScreen.route,
                    ) {
                        composable(SiteSelectorActivityRoute.SiteSelectorScreen.route){*/
                            SiteSelectorComponent()
                       /* }
                        composable(SiteSelectorActivityRoute.MainActivity.route) {
                            val context = LocalContext.current
                            context.startActivity(Intent(context, MainActivity::class.java))
                        }
                    }*/
                   // val viewModel: SiteSelectorViewModel = viewModel()

                    /*val context = LocalContext.current
                    */
                }
            }
        }
    }
}

private sealed class SiteSelectorActivityRoute(val route: String) {
    object SiteSelectorScreen: SiteSelectorActivityRoute(route = "SiteSelectorScreen")
    object MainActivity: SiteSelectorActivityRoute(route = "MainActivity")
}

private fun observeAction(

) {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MeliTheme {
        SiteSelectorComponent()
    }
}