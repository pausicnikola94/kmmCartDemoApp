package com.example.kmmcartdemoapp.feature_cart.data.remote.data_source

import com.example.kmmcartdemoapp.core.util.Resource
import com.example.kmmcartdemoapp.feature_cart.data.interfaces.ICategoryDataSource
import com.example.kmmcartdemoapp.feature_cart.data.remote.api.ICategoryApi
import com.example.kmmcartdemoapp.feature_cart.data.remote.dto.ProductDto

class CategoryRemoteDataSource constructor(
    private val ICategoryApi: ICategoryApi
) : ICategoryDataSource {

    override suspend fun getCategories(): Resource<List<String>> {
        return Resource.success(ICategoryApi.getCategories())
    }

    override suspend fun getProductsByCategoryId(id: String): Resource<List<ProductDto>> {
        return Resource.success(ICategoryApi.getProductsByCategoryId(id))
    }
}
