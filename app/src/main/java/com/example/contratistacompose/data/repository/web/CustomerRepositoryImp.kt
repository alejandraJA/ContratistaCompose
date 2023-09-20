package com.example.contratistacompose.data.repository.web


import com.example.contratistacompose.data.source.web.models.Customer
import com.example.contratistacompose.data.source.web.models.ResponseApi
import com.example.contratistacompose.data.source.web.retrofit.Service
import com.example.contratistacompose.domain.CustomerRepository
import retrofit2.Response
import javax.inject.Inject

class CustomerRepositoryImp @Inject constructor(private val service: Service) : CustomerRepository {
    override suspend fun getCustomer(token: String): Response<ResponseApi<List<Customer>>> =
            service.getCustomer(token)
}