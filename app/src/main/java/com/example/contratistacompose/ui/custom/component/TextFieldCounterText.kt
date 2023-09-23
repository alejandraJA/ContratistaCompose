package com.example.contratistacompose.ui.custom.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldCounterText(
    field: TextFieldValue,
    counterNumber: Int
) {
    Text(
        text = "${field.text.length}/$counterNumber",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(top = 8.dp).alpha(0.5f)
    )
}