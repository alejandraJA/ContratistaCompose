package com.example.contratistacompose.data.repository.web

import com.example.contratistacompose.data.repository.web.utils.Resolve
import com.example.contratistacompose.data.repository.web.utils.WebStatus
import com.example.contratistacompose.data.source.web.models.Availability
import com.example.contratistacompose.data.source.web.models.Available
import com.example.contratistacompose.data.source.web.models.ProductInventory
import com.example.contratistacompose.data.source.web.retrofit.Service
import com.example.contratistacompose.domain.ProductRepository
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(private val service: Service) : ProductRepository {
    override suspend fun getByIdProduct(
        token: String,
        idProduct: String,
        webStatus: WebStatus<ProductInventory>
    ): Unit = Resolve(service.getByIdProduct(token, idProduct), webStatus).invoke()

    override suspend fun getAvailability(token: String, idProduct: String, webStatus: WebStatus<Availability>) =
        Resolve(service.getAvailability(token, idProduct), webStatus).invoke()

    override suspend fun getAvailable(token: String, webStatus: WebStatus<List<Available>>) =
        Resolve(service.getAvailable(token), webStatus).invoke()

    override suspend fun getAll(token: String, webStatus: WebStatus<List<ProductInventory>>) =
        Resolve(service.getAllProducts(token), webStatus).invoke()
}