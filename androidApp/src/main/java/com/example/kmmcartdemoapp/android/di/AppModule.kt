package com.example.kmmcartdemoapp.android.di

import com.example.kmmcartdemoapp.KMMCartDemoAppDatabase
import com.example.kmmcartdemoapp.feature_cart.data.interfaces.ICategoryDataSource
import com.example.kmmcartdemoapp.feature_cart.data.interfaces.ICheckoutOrderDataSource
import com.example.kmmcartdemoapp.feature_cart.data.interfaces.IProductOrderDataSource
import com.example.kmmcartdemoapp.feature_cart.data.local.data_source.CheckoutOrderLocalDataSource
import com.example.kmmcartdemoapp.feature_cart.data.local.data_source.ProductOrderLocalDataSource
import com.example.kmmcartdemoapp.feature_cart.data.remote.api.CategoryApi
import com.example.kmmcartdemoapp.feature_cart.data.remote.api.ICategoryApi
import com.example.kmmcartdemoapp.feature_cart.data.remote.data_source.CategoryRemoteDataSource
import com.example.kmmcartdemoapp.feature_cart.data.repository.CategoryRepository
import com.example.kmmcartdemoapp.feature_cart.data.repository.CheckoutOrderRepository
import com.example.kmmcartdemoapp.feature_cart.domain.repository.ICategoryRepository
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.*
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.*
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    //Http - Remote
    single {
        HttpClient() {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    single<ICategoryApi> {
        CategoryApi(get())
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

    //DB
    single<SqlDriver> {
        AndroidSqliteDriver(schema = KMMCartDemoAppDatabase.Schema,
            context = this.androidContext(),
            name = "kmmCartDemoApp.db"
        )
    }

    single {
        KMMCartDemoAppDatabase(get())
    }

    single<ICheckoutOrderDataSource> {
        CheckoutOrderLocalDataSource(get())
    }

    single<IProductOrderDataSource>{
        ProductOrderLocalDataSource(get())
    }

    //Repo

    single {
        CategoryRepository(get())
    }

    single {
        CheckoutOrderRepository(get(), get())
    }

    // Use cases

    single<IAddToCart> {
        AddToCart(get())
    }
    single<IGetCategories>{
        GetCategories(get())
    }
    single<IGetCategoryProducts>{
        GetCategoryProducts(get(), get())
    }
    single<IGetCheckoutOrder>{
        GetCheckoutOrder(get(), get())
    }
    single<IGetProduct>{
        GetProduct(get())
    }
    single<IRemoveFromCart>{
        RemoveFromCart(get())
    }

    // View Model

}