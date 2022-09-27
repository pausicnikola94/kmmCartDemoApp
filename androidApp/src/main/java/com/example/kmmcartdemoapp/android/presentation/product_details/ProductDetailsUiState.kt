package com.example.kmmcartdemoapp.android.presentation.product_details

import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.Product

data class ProductDetailsUiState(
    val product: Product? = null,
)
