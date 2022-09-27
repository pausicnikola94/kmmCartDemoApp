package com.example.kmmcartdemoapp.feature_cart.domain.use_cases

import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.Product
import com.example.kmmcartdemoapp.feature_cart.domain.repository.ICheckoutOrderRepository
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IRemoveFromCart

class RemoveFromCart constructor(
    private val checkoutOrderRepository: ICheckoutOrderRepository
) : IRemoveFromCart {
    override suspend fun removeProduct(product: Product, checkoutOrderId: Int) {
        if (product.quantity - 1 >= 0) {
            checkoutOrderRepository.removeFromCart(product.copy(quantity = product.quantity - 1), checkoutOrderId)
        }
    }
}
