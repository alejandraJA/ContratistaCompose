package com.example.contratistacompose.domain

import com.example.contratistacompose.data.repository.web.utils.WebStatus
import com.example.contratistacompose.data.source.web.models.Part
import com.example.contratistacompose.data.source.web.models.Reserved

interface ReservedRepository {
    suspend fun updateProduct(token: String, idReserved: String, idProduct: String, webStatus: WebStatus<Reserved>)
    suspend fun deletePart(token: String, idReserved: String, webStatus: WebStatus<Boolean>)

    suspend fun updatePart(token: String, idReserved: String, quantity: Int, discount: Double, webStatus: WebStatus<Part>)
}