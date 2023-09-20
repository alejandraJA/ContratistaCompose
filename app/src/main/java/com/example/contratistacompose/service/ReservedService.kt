package com.example.contratistacompose.service

import com.example.contratistacompose.data.source.web.models.Part
import com.example.contratistacompose.data.source.web.models.Reserved
import com.example.contratistacompose.domain.ReservedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReservedService @Inject constructor(
    private val repository: ReservedRepository,
    userService: UserService
) : SuperService(userService) {

    suspend fun updateProduct(
        idReserved: String,
        idProduct: String,
        onSuccess: (Reserved) -> Unit,
        onError: (String) -> Unit,
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) {
            repository.updateProduct(token!!, idReserved, idProduct, getWebStatus(onSuccess, onError))
        }
    }

    suspend fun deletePart(
        id: String,
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) {
            repository.deletePart(token!!, id, getWebStatus(onSuccess, onError))
        }
    }

    suspend fun updatePart(
        idReserved: String,
        quantity: Int,
        discount: Double,
        onSuccess: (Part) -> Unit,
        onError: (String) -> Unit,
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) repository.updatePart(
            token!!,
            idReserved,
            quantity,
            discount,
            getWebStatus(onSuccess, onError)
        )
    }

}