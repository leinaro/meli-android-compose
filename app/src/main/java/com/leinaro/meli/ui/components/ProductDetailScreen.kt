package com.leinaro.meli.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import com.leinaro.meli.R.drawable
import com.leinaro.meli.domain.entities.Product
import com.leinaro.meli.ui.common.debugPlaceholder

@Composable
fun ProductDetailScreen(product: Product) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = product.name,
            textAlign = TextAlign.Left,
        )

        AsyncImage(
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(16.dp)
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

        Row(
            //modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Price ",
                fontSize = 26.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF322F37),
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = product.price,
                fontSize = 26.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF322F37),
                textAlign = TextAlign.Center,
            )
            if (!product.originalPrice.isNullOrBlank()) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = product.price,
                    fontSize = 22.sp,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough),
                    fontWeight = FontWeight.Normal,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Sold quantity ",
                fontSize = 26.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF322F37),
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = product.soldQuantity.toString(),
                fontSize = 26.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF322F37),
                textAlign = TextAlign.Center,
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Available Quantity ",
                fontSize = 26.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF322F37),
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = product.availableQuantity.toString(),
                fontSize = 26.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF322F37),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    ProductDetailScreen(
        Product(
            "123",
            name = "Product",
            price = "$15.000",
            originalPrice = "$20.000",
            "",
        15,
        10,
        )
    )
}