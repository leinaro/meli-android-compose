package com.leinaro.meli.ui.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.leinaro.meli.domain.entities.Product
import com.leinaro.meli.ui.common.SearchView
import com.leinaro.meli.ui.main.categoryGridItemComponent

@Composable
fun SearchScreen(
    query: String? = null,
    productList: List<Product> = listOf(),
    onQueryChange: (String) -> Unit = {},
    navigateTo: (String) -> Unit = {},
    navigateUp: () -> Unit = {},
) {

    val textState = remember { mutableStateOf(TextFieldValue(query.orEmpty())) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        SearchView(
            state = textState,
            onQueryChange = onQueryChange,
            navigateUp = navigateUp,
        )
        SearchGridView(
            productList = productList,
            navigateTo = navigateTo,
        )
    }
}

@Composable
fun SearchGridView(
    productList: List<Product> = listOf(),
    navigateTo: (String) -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val columns = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        6
    } else {
        3
    }

    LazyVerticalGrid(
        columns = Fixed(columns),
    ) {
        categoryGridItemComponent(
            productList = productList,
            navigateTo,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        productList = listOf(
            Product(
                "123",
                "product abc",
                "$15.000",
                "$15.000",
                "qwerqwerqwer",
                1,
                2,
            ),
            Product(
                "123",
                "product abc",
                "$15.000",
                "$15.000",
                "qwerqwerqwer",
                1,
                2,
            ),
        )
    )
}