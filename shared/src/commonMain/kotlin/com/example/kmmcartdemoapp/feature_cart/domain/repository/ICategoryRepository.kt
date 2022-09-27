package com.example.kmmcartdemoapp.feature_cart.domain.repository

import com.example.kmmcartdemoapp.core.util.Resource
import com.example.kmmcartdemoapp.feature_cart.domain.models.main.Category
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository {
    fun getCategories(): Flow<Resource<List<Category>>>
}
