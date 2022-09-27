package com.example.kmmcartdemoapp.feature_cart.domain.models.main

import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.Product

data class Category(
    val id: String,
    val products: List<Product>,
    val categoryType: CategoryType
)
