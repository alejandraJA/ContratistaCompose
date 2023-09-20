package com.example.contratistacompose.domain

import com.example.contratistacompose.data.source.web.models.Customer
import com.example.contratistacompose.data.source.web.models.ResponseApi
import retrofit2.Response

interface CustomerRepository {
    suspend fun getCustomer(token: String): Response<ResponseApi<List<Customer>>>
}