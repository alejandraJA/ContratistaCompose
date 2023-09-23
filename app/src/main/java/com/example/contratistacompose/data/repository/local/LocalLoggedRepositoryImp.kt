package com.example.contratistacompose.data.repository.local

import android.content.Context
import com.example.contratistacompose.data.source.local.UserLogged
import com.example.contratistacompose.data.source.web.models.UpdateTokenRequest
import com.example.contratistacompose.domain.UserLoggedRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalLoggedRepositoryImp @Inject constructor(@ApplicationContext context: Context) :
    UserLoggedRepository {

    private val _preferences =
        context.getSharedPreferences(
            LocalLoggedRepositoryImp::class.simpleName!!,
            Context.MODE_PRIVATE
        )

    private var _email: String
        get() = _preferences.getString(UserConstants.USERNAME, "")!!
        set(value) = this._preferences.edit()
            .putString(UserConstants.USERNAME, value).apply()


    private var _token: String
        get() = _preferences.getString("${_email}.${UserConstants.TOKEN}", "")!!
        set(value) = _preferences.edit()
            .putString("${_email}.${UserConstants.TOKEN}", value).apply()

    private var _password: String
        get() = _preferences.getString("$_email.${UserConstants.PASSWORD}", "")!!
        set(value) = _preferences.edit()
            .putString("$_email.${UserConstants.PASSWORD}", value).apply()

    override fun get() = UserLogged(email = _email, token = _token, password = _password)

    override fun singIn(userLogged: UserLogged) {
        _email = userLogged.email
        _token = userLogged.token
        _password = userLogged.password
    }

    override fun logout() = _preferences.edit()!!.clear().apply()

    override fun getToken() = _token

    override fun setToken(token: String) {
        this._token = token
    }

    override fun isUserLogged() =
        _token.isNotEmpty() && _email.isNotEmpty() && _password.isNotEmpty()

    override fun login(email: String, password: String): Boolean =
        this._email == email && this._password == password

    override fun getTokenRequest(): UpdateTokenRequest = UpdateTokenRequest(_email, _token)

}

object UserConstants {
    const val USERNAME = "username"
    const val PASSWORD = "password"
    const val TOKEN = "token"
}