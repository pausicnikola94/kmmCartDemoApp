package com.example.kmmcartdemoapp.feature_cart.data.local.data_source

import cartDb.CheckoutOrderEntity
import com.example.kmmcartdemoapp.KMMCartDemoAppDatabase
import com.example.kmmcartdemoapp.feature_cart.data.interfaces.ICheckoutOrderDataSource
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CheckoutOrderLocalDataSource constructor(
    db: KMMCartDemoAppDatabase
) : ICheckoutOrderDataSource {
    private val queries = db.checkoutOrderEntityQueries

    override fun getCheckoutOrder(): Flow<CheckoutOrderEntity?> {
        return queries.getCheckoutOrder().asFlow().map { it -> CheckoutOrderEntity(it.executeAsOne()) }
    }

    override suspend fun insertOrUpdate(checkoutOrder: CheckoutOrderEntity) {
        withContext(Dispatchers.Default) {
            queries.insertOrUpdate(checkoutOrder.id)
        }
    }

    override suspend fun doesItemExist(id: Int): Boolean {
        return true
//        withContext(Dispatchers.Default) {
//            queries
//        }
    }
}
