package com.leinaro.meli.domain.entities

import com.leinaro.meli.data.repositories.ProductResponse
import java.text.NumberFormat
import java.util.Currency

data class Product(
    val id: String,
    val name: String,
    val price: String,
    val originalPrice :String? = null,
    val imageUrl: String,
    val soldQuantity: Int,
    val availableQuantity: Int,
)


fun List<ProductResponse>.toUiModel() = map { productDto ->
    productDto.toUiModel()
}
fun ProductResponse.toUiModel() = Product(
    id = id,
    name = title,
    price = price?.toFormattedCurrency(currencyId).orEmpty(),
    originalPrice = originalPrice?.toFormattedCurrency(currencyId),
    imageUrl = thumbnail,
    soldQuantity = soldQuantity,
    availableQuantity = availableQuantity,
)

fun Double.toFormattedCurrency(currencyId: String = "COP"): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance(currencyId)
    return format.format(this)
}