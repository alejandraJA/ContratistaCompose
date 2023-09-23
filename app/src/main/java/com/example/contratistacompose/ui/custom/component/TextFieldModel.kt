package com.example.contratistacompose.ui.custom.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.example.contratistacompose.R
import com.example.contratistacompose.utils.Constants
import com.example.contratistacompose.utils.INVALID_EMAIL
import com.example.contratistacompose.utils.IS_NOT_MONEY
import com.example.contratistacompose.utils.NO_LETTER_AND_SPECIAL_CHARACTER
import com.example.contratistacompose.utils.NO_NUMBER_AND_SPECIAL_CHARACTER
import com.example.contratistacompose.utils.NO_SPECIAL_CHARACTER
import com.example.contratistacompose.utils.REQUIRED
import com.example.contratistacompose.utils.TextValidation.isEmailValid
import com.example.contratistacompose.utils.TextValidation.isMoney
import com.example.contratistacompose.utils.TextValidation.isOnlyLettersAndNumber
import com.example.contratistacompose.utils.TextValidation.isOnlyNumber
import com.example.contratistacompose.utils.TextValidation.isOnlyText
import com.example.contratistacompose.utils.TextValidation.isPasswordValid

class TextFieldModel(
    val hint: String,
    val init: String = "",
    val trailingIcon: String = "",
    val placeholder: String = "",
    val icon: Int = R.drawable.high,
    val counterNumber: Int = 0,
    val format: Constants.Format,
    val isRequired: Boolean = false,
    val counterEnable: Boolean = false,
    val change: (String) -> Unit,
    val modifier: Modifier = Modifier.fillMaxWidth(),
    val externalError: MutableState<String> = mutableStateOf(""),
    val visualTransformation: VisualTransformation = VisualTransformation.None,
) {

    val passwordState: MutableState<Boolean> = mutableStateOf(false)
    fun validateTextInput(
        input: String,
        format: Constants.Format,
        required: Boolean,
    ): Boolean {
        val string = input.trim()
        if (required && string.isEmpty()) {
            externalError.value = REQUIRED
            return false
        }
        when (format) {
            Constants.Format.Money -> {
                if (!string.isMoney())
                    externalError.value = IS_NOT_MONEY
                return string.isMoney()
            }

            Constants.Format.Number -> {
                if (!string.isOnlyNumber())
                    externalError.value = NO_LETTER_AND_SPECIAL_CHARACTER
                return string.isOnlyNumber()
            }

            Constants.Format.Text -> {
                if (!string.isOnlyText())
                    externalError.value = NO_NUMBER_AND_SPECIAL_CHARACTER
                return string.isOnlyText()
            }

            Constants.Format.TextAndNumbers -> {
                if (!string.isOnlyLettersAndNumber())
                    externalError.value = NO_SPECIAL_CHARACTER
                return string.isOnlyLettersAndNumber()
            }

            Constants.Format.Email -> {
                if (!string.isEmailValid())
                    externalError.value = INVALID_EMAIL
                return string.isEmailValid()
            }

            Constants.Format.Password -> {
                val result = string.isPasswordValid(error = {
                    externalError.value = it
                })
                if (result) externalError.value = ""
                return result
            }

            Constants.Format.MultiCharacter -> {
                return true
            }
        }
    }
}