package com.example.contratistacompose.service

import com.example.contratistacompose.data.source.web.models.Availability
import com.example.contratistacompose.data.source.web.models.ProductInventory
import com.example.contratistacompose.domain.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductService @Inject constructor(
    private val repository: ProductRepository,
    userService: UserService
): SuperService(userService) {

    suspend fun findByProductId(
        productId: String,
        onSuccess: (ProductInventory) -> Unit,
        onError: (String) -> Unit,
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) {
            repository.getByIdProduct(token!!, productId, getWebStatus(onSuccess, onError))
        }
    }

    suspend fun getAll(
        onSuccess: (List<ProductInventory>) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) {
            repository.getAll(token!!, getWebStatus(onSuccess, onError))
        }
    }

    suspend fun getAvailability(
        idProduct: String,
        onSuccess: (Availability) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) {
            repository.getAvailability(token!!, idProduct, getWebStatus(onSuccess, onError))
        }
    }


}