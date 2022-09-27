package com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces

import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.Product

interface IAddToCart {
    suspend fun addProduct(product: Product, checkoutOrderId: Int)
}
