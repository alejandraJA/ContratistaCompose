package com.example.contratistacompose.data.source.web.retrofit

import com.example.contratistacompose.data.source.web.models.Availability
import com.example.contratistacompose.data.source.web.models.Available
import com.example.contratistacompose.data.source.web.models.Budget
import com.example.contratistacompose.data.source.web.models.Customer
import com.example.contratistacompose.data.source.web.models.SingRequest
import com.example.contratistacompose.data.source.web.models.UpdateTokenRequest
import com.example.contratistacompose.data.source.web.models.Event
import com.example.contratistacompose.data.source.web.models.Part
import com.example.contratistacompose.data.source.web.models.ProductInventory
import com.example.contratistacompose.data.source.web.models.Reserved
import com.example.contratistacompose.data.source.web.models.ResponseApi
import com.example.contratistacompose.data.source.web.models.Token
import com.example.contratistacompose.data.source.web.models.User
import com.example.contratistacompose.utils.Constants.AUTHORIZATION
import retrofit2.Response
import retrofit2.http.*

interface Service {

    @GET("customer")
    suspend fun getCustomer(
        @Header(AUTHORIZATION) token: String
    ): Response<ResponseApi<List<Customer>>>

    @POST("sing/in")
    suspend fun singIn(@Body request: SingRequest): Response<ResponseApi<Token?>>

    @POST("sing/up")
    suspend fun singUp(@Body request: SingRequest): Response<ResponseApi<User>>

    @PUT("sing/updateToken")
    suspend fun updateToken(@Body request: UpdateTokenRequest): Response<ResponseApi<Token>>

    @GET("event")
    suspend fun getAllEvents(@Header(AUTHORIZATION) token: String): Response<ResponseApi<List<Event>>>

    @GET("product/{id}")
    suspend fun getByIdProduct(
        @Header(AUTHORIZATION) token: String,
        @Path("id") idProduct: String
    ): Response<ResponseApi<ProductInventory>>

    @GET("product/{id}/availability")
    suspend fun getAvailability(
        @Header(AUTHORIZATION) token: String,
        @Path("id") idProduct: String
    ): Response<ResponseApi<Availability>>

    @GET("product/available")
    suspend fun getAvailable(@Header(AUTHORIZATION) token: String): Response<ResponseApi<List<Available>>>

    @GET("product")
    suspend fun getAllProducts(@Header(AUTHORIZATION) token: String): Response<ResponseApi<List<ProductInventory>>>

    @GET("budget/{id}")
    suspend fun getBudgetById(
        @Header(AUTHORIZATION) token: String,
        @Path("id") id: String
    ): Response<ResponseApi<Budget>>

    @POST("budget/{id}/createPart")
    suspend fun createPart(
        @Header(AUTHORIZATION) token: String,
        @Path("id") idBudget: String
    ): Response<ResponseApi<List<Part>>>

    @PUT("reserved/{id}")
    suspend fun updateProductInPart(
        @Header(AUTHORIZATION) token: String,
        @Path("id") idReserved: String,
        @Query("idProduct") idProduct: String,
    ): Response<ResponseApi<Reserved>>

    @DELETE(("reserved/{idReserved}"))
    suspend fun deletePart(
        @Header(AUTHORIZATION) token: String,
        @Path("idReserved") idReserved: String
    ): Response<ResponseApi<Boolean>>


    @PUT("part/{idReserved}")
    suspend fun updatePart(
        @Header(AUTHORIZATION) token: String,
        @Path("idReserved") idReserved: String,
        @Query("quantity") quantity:Int,
        @Query("discount") discount: Double,
    ): Response<ResponseApi<Part>>

}