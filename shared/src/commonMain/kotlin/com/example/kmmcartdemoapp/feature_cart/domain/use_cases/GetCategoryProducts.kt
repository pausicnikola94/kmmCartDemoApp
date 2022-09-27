package com.example.kmmcartdemoapp.feature_cart.domain.use_cases

import com.example.kmmcartdemoapp.core.util.Resource
import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.CheckoutOrder
import com.example.kmmcartdemoapp.feature_cart.domain.models.checkout.Product
import com.example.kmmcartdemoapp.feature_cart.domain.models.main.Category
import com.example.kmmcartdemoapp.feature_cart.domain.repository.ICategoryRepository
import com.example.kmmcartdemoapp.feature_cart.domain.repository.ICheckoutOrderRepository
import com.example.kmmcartdemoapp.feature_cart.domain.use_cases.interfaces.IGetCategoryProducts
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetCategoryProducts constructor(
    private val categoryRepository: ICategoryRepository,
    private val checkoutOrderRepository: ICheckoutOrderRepository
) : IGetCategoryProducts {
    override suspend fun getCategoryProducts(categoryId: String): Flow<Resource<List<Product>>> {
        val flowCategories = categoryRepository.getCategories() // TODO: get Category
        val flowCheckoutOrder = checkoutOrderRepository.getCheckoutOrder()

        return flowCategories.combine(flowCheckoutOrder) { categories, checkoutOrder ->
            getProducts(categories, checkoutOrder, categoryId)
        }
    }

    private fun getProducts(
        result: Resource<List<Category>>,
        checkoutOrder: CheckoutOrder?,
        categoryId: String
    ): Resource<List<Product>> {
        when (result.status) {
            Resource.Status.SUCCESS -> {
                if (result.data == null) return Resource.error(result.message ?: "No Data")

                val category = result.data.firstOrNull() {
                    it.id == categoryId
                }
                var productsUpdated = listOf<Product>()
                category.let { cat ->
                    productsUpdated = cat?.products?.map { catProduct ->
                        catProduct.copy(quantity = checkoutOrder?.products?.find { it.id == catProduct.id }?.quantity?.toInt() ?: 0)
                    } ?: listOf()
                }

                return Resource.success(productsUpdated)
            }
            Resource.Status.ERROR -> {
                return Resource.error(result.message ?: "")
            }
            Resource.Status.LOADING -> {
                return Resource.loading()
            }
        }
    }
}
