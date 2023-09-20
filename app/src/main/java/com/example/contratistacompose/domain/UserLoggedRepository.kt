package com.example.contratistacompose.domain

import com.example.contratistacompose.data.source.local.UserLogged
import com.example.contratistacompose.data.source.web.models.UpdateTokenRequest

interface UserLoggedRepository {
    fun get(): UserLogged?
    fun singIn(userLogged: UserLogged)
    fun logout()
    fun getToken(): String?
    fun setToken(token: String)
    fun isUserLogged(): Boolean
    fun login(email: String, password: String): Boolean
    fun getTokenRequest(): UpdateTokenRequest
}