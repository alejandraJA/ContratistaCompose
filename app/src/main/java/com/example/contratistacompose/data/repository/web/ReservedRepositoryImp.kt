package com.example.contratistacompose.data.repository.web

import com.example.contratistacompose.data.repository.web.utils.Resolve
import com.example.contratistacompose.data.repository.web.utils.WebStatus
import com.example.contratistacompose.data.source.web.models.Part
import com.example.contratistacompose.data.source.web.models.Reserved
import com.example.contratistacompose.data.source.web.retrofit.Service
import com.example.contratistacompose.domain.ReservedRepository
import javax.inject.Inject

class ReservedRepositoryImp @Inject constructor(private val service: Service) : ReservedRepository {
    override suspend fun updateProduct(
        token: String,
        idReserved: String,
        idProduct: String,
        webStatus: WebStatus<Reserved>
    ): Unit = Resolve(service.updateProductInPart(token, idReserved, idProduct), webStatus).invoke()

    override suspend fun deletePart(
        token: String,
        idReserved: String,
        webStatus: WebStatus<Boolean>
    ): Unit = Resolve(service.deletePart(token, idReserved), webStatus).invoke()

    override suspend fun updatePart(
        token: String,
        idReserved: String,
        quantity: Int,
        discount: Double,
        webStatus: WebStatus<Part>
    ): Unit = Resolve(service.updatePart(token, idReserved, quantity, discount), webStatus).invoke()


}