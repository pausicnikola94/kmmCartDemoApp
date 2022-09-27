package com.example.kmmcartdemoapp.feature_cart.domain.use_cases

import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.Product
import com.example.kmmcartdemoapp.feature_cart.domain.repository.ICheckoutOrderRepository
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IAddToCart

class AddToCart constructor(
    private val checkoutOrderRepository: ICheckoutOrderRepository
) : IAddToCart {
    override suspend fun addProduct(product: Product, checkoutOrderId: Int) {
        checkoutOrderRepository.addToCart(product.copy(quantity = product.quantity + 1), checkoutOrderId)
    }
}
