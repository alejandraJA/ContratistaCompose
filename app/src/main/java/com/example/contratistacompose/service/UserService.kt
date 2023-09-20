package com.example.contratistacompose.service

import com.example.contratistacompose.data.source.local.UserLogged
import com.example.contratistacompose.data.source.web.models.UpdateTokenRequest
import com.example.contratistacompose.domain.UserLoggedRepository
import javax.inject.Inject

class UserService @Inject constructor(private val repository: UserLoggedRepository) {

    fun getUpdateTokenRequest(): UpdateTokenRequest = repository.getTokenRequest()

    var token: String?
        get() = repository.getToken()
        set(value) = repository.setToken(value ?: "")

    fun isLoggedUser(): Boolean = repository.isUserLogged()

    fun login(email: String, password: String) = repository.login(email, password)
    fun singIn(userLogged: UserLogged) = repository.singIn(userLogged)
    fun logout() = repository.logout()
}