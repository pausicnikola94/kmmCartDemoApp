package com.example.kmmcartdemoapp.feature_cart.domain.repository

import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.CheckoutOrder
import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.Product
import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.ProductOrder
import kotlinx.coroutines.flow.Flow

interface ICheckoutOrderRepository {
    fun getCheckoutOrder(): Flow<CheckoutOrder?>
    fun getProductOrder(id: String): Flow<ProductOrder?>
    suspend fun addToCart(product: Product, checkoutOrderId: Int)
    suspend fun removeFromCart(product: Product, checkoutOrderId: Int)
    suspend fun init() {}
}
