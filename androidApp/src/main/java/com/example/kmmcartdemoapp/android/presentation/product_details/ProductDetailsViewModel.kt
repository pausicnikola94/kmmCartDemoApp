package com.example.kmmcartdemoapp.android.presentation.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmcartdemoapp.android.presentation.models.ProductDetails
import com.example.kmmcartdemoapp.android.presentation.models.toProduct
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IAddToCart
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IGetProduct
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IRemoveFromCart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel constructor (
    private val addToCart: IAddToCart,
    private val removeFromCart: IRemoveFromCart,
    private val getProduct: IGetProduct
) :
    ViewModel() {

    private var _productDetailsUiState = MutableStateFlow<ProductDetailsUiState>(
        ProductDetailsUiState()
    )
    val productDetailsUiState: StateFlow<ProductDetailsUiState> = _productDetailsUiState

    private var _job: Job? = null

    fun setup(productDetails: ProductDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            getProduct.getProduct(productDetails.toProduct()).collect {
                _productDetailsUiState.value = ProductDetailsUiState(it)
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
