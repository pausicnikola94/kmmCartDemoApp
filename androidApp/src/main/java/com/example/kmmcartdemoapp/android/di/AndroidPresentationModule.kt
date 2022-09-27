package com.example.kmmcartdemoapp.android.di


import com.example.kmmcartdemoapp.android.presentation.category_details.CategoryDetailsViewModel
import com.example.kmmcartdemoapp.android.presentation.main.MainViewModel
import com.example.kmmcartdemoapp.android.presentation.product_details.ProductDetailsViewModel
import com.example.kmmcartdemoapp.android.presentation.shopping_cart.ShoppingCartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidPresentationModule = module {
    viewModel{
        CategoryDetailsViewModel(get(), get(), get())
    }
    viewModel {
        MainViewModel(get(), get())
    }
    viewModel {
        ProductDetailsViewModel(get(), get(), get())
    }
    viewModel {
        ShoppingCartViewModel(get(), get(), get())
    }
}