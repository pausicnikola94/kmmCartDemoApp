package com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces

import com.example.kmmcartdemoapp.core.util.Resource
import com.example.kmmcartdemoapp.feature_cart.domain.models.main.Category
import kotlinx.coroutines.flow.Flow

interface IGetCategories {
    suspend fun getCategorySection(): Flow<Resource<List<Category>>>
}
