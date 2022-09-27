package com.example.kmmcartdemoapp.feature_cart.data.local.data_source

import cartDb.ProductOrderEntity
import com.example.kmmcartdemoapp.KMMCartDemoAppDatabase
import com.example.kmmcartdemoapp.feature_cart.data.interfaces.IProductOrderDataSource
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext

class ProductOrderLocalDataSource constructor(
    db: KMMCartDemoAppDatabase
) : IProductOrderDataSource {

    private val queries = db.productOrderEntityQueries

    override fun getProductOrderByCheckoutOrderId(checkoutOrderId: Int): Flow<List<ProductOrderEntity>> {
        return queries.getProductOrderByCheckoutOrderId(checkoutOrderId.toLong()).asFlow().mapToList()
    }

    override fun getProductOrderById(productId: String): Flow<ProductOrderEntity?> {
        return queries.getProductOrderById(productId).asFlow().mapToOneOrNull()
    }

    override suspend fun insertOrUpdate(productOrder: ProductOrderEntity) {
        withContext(Dispatchers.Default) {
            queries.insertOrUpdate(productOrder.id, productOrder.quantity, productOrder.checkoutOrderId)
        }
    }

    override suspend fun delete(id: String) {
        withContext(Dispatchers.Default) {
            queries.delete(id)
        }
    }
}
