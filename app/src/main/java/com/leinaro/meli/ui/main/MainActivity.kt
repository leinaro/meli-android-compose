package com.leinaro.meli.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leinaro.meli.ui.common.Action.NavigateTo
import com.leinaro.meli.ui.common.Action.NavigateToActivity
import com.leinaro.meli.ui.common.ScaffoldViewState
import com.leinaro.meli.ui.common.TopAppBar
import com.leinaro.meli.ui.siteselector.SiteSelectorActivity
import com.leinaro.meli.ui.theme.MeliTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeliTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<MainViewModel>()
                    val navController = rememberNavController()
                    var scaffoldViewState: ScaffoldViewState? by remember {
                        mutableStateOf(null)
                    }

                    LaunchedEffect(viewModel.action) {
                        observeAction(
                            viewModel = viewModel,
                            lifecycleOwner = this@MainActivity,
                            navController = navController,
                            activity = this@MainActivity
                        )
                    }

                    Scaffold(

                        topBar = {
                            scaffoldViewState?.let { scaffoldViewState->
                                TopAppBar(scaffoldViewState)
                            }
                        },
                        floatingActionButton = {}
                    ) { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues = paddingValues)){
                            MainNavHostComponent(
                                navController = navController,
                                viewModel = viewModel,
                                updateScaffoldViewState = { newScaffoldViewState ->
                                    scaffoldViewState = newScaffoldViewState
                                }
                            )
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