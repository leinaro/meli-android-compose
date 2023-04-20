package com.leinaro.meli.ui

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.leinaro.meli.ui.common.Action.NavigateTo
import com.leinaro.meli.ui.common.Action.NavigateToActivity
import com.leinaro.meli.ui.common.BaseViewModel
import com.leinaro.meli.ui.main.MainActivity
import com.leinaro.meli.ui.siteselector.SiteSelectorActivity
import com.leinaro.meli.ui.ui.theme.MeliTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class StarthupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeliTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val viewModel = hiltViewModel<StarthubViewModel>()
                    LaunchedEffect(viewModel.action) {
                        observeAction(
                            viewModel = viewModel,
                            lifecycleOwner = this@StarthupActivity,
                            activity = this@StarthupActivity
                        )
                    }
                    SplashScreen(){
                        viewModel.getSelectedSite()
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(
    onAnimationSplashEnds: ()->Unit = {},
) {

    val scaleAnimation: Animatable<Float, AnimationVector1D> =
        remember { Animatable(initialValue = 0f) }

    AnimationSplashContent(
        scaleAnimation = scaleAnimation,
        durationMillisAnimation = 1500,
        delayScreen = 3000L,
        onAnimationSplashEnds = onAnimationSplashEnds,
    )

    DesignSplashScreen(
        scaleAnimation = scaleAnimation
    )
}

@Composable
@Preview
fun SplashScreenPreview() {
    SplashScreen()
}

@Composable
fun DesignSplashScreen(
    modifier: Modifier = Modifier,
    scaleAnimation: Animatable<Float, AnimationVector1D>
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            LinearProgressIndicator()
            Text(
                text = "Meli - Prueba tecnica",
                color = Color.Black,
                fontSize = 50.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                modifier = modifier.scale(scale =
                scaleAnimation.value)
            )
        }
    }
}

@Composable
fun AnimationSplashContent(
    scaleAnimation: Animatable<Float, AnimationVector1D>,
    onAnimationSplashEnds: () ->Unit= {},
    durationMillisAnimation: Int,
    delayScreen: Long
) {

    LaunchedEffect(key1 = true) {
        scaleAnimation.animateTo(
            targetValue = 0.5F,
            animationSpec = tween(
                durationMillis = durationMillisAnimation,
                easing = {
                    OvershootInterpolator(3F).getInterpolation(it)
                }
            )
        )

        delay(timeMillis = delayScreen)
        onAnimationSplashEnds()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MeliTheme {
    }
}


private fun <T> observeAction(
    viewModel: BaseViewModel<T>,
    activity: ComponentActivity,
    lifecycleOwner: LifecycleOwner,
    navController: NavController? = null,
) {
    viewModel.action.observe(lifecycleOwner) { action ->
        when (action) {
            is NavigateToActivity.Main -> {
                val intent = Intent(activity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                activity.startActivity(intent)
            }
            is NavigateToActivity.SiteSelector -> {
                val intent = Intent(activity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                activity.startActivity(Intent(activity, SiteSelectorActivity::class.java))
            }
            is NavigateTo -> {
                navController?.navigate(action.route)
            }

        }
    }
}