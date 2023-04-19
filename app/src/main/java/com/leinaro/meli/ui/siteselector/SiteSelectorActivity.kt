package com.leinaro.meli.ui.siteselector

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leinaro.meli.ui.common.Action.NavigateTo
import com.leinaro.meli.ui.common.Action.NavigateToActivity
import com.leinaro.meli.ui.main.MainActivity
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
                    val viewModel = hiltViewModel<SiteSelectorViewModel>()
                    val navController = rememberNavController()

                    LaunchedEffect(viewModel.action) {
                        observeAction(
                            viewModel = viewModel,
                            lifecycleOwner = this@SiteSelectorActivity,
                            navController = navController,
                            activity = this@SiteSelectorActivity
                        )
                    }
                    NavHost(
                        navController = navController,
                        startDestination = SiteSelectorActivityRoute.SiteSelectorScreen.route,
                    ) {
                        composable(SiteSelectorActivityRoute.SiteSelectorScreen.route) {
                            SiteSelectorScreen(viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}

private fun observeAction(
    viewModel: SiteSelectorViewModel,
    activity: ComponentActivity,
    lifecycleOwner: LifecycleOwner,
    navController: NavController,
) {
    viewModel.action.observe(lifecycleOwner) { action ->
        when (action) {
            is NavigateToActivity.Main -> {
                val intent = Intent(activity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                activity.startActivity(intent)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MeliTheme {
        SiteSelectorScreen()
    }
}