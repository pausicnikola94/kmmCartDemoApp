package com.example.kmmcartdemoapp.feature_cart.data.local.entity_mappers

import cartDb.CheckoutOrderEntity
import cartDb.ProductOrderEntity
import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.CheckoutOrder
import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.ProductOrder

fun CheckoutOrderEntity.toCheckoutOrder(products: List<ProductOrder>): CheckoutOrder {
    return CheckoutOrder(this.id, products)
}

fun ProductOrderEntity.toProductOrder(): ProductOrder {
    return ProductOrder(this.id, this.quantity)
}