package com.example.kmmcartdemoapp.feature_cart.data.interfaces

import cartDb.ProductOrderEntity
import kotlinx.coroutines.flow.Flow

interface IProductOrderDataSource {
    fun getProductOrderByCheckoutOrderId(checkoutOrderId: Int): Flow<List<ProductOrderEntity>>
    fun getProductOrderById(productId: String): Flow<ProductOrderEntity?>
    suspend fun insertOrUpdate(productOrder: ProductOrderEntity)
    suspend fun delete(id: String)
}
