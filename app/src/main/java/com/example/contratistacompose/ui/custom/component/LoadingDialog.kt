package com.example.contratistacompose.ui.custom.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contratistacompose.ui.theme.AppTheme
import com.invoice.contratista.ui.theme.ModifierFill

@Composable
fun LoadingDialog(show: Boolean, onDismiss: () -> Unit) {
    if (show)
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
            },
            title = {
                Row(
                    modifier = ModifierFill,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(modifier = Modifier.width(30.dp))
                    Text("Loading...", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(start = 8.dp))
                }
            },
            shape = RoundedCornerShape(20.dp),
        )
}

@Preview
@Composable
fun PreviewError() = AppTheme(true){
    LoadingDialog(show = true) {

    }
}