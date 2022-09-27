package com.example.kmmcartdemoapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kmmcartdemoapp.Greeting
import com.example.kmmcartdemoapp.android.presentation.category_details.CategoryDetailsScreen
import com.example.kmmcartdemoapp.android.presentation.category_details.CategoryDetailsViewModel
import com.example.kmmcartdemoapp.android.presentation.main.MainScreen
import com.example.kmmcartdemoapp.android.presentation.main.MainViewModel
import com.example.kmmcartdemoapp.android.presentation.models.ProductDetails
import com.example.kmmcartdemoapp.android.presentation.navigation.Screen
import com.example.kmmcartdemoapp.android.presentation.product_details.ProductDetailsScreen
import com.example.kmmcartdemoapp.android.presentation.product_details.ProductDetailsViewModel
import com.example.kmmcartdemoapp.android.presentation.shopping_cart.ShoppingCartScreen
import com.example.kmmcartdemoapp.android.presentation.shopping_cart.ShoppingCartViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColors(
            primary = Color(0xFF6200EE),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val mainViewModel = getViewModel<MainViewModel>()
            MyApplicationTheme {
                Scaffold(
                    topBar = {
                        com.example.kmmcartdemoapp.android.presentation.common.TopAppBar(navController = navController,
                            mainViewModel)
                    }
                ){
                    NavHost(navController, Screen.MainScreen.route) {
                        composable(Screen.MainScreen.route) {
                            MainScreen(navController, mainViewModel)
                        }
                        composable(Screen.ProductDetailsScreen.route) {
                            val viewModel = getViewModel<ProductDetailsViewModel>()
                            val result = navController.previousBackStackEntry?.savedStateHandle?.get<ProductDetails>("product")
                            ProductDetailsScreen(viewModel, result)
                        }
                        composable(Screen.ShoppingCartScreen.route) {
                            val viewModel = getViewModel<ShoppingCartViewModel>()
                            ShoppingCartScreen(navController, viewModel)
                        }
                        composable(Screen.CategoryDetailsScreen.route + "/{categoryId}") {
                            val viewModel = getViewModel<CategoryDetailsViewModel>()
                            CategoryDetailsScreen(navController, viewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Hello, Android!")
    }
}
