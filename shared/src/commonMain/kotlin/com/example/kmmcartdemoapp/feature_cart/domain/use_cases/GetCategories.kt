package com.example.kmmcartdemoapp.feature_cart.domain.use_cases

import com.example.kmmcartdemoapp.core.util.Resource
import com.example.kmmcartdemoapp.feature_cart.domain.models.main.Category
import com.example.kmmcartdemoapp.feature_cart.domain.repository.ICategoryRepository
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IGetCategories
import kotlinx.coroutines.flow.Flow

class GetCategories constructor(
    private val categoryRepository: ICategoryRepository,
) : IGetCategories {

    override suspend fun getCategorySection(): Flow<Resource<List<Category>>> {
        return categoryRepository.getCategories()
    }
}
