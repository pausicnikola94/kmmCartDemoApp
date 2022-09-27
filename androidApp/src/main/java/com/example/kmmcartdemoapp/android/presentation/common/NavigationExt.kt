package com.example.kmmcartdemoapp.android.presentation.common

import androidx.navigation.NavController
import com.example.kmmcartdemoapp.android.presentation.navigation.Screen

fun <T> NavController.navigateCustom(screen: Screen, data: T){
    when(screen){
        is Screen.ProductDetailsScreen -> {
            this.currentBackStackEntry?.savedStateHandle?.set(
                "product",
                data
            )
            this.navigate(Screen.ProductDetailsScreen.route)
        }
        is Screen.CategoryDetailsScreen -> {
            this.navigate(Screen.CategoryDetailsScreen.route + "/${data}")
        }
        else -> {}
    }
}