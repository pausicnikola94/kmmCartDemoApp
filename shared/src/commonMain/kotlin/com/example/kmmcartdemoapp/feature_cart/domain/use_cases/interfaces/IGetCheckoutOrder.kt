package com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces

import com.example.kmmcartdemoapp.core.util.Resource
import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.Product
import kotlinx.coroutines.flow.Flow

interface IGetCheckoutOrder {
    suspend fun getCheckoutOrder(): Flow<Resource<List<Product>>>
    suspend fun init()
}
