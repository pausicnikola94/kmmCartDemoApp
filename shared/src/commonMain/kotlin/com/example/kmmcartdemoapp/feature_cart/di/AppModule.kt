package com.example.kmmcartdemoapp.feature_cart.di

import com.example.kmmcartdemoapp.feature_cart.data.interfaces.ICategoryDataSource
import com.example.kmmcartdemoapp.feature_cart.data.remote.data_source.CategoryRemoteDataSource
import com.example.kmmcartdemoapp.feature_cart.data.repository.CategoryRepository
import com.example.kmmcartdemoapp.feature_cart.domain.repository.ICategoryRepository
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.GetCategories
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IGetCategories
import org.koin.dsl.module

val appModule = module {

    single {
//        CategoryApi
    // TODO: Ktor Client
    }

    single<ICategoryDataSource> {
        CategoryRemoteDataSource(get())
    }

    single<ICategoryRepository> {
        CategoryRepository(get())
    }

    single<IGetCategories> {
        GetCategories(get())
    }
}