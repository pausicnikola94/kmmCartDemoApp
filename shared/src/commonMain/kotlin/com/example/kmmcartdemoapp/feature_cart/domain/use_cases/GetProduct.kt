package com.example.kmmcartdemoapp.feature_cart.domain.use_cases

import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.Product
import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.ProductOrder
import com.example.kmmcartdemoapp.feature_cart.domain.repository.ICheckoutOrderRepository
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IGetProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class GetProduct constructor(
    private val checkoutOrderRepository: ICheckoutOrderRepository
) : IGetProduct {

    override fun getProduct(product: Product): Flow<Product> {
        return checkoutOrderRepository.getProductOrder(product.id).transform {
            emit(createProductDetailsUiState(it, product))
        }
    }

    private fun createProductDetailsUiState(it: ProductOrder?, product: Product): Product {
        return product.copy(quantity = it?.quantity?.toInt() ?: product.quantity)
    }
}
