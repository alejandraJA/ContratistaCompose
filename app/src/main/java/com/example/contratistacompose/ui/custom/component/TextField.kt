package com.example.contratistacompose.ui.custom.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import com.example.contratistacompose.R
import com.example.contratistacompose.utils.Constants
import com.invoice.contratista.ui.theme.ModifierFieldImages

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun TextField(model: TextFieldModel) = Column(modifier = model.modifier) {
    var field by remember { mutableStateOf(TextFieldValue(model.init)) }
    val isFieldEmpty = field.text.isEmpty()
    val hasError = model.externalError.value.isNotEmpty()
    val isRequiredAndEmpty = model.isRequired && isFieldEmpty
    val isError = isRequiredAndEmpty || hasError

    OutlinedTextField(
        value = field,
        onValueChange = { newValue ->
            field = newValue
            if (model.validateTextInput(newValue.text, model.format, model.isRequired))
                model.change.invoke(newValue.text)
        },
        label = {
            val labelHint = "${model.hint}${if (model.isRequired) "*" else ""}"
            Text(text = labelHint)
        },
        placeholder = model.placeholder.takeIf { it.isNotEmpty() }?.let {
            { Text(text = it) }
        },
        isError = isError,
        leadingIcon =
        {
            Icon(
                painter = painterResource(model.icon),
                contentDescription = "${model.hint} Icon",
                tint = if (isError) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.surfaceVariant,
                modifier = ModifierFieldImages
            )
        },
        trailingIcon = if (model.trailingIcon.isNotEmpty() && !(model.isRequired && isFieldEmpty)) {
            {
                if (model.format != Constants.Format.Password) Icon(
                    painter = painterResource(R.drawable.high),
                    contentDescription = "Error $model.hint",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = ModifierFieldImages
                ) else {
                    Icon(
                        painter = painterResource(R.drawable.high),
                        contentDescription = "Error $model.hint",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = ModifierFieldImages
                    )
                }
            }
        } else if (model.isRequired && isFieldEmpty) {
            {
                if (model.passwordState.value)
                    Icon(
                        painter = painterResource(R.drawable.visibility),
                        contentDescription = "visibility",
                        modifier = ModifierFieldImages.clickable {
                            model.passwordState.value = !model.passwordState.value
                        }
                    )
                else
                    Icon(
                        painter = painterResource(R.drawable.visibility_off),
                        contentDescription = "visibility_off",
                        modifier = ModifierFieldImages.clickable {
                            model.passwordState.value = !model.passwordState.value
                        }
                    )
            }
        } else null,
        visualTransformation = model.visualTransformation,
        modifier = model.modifier
    )

    Row {
        if (model.externalError.value.isNotEmpty())
            TextFieldErrorText(model = model, isRequiredAndEmpty = isRequiredAndEmpty)
        if (model.externalError.value.isNotEmpty() || model.counterEnable)
            Spacer(Modifier.weight(1f))
        if (model.counterEnable)
            TextFieldCounterText(field = field, counterNumber = model.counterNumber)
    }
}