package com.example.kmmcartdemoapp.android.presentation.shopping_cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmcartdemoapp.android.presentation.models.ProductDetails
import com.example.kmmcartdemoapp.android.presentation.models.ProductWrapper
import com.example.kmmcartdemoapp.android.presentation.models.toProduct
import com.example.kmmcartdemoapp.android.presentation.models.toProductDetails
import com.example.kmmcartdemoapp.core.util.Resource
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IAddToCart
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IGetCheckoutOrder
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IRemoveFromCart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShoppingCartViewModel constructor (
    private val getCheckoutOrder: IGetCheckoutOrder,
    private val addToCart: IAddToCart,
    private val removeFromCart: IRemoveFromCart
) :
    ViewModel() {

    private var _shoppingCartUiState = MutableStateFlow<ShoppingCartUiState>(ShoppingCartUiState())
    val shoppingCartUiState: StateFlow<ShoppingCartUiState> = _shoppingCartUiState

    private var _job: Job? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getCheckoutOrder.init()
            getCheckoutOrder.getCheckoutOrder()
                .collect { productsResult ->
                    when (productsResult.status) {
                        Resource.Status.SUCCESS -> {
                            val productWrapperList = productsResult.data?.map {
                                ProductWrapper.ProductCart(it.toProductDetails())
                            }
                            _shoppingCartUiState.value = ShoppingCartUiState(productWrapperList)
                        }
                        Resource.Status.ERROR -> {
                            _shoppingCartUiState.value = ShoppingCartUiState(hasError = productsResult.message)
                        }
                        Resource.Status.LOADING -> {
                            _shoppingCartUiState.value = ShoppingCartUiState(isLoading = true)
                        }
                    }
                }
        }
    }

    fun addToCart(productDetails: ProductDetails) {
        _job?.cancel()
        _job = viewModelScope.launch(Dispatchers.IO) {
            addToCart.addProduct(productDetails.toProduct(), 0)
        }
    }

    fun removeFromCart(productDetails: ProductDetails) {
        _job?.cancel()
        _job = viewModelScope.launch(Dispatchers.IO) {
            removeFromCart.removeProduct(productDetails.toProduct(), 0)
        }
    }
}
