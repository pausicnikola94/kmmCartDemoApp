package com.example.kmmcartdemoapp.android.presentation.navigation

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object ProductDetailsScreen: Screen("product_details_screen")
    object ShoppingCartScreen: Screen("shopping_cart_screen")
    object CategoryDetailsScreen: Screen("category_detail_screen")

}