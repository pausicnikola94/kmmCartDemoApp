package com.example.kmmcartdemoapp.feature_cart.data.remote.api

import com.example.kmmcartdemoapp.feature_cart.data.remote.dto.ProductDto

interface ICategoryApi {
    suspend fun getCategories(): List<String>

    suspend fun getProductsByCategoryId(id: String): List<ProductDto>
}
