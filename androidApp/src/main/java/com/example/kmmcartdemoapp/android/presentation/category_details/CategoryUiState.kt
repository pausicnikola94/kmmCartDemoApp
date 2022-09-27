package com.example.kmmcartdemoapp.android.presentation.category_details

import com.example.kmmcartdemoapp.android.presentation.models.ProductWrapper

data class CategoryUiState(
    val id: String? = null,
    val products: List<ProductWrapper>? = null,
    val isLoading: Boolean? = false,
    val hasError: String? = null
)
