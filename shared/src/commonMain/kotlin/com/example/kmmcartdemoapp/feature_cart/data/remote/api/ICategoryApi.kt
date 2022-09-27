package com.example.kmmcartdemoapp.feature_cart.data.remote.api

import com.example.kmmcartdemoapp.feature_cart.data.remote.dto.ProductDto
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*


interface ICategoryApi {
    suspend fun getCategories(): List<String>

    suspend fun getProductsByCategoryId(id: String): List<ProductDto>

    //TODO: Inject
    companion object {
        fun create() : ICategoryApi {
            return CategoryApi(HttpClient() {
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            })
        }
    }
}
