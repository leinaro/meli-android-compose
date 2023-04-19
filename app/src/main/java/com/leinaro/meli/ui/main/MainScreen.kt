package com.leinaro.meli.ui.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leinaro.meli.domain.entities.Category
import com.leinaro.meli.domain.entities.Product
import com.leinaro.meli.ui.common.LoadingComponent
import com.leinaro.meli.ui.common.ProductItemComponent
import com.leinaro.meli.ui.common.ShowMoreComponent
import com.leinaro.meli.ui.siteselector.SiteSelectorScreen

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.CenterEnd,
        ) {
            OutlinedButton(
                modifier = Modifier.wrapContentSize(),
                onClick = {
                    viewModel.onChageSiteClick()
                }
            ) {
                Text(
                    text = uiState.siteName,
                    textAlign = TextAlign.Center,
                )
            }
        }

        Divider(
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()  //fill the max height
                .width(1.dp)
        )
        if (uiState.isLoading) {
            LoadingComponent()
        } else {
            CategoryGridComponent(
                uiState,
                viewModel,
            )
        }
    }
}

fun LazyGridScope.categoryGridItemComponent(
    productList: List<Product>,
    navigateTo: (String) -> Unit = {},
) {
    items(productList) { product ->
        ProductItemComponent(
            product, navigateTo
        )
    }
}

@Composable
fun CategoryGridComponent(
    uiState: MainUiState,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val productsByCategory = uiState.productsByCategory
    val categories = uiState.categories

    val configuration = LocalConfiguration.current

    val columns = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        6
    } else {
        3
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
    ) {
        categories.forEach { category ->
            viewModel.getItemsByCategory(category.id)
            item(
                span = { GridItemSpan(this.maxLineSpan) },
                content = {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        textAlign = TextAlign.Left,
                        text = category.name,
                    )
                }
            )

            val products = productsByCategory[category.id].orEmpty()
            categoryGridItemComponent(
                products,
                viewModel::navigateTo,
            )
            item(
                span = { GridItemSpan(this.maxLineSpan) },
                content = {
                    ShowMoreComponent(
                        onClick = {
                            //TODO: search by category category.id
                        }
                    )
                }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CategoryGridComponentPreview() {
    CategoryGridComponent(
        MainUiState(
            categories = listOf(Category("123", "Categoria 1")),
            productsByCategory = mapOf(
                Pair(
                    "123",
                    listOf(
                        Product(
                            "producto 1",
                            "producto 1",
                            "15000",
                            "15000",
                            "producto 3",
                            1,
                            2,
                        ),
                        Product(
                            "producto 1",
                            "producto 1",
                            "15000",
                            "15000",
                            "producto 3",
                            1,
                            2,
                        ),
                        Product(
                            "producto 1",
                            "producto 1",
                            "15000",
                            "15000",
                            "producto 3",
                            1,
                            2,
                        ),
                        Product(
                            "producto 1",
                            "producto 1",
                            "15000",
                            "15000",
                            "producto 3",
                            1,
                            2,
                        ),
                    )
                )
            )
        )
    )
}
