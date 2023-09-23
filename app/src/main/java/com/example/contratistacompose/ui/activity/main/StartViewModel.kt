package com.example.contratistacompose.ui.activity.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contratistacompose.service.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val service: AuthenticationService
) : ViewModel() {

    val isLoggedUser = mutableStateOf(service.isUserLogged)
    private val updateToken = mutableStateOf(false)

    fun updateToken(onError: (String) -> Unit) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            service.updateToken({ updateToken.value = true }, onError)
        }
    }

    fun logout() {
        isLoggedUser.value = false
        service.logout()
    }
}