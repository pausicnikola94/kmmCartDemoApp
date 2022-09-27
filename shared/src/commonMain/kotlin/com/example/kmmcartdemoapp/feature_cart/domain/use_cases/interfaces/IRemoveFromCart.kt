package com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces

import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.Product

interface IRemoveFromCart {
    suspend fun removeProduct(product: Product, checkoutOrderId: Int)
}
