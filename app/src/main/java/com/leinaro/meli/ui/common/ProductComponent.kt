package com.leinaro.meli.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextOverflow.Companion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import com.leinaro.meli.R.drawable
import com.leinaro.meli.domain.entities.Product
import com.leinaro.meli.ui.main.MainActivityRoute.ProductDetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItemComponent(
    product: Product,
    navigateTo: (String) -> Unit = {},
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
                .clickable(onClick = {
                    navigateTo(
                        ProductDetailScreen.route.replace(
                            "{productId}", product.id
                        )
                    )
                }),
        ) {
            AsyncImage(
                contentScale = ContentScale.Fit,

                modifier = Modifier
                    .padding(8.dp)
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                model = Builder(LocalContext.current)
                    .data(product.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = debugPlaceholder(drawable.ic_launcher_background),
                contentDescription = "Translated description of what the image contains"
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = product.name,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = product.price,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF322F37),
                textAlign = TextAlign.End,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItemComponentPreview() {
    ProductItemComponent(
        Product(
            "123",
            "Producto ABC nombre muy largo lorem ipsum 123 asd",
            "$500.000",
            "$500.000",
            "image_url",
        9,
            8,
        )
    )
}
