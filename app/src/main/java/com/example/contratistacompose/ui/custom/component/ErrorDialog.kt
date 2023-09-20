package com.example.contratistacompose.ui.custom.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contratistacompose.R
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.ui.theme.ModifierFill

@Composable
fun ErrorDialog(errorFromApi: String, onDismiss: () -> Unit) {
    if (errorFromApi.isNotEmpty()) AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Accept")
            }
        },
        title = {
            Row(
                modifier = ModifierFill,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.bug),
                    contentDescription = "bug",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = ModifierFieldImages
                )
                Text(text = "Ups!", style = MaterialTheme.typography.titleMedium)
            }
        },
        text = {
            Text(text = errorFromApi, style = MaterialTheme.typography.bodyMedium, modifier = ModifierFill)
        },
        shape = RoundedCornerShape(20.dp),
//        backgroundColor = MaterialTheme.colorScheme.background,
//        contentColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.width(300.dp)
    )
}

@Preview
@Composable
fun Preview() {
    val hello by rememberSaveable {
        mutableStateOf("hola")
    }
    ErrorDialog(errorFromApi = hello) {

    }
}