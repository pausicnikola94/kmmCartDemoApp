package com.example.kmmcartdemoapp.feature_cart.data.interfaces

import cartDb.CheckoutOrderEntity
import kotlinx.coroutines.flow.Flow

interface ICheckoutOrderDataSource {
    fun getCheckoutOrder(): Flow<CheckoutOrderEntity?>
    suspend fun insertOrUpdate(checkoutOrder: CheckoutOrderEntity)
    suspend fun doesItemExist(id: Int): Boolean
}
