package com.example.contratistacompose.domain

import com.example.contratistacompose.data.repository.web.utils.WebStatus
import com.example.contratistacompose.data.source.web.models.Availability
import com.example.contratistacompose.data.source.web.models.Available
import com.example.contratistacompose.data.source.web.models.ProductInventory

interface ProductRepository {
    suspend fun getByIdProduct(
        token: String,
        idProduct: String,
        webStatus: WebStatus<ProductInventory>
    )

    suspend fun getAvailability(
        token: String,
        idProduct: String, webStatus: WebStatus<Availability>
    )

    suspend fun getAvailable(token: String, webStatus: WebStatus<List<Available>>)

    suspend fun getAll(token: String, webStatus: WebStatus<List<ProductInventory>>)
}