package com.example.contratistacompose.data.repository.web

import com.example.contratistacompose.data.repository.web.utils.Resolve
import com.example.contratistacompose.data.repository.web.utils.WebStatus
import com.example.contratistacompose.data.source.web.models.SingRequest
import com.example.contratistacompose.data.source.web.models.Token
import com.example.contratistacompose.data.source.web.models.UpdateTokenRequest
import com.example.contratistacompose.data.source.web.models.User
import com.example.contratistacompose.data.source.web.retrofit.Service
import com.example.contratistacompose.domain.SingRepository
import javax.inject.Inject

class SingRepositoryImp @Inject constructor(private val service: Service) : SingRepository {
    override suspend fun singIn(
        request: SingRequest,
        webStatus: WebStatus<Token?>
    ) = Resolve(service.singIn(request), webStatus).invoke()

    override suspend fun updateToken(
        request: UpdateTokenRequest,
        webStatus: WebStatus<Token>
    ) = Resolve(service.updateToken(request), webStatus).invoke()

    override suspend fun singUp(
        request: SingRequest,
        webStatus: WebStatus<User>
    ) = Resolve(service.singUp(request), webStatus).invoke()

}