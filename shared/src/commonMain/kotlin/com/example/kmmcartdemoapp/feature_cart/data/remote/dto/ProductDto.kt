package com.example.kmmcartdemoapp.feature_cart.data.remote.dto

import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
) {
    fun toProduct(): Product {
        return Product(this.id, this.title, this.description, this.image, 0)
    }
}
