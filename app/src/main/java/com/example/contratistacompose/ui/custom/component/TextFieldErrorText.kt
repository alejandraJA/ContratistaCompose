package com.example.contratistacompose.ui.custom.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.contratistacompose.utils.REQUIRED

@Composable
fun TextFieldErrorText(
    model: TextFieldModel,
    isRequiredAndEmpty: Boolean
) {
    Text(
        text =
        if (isRequiredAndEmpty) REQUIRED
        else model.externalError.value.ifEmpty { "" },
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(top = 8.dp)
    )
}