package com.example.contratistacompose.ui.activity.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.ViewModelProvider
import com.example.contratistacompose.R
import com.example.contratistacompose.data.source.web.models.SingRequest
import com.example.contratistacompose.ui.custom.component.StatusContent
import com.example.contratistacompose.ui.custom.component.StatusData
import com.example.contratistacompose.ui.custom.component.TextField
import com.example.contratistacompose.ui.custom.component.TextFieldModel
import com.example.contratistacompose.ui.theme.AppTheme
import com.example.contratistacompose.utils.ComposableFun
import com.example.contratistacompose.utils.Constants
import com.invoice.contratista.ui.theme.ModifierFill
import com.invoice.contratista.ui.theme.ModifierPaddingScreen
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class StartActivity : ComponentActivity() {

    private lateinit var viewModel: StartViewModel
    private lateinit var loginViewModel: SingViewModel
    private lateinit var singInViewModel: SingViewModel
    private var status by mutableStateOf(StatusData(Constants.Status.Success, ""))

    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[StartViewModel::class.java]
        loginViewModel = ViewModelProvider(this)[SingViewModel::class.java]
        singInViewModel = ViewModelProvider(this)[SingViewModel::class.java]

        setConfigurations()

        setObserver()
        setContent {
            AppTheme(isSystemInDarkTheme()) {
                StatusContent(status = mutableStateOf(this.status)) {
                    if (viewModel.isLoggedUser.value) {
                        viewModel.updateToken {
                            status = StatusData(Constants.Status.Failure, it)
                        }
                    } else
                        AuthenticationScreen()
                }
            }
        }
    }

    private fun setConfigurations() {
        loginViewModel.onLoggedUser = onLoggedUser()
        loginViewModel.auth = Constants.Authentication.SingUp
        singInViewModel.onLoggedUser = onLoggedUser()
        singInViewModel.auth = Constants.Authentication.SingUp
    }

    private fun setObserver() {
        val changeStatus = { it: StatusData -> status = it }
        loginViewModel.statusData.observe(this, changeStatus)
        singInViewModel.statusData.observe(this, changeStatus)
    }

    private fun onLoggedUser(): () -> Unit = {
        viewModel.isLoggedUser.value = true
    }

    // region Composable Fun
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun AuthenticationScreen() = Column(modifier = ModifierPaddingScreen) {

        val tabs: List<ComposableFun> = listOf(
            { ElevatedCard { SetLogin() } },
            { ElevatedCard { SetSingIn() } },
        )

        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f
        ) { tabs.size }

        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxHeight()) {
            tabs[it]()
        }
    }

    @ExperimentalFoundationApi
    @Composable
    private fun SetLogin() = Column(
        modifier = ModifierPaddingScreen,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login now!", style = MaterialTheme.typography.titleLarge)
        TextField(
            TextFieldModel(
                hint = "Email",
                change = loginViewModel.onEmailChange,
                placeholder = "Type your email",
                init = loginViewModel.email,
                icon = R.drawable.mail,
                isRequired = true,
                externalError = loginViewModel.errorEmail,
                format = Constants.Format.Email
            )
        )
        TextField(
            TextFieldModel(
                hint = "Password",
                change = loginViewModel.onPasswordChange,
                placeholder = "Type your Password",
                init = loginViewModel.password,
                icon = R.drawable.password,
                isRequired = true,
                visualTransformation = PasswordVisualTransformation(),
                externalError = loginViewModel.errorPassword,
                format = Constants.Format.Password
            )
        )
        Row {
            TextButton(onClick = loginViewModel.onLostYourPass) {
                Text("Lost your password?")
            }
            Spacer(Modifier.weight(1f))
            Button(onClick = loginViewModel.onLogIn) {
                Text("Login")
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun SetSingIn() = Column(
        modifier = ModifierPaddingScreen,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Create an account", style = MaterialTheme.typography.titleLarge)
        // region Field Email
        TextField(
            TextFieldModel(
                hint = "Email",
                init = singInViewModel.email,
                placeholder = "Type your email",
                icon = R.drawable.mail,
                format = Constants.Format.Email,
                isRequired = true,
                change = singInViewModel.onEmailChange,
                externalError = singInViewModel.errorEmail,
            )
        )
        // endregion
        // region Field Password
        TextField(
            TextFieldModel(
                hint = "Password",
                init = singInViewModel.password,
                placeholder = "Type your Password",
                icon = R.drawable.password,
                format = Constants.Format.Password,
                isRequired = true,
                change = singInViewModel.onPasswordChange,
                externalError = singInViewModel.errorPassword,
                visualTransformation = PasswordVisualTransformation()
            )
        )
        // endregion
        // region Field Confirm Password
        TextField(
            TextFieldModel(
                hint = "Password",
                init = singInViewModel.passwordConfirm,
                placeholder = "Confirm Password",
                icon = R.drawable.password,
                format = Constants.Format.Password,
                isRequired = true,
                change = singInViewModel.onPasswordConfirmChange,
                externalError = singInViewModel.errorPassword,
                visualTransformation = PasswordVisualTransformation()
            )
        )
        // endregion
        // region Check terms
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = singInViewModel.checkState,
                onCheckedChange = {
                    singInViewModel.checkState = it
                }
            )
            Text(text = "I agree with privacy policy")
        }
        // endregion
        // region Button SingUp
        Button(
            onClick = {
                singInViewModel.singUp(
                    SingRequest(
                        singInViewModel.email,
                        singInViewModel.password
                    )
                )
            },
            modifier = ModifierFill,
            enabled = singInViewModel.checkState && (singInViewModel.email.isNotEmpty()
                    && singInViewModel.password.isNotEmpty()
                    && singInViewModel.passwordConfirm.isNotEmpty())
                    && (singInViewModel.password == singInViewModel.passwordConfirm
                    && singInViewModel.email != singInViewModel.password)
        ) {
            Text("Sing Up")
        }
        // endregion
        // endregion
    }
    // endregion

}
