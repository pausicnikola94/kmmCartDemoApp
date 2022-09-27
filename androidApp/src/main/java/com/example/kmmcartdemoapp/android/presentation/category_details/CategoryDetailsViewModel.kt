package com.example.kmmcartdemoapp.android.presentation.category_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmcartdemoapp.android.presentation.models.ProductDetails
import com.example.kmmcartdemoapp.android.presentation.models.ProductWrapper
import com.example.kmmcartdemoapp.android.presentation.models.toProduct
import com.example.kmmcartdemoapp.android.presentation.models.toProductDetails
import com.example.kmmcartdemoapp.core.util.Resource
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IAddToCart
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IGetCategoryProducts
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IRemoveFromCart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryDetailsViewModel constructor (
    private val getCategoryProducts: IGetCategoryProducts,
    private val addToCart: IAddToCart,
    private val removeFromCart: IRemoveFromCart
) :
    ViewModel() {

    private var _categoryUiState = MutableStateFlow<CategoryUiState>(CategoryUiState(isLoading = true))
    val categoryUiState: StateFlow<CategoryUiState> = _categoryUiState

    private var _job: Job? = null

    fun setup(categoryId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getCategoryProducts.getCategoryProducts(categoryId)
                .collect { result ->
                    when (result.status) {
                        Resource.Status.SUCCESS -> {
                            val productWrapperList = result.data?.map {
                                ProductWrapper.ProductMain(it.toProductDetails())
                            }
                            _categoryUiState.value = CategoryUiState(categoryId, productWrapperList)
                        }
                        Resource.Status.ERROR -> {
                            _categoryUiState.value = CategoryUiState(hasError = result.message)
                        }
                        Resource.Status.LOADING -> {
                            _categoryUiState.value = CategoryUiState(isLoading = true)
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
