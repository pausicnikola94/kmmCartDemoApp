package com.example.kmmcartdemoapp.feature_cart.data.interfaces

import com.example.kmmcartdemoapp.core.util.Resource
import com.example.kmmcartdemoapp.feature_cart.data.remote.dto.ProductDto

interface ICategoryDataSource {
    suspend fun getCategories(): Resource<List<String>>
    suspend fun getProductsByCategoryId(id: String): Resource<List<ProductDto>>
}
