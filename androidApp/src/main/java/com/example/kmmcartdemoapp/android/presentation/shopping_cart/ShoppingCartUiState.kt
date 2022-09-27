package com.example.kmmcartdemoapp.android.presentation.shopping_cart

import com.example.kmmcartdemoapp.android.presentation.models.ProductWrapper

data class ShoppingCartUiState(
    val products: List<ProductWrapper>? = null,
    val isLoading: Boolean? = false,
    val hasError: String? = null
)
