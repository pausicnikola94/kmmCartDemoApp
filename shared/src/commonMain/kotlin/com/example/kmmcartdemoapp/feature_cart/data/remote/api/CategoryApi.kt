package com.example.kmmcartdemoapp.feature_cart.data.remote.api

import com.example.kmmcartdemoapp.core.util.Constants
import com.example.kmmcartdemoapp.feature_cart.data.remote.dto.ProductDto
import io.ktor.client.*
import io.ktor.client.request.*

class CategoryApi(
    private val client: HttpClient
) : ICategoryApi {
    override suspend fun getCategories(): List<String> {
       return client.get { url("${Constants.BASE_URL}/products/categories") }
    }

    override suspend fun getProductsByCategoryId(id: String): List<ProductDto> {
        return client.get{ url("${Constants.BASE_URL}/products/category/$id") }
    }
}