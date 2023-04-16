package com.leinaro.meli.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells.Adaptive
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.leinaro.meli.R.drawable
import com.leinaro.meli.domain.entities.Product
import com.leinaro.meli.ui.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val productsByCategory = uiState.productsByCategory

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.CenterEnd,
        )  {
            OutlinedButton(
                modifier = Modifier.wrapContentSize(),
                onClick = { /*TODO*/ }
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
            CategoryGridComponent(productsByCategory)
        }
    }

}


fun LazyGridScope.categoryGridItemComponent(
    categoryName: String,
    productList: List<Product>,
) {
    item(
        span = { GridItemSpan(this.maxLineSpan) },
        content = {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                textAlign = TextAlign.Left,
                text = categoryName,
            )
        }
    )
    items(productList) { product ->
        ProductItemComponent(product.name, product.price, product.imageUrl)
    }
    item(
        span = { GridItemSpan(this.maxLineSpan) },
        content = {
            ShowMoreComponent()
        }
    )
}

@Composable
fun CategoryGridComponent(productsByCategory: Map<String, List<Product>>) {
    val categories = productsByCategory.keys.toList()

    LazyVerticalGrid(
        columns = Adaptive(minSize = 128.dp)
    ) {
        categories.forEach { category ->
            val products = productsByCategory[category].orEmpty()
            categoryGridItemComponent(category, products)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CategoryGridComponentPreview() {
    CategoryGridComponent(
        mapOf(
            Pair(
                "Categoria",
                listOf(
                    Product(
                        "producto 1",
                        "15000",
                        "producto 3",
                    ),
                    Product(
                        "producto 1",
                        "15000",
                        "producto 3",
                    ),
                    Product(
                        "producto 1",
                        "15000",
                        "producto 3",
                    ),
                    Product(
                        "producto 1",
                        "15000",
                        "producto 3",
                    ),
                )
            )
        )
    )
}

@Composable
fun LoadingComponent() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .wrapContentSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingComponentPreview() {
    LoadingComponent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItemComponent(
    name: String,
    price: String,
    imageUrl: String,
) {
    Card(
        modifier = Modifier
            .padding(8.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
                .aspectRatio(0.7f)
                .clickable(onClick = { }),
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(16.dp)),
                model = imageUrl,
                placeholder = debugPlaceholder(drawable.ic_launcher_background),
                contentDescription = "Translated description of what the image contains"
            )
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Text(
                text = price,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF322F37),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItemComponentPreview() {
    ProductItemComponent("Producto ABC", "$500.000", "image_url")
}

@Composable
fun ShowMoreComponent(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = "Show more",
                textAlign = TextAlign.Center,
            )
        }
        Divider(
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()  //fill the max height
                .width(1.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowMoreComponentPreview() {
    ShowMoreComponent()
}

@Composable
fun debugPlaceholder(@DrawableRes debugPreview: Int) =
    if (LocalInspectionMode.current) {
        painterResource(id = debugPreview)
    } else {
        null
    }