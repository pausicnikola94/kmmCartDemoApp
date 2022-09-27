package com.example.kmmcartdemoapp.android.presentation.main

import com.example.kmmcartdemoapp.android.presentation.models.CategorySection

data class MainUiState(
    val categorySectionList: List<CategorySection>? = null,
    val hasCheckoutOrder: Boolean? = false,
    val isLoading: Boolean? = false,
    val hasError: String? = null
)
