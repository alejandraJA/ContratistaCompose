package com.example.contratistacompose.domain

import com.example.contratistacompose.data.repository.web.utils.WebStatus
import com.example.contratistacompose.data.source.web.models.SingRequest
import com.example.contratistacompose.data.source.web.models.Token
import com.example.contratistacompose.data.source.web.models.UpdateTokenRequest
import com.example.contratistacompose.data.source.web.models.User

interface SingRepository {
    suspend fun singIn(request: SingRequest, webStatus: WebStatus<Token?>)
    suspend fun updateToken(request: UpdateTokenRequest, webStatus: WebStatus<Token>)
    suspend fun singUp(request: SingRequest, webStatus: WebStatus<User>)

}