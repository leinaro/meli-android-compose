package com.leinaro.meli.ui.common

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TopAppBar(
    scaffoldViewState: ScaffoldViewState,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                "Meli",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = scaffoldViewState.navigationIcon,
        actions = scaffoldViewState.actions
    )
}
