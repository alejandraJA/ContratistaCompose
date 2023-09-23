package com.example.contratistacompose.service

import com.example.contratistacompose.data.repository.web.utils.WebStatus
import com.example.contratistacompose.data.source.local.UserLogged

open class SuperService(private val userService: UserService) {
    val isUserLogged: Boolean
        get() = userService.isLoggedUser()
    var token: String?
        get() = userService.token
        set(value) {
            userService.token = value ?: ""
        }

    fun <T> getWebStatus(
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit
    ) = object : WebStatus<T> {
        override fun success(data: T) {
            if (data is List<*>) {
                if (data.isNotEmpty()) onSuccess.invoke(data)
                else onError.invoke("Empty list")
                return
            }
            onSuccess.invoke(data)
        }

        override fun error(e: String) {
            onError.invoke(e)
        }
    }
}