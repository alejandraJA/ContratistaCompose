package com.example.contratistacompose.ui.activity.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contratistacompose.data.source.web.models.SingRequest
import com.example.contratistacompose.data.source.web.models.Token
import com.example.contratistacompose.data.source.web.models.User
import com.example.contratistacompose.service.AuthenticationService
import com.example.contratistacompose.ui.custom.component.StatusData
import com.example.contratistacompose.utils.Constants
import com.example.contratistacompose.utils.EMAIL_CANNOT_EQUALS_PASSWORD
import com.example.contratistacompose.utils.PASSWORD_NOT_MATCH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.reflect.KProperty

@HiltViewModel
class SingViewModel @Inject constructor(
    private val service: AuthenticationService
) : ViewModel() {
    lateinit var onLoggedUser: () -> Unit
    lateinit var auth: Constants.Authentication

    var email by mutableStateOf("Ale@email.com")
    var password by mutableStateOf("ale.-112233")
    var passwordConfirm by mutableStateOf("ale.-112233")
    var checkState by mutableStateOf(true)
    var errorPassword = mutableStateOf("")
    var errorEmail = mutableStateOf("")
    val statusData = MutableLiveData<StatusData>()

    private val onError: (String) -> Unit = { error ->
        statusData.postValue(StatusData(Constants.Status.Failure, error))
    }

    private val onSuccessLogin: (Token) -> Unit = {
        onSing()
    }

    private val onSuccessSingUp: (User) -> Unit = {
        onSing()
    }

    private fun onSing() {
        onLoggedUser.invoke()
        statusData.postValue(StatusData(Constants.Status.Success, ""))
    }

    val onEmailChange: (String) -> Unit = { change ->
        when (auth) {
            Constants.Authentication.SingIn -> {
                email = change
                errorPassword.value = ""
                errorEmail.value = ""
            }

            Constants.Authentication.SingUp -> {
                email = change
                errorEmail.value =
                    if (email == password)
                        EMAIL_CANNOT_EQUALS_PASSWORD
                    else ""
            }
        }
    }

    val onPasswordChange: (String) -> Unit = { change ->
        when (auth) {
            Constants.Authentication.SingIn -> {
                password = change
                errorPassword.value = ""
                errorEmail.value = ""
            }

            Constants.Authentication.SingUp -> {
                password = change
                errorEmail.value =
                    if (email == password)
                        EMAIL_CANNOT_EQUALS_PASSWORD
                    else ""
                errorPassword.value =
                    if (password != passwordConfirm)
                        PASSWORD_NOT_MATCH
                    else ""
            }
        }
    }

    val onPasswordConfirmChange: (String) -> Unit = { change ->
        passwordConfirm = change
        errorEmail.value =
            if (email == password)
                EMAIL_CANNOT_EQUALS_PASSWORD
            else ""
        errorPassword.value =
            if (password != passwordConfirm)
                PASSWORD_NOT_MATCH
            else ""
    }

    val onLogIn: () -> Unit = {
        login(email, password)
        errorPassword.value = ""
        errorEmail.value = ""
    }

    fun singUp(request: SingRequest) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            statusData.postValue(StatusData(Constants.Status.Loading, ""))
            service.singUp(request, onSuccessSingUp, onError)
        }
    }

    private fun login(email: String, password: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            statusData.postValue(StatusData(Constants.Status.Loading, ""))
            service.login(email, password, onSuccessLogin, onError)
        }
    }

    val onLostYourPass: () -> Unit = {
        println("Lost your pass")
    }
}
