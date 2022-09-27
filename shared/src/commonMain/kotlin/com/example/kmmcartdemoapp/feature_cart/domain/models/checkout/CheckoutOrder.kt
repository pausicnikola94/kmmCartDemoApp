package com.example.kmmcartdemoapp.feature_cart.domain.models.checkout

data class CheckoutOrder(
    val id: Long = 0,
    val products: List<ProductOrder>,
    val productsUI: MutableList<Product>? = null
)
