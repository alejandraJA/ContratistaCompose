package com.example.contratistacompose.ui.custom.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.contratistacompose.utils.ComposableFun
import com.example.contratistacompose.utils.Constants
import com.invoice.contratista.ui.theme.ModifierFill


@Composable
fun StatusContent(
    status: MutableState<StatusData>,
    content: ComposableFun,
) = Column {
    content()
    if (status.value.status == Constants.Status.Loading)
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
            },
            title = {
                Row(
                    modifier = ModifierFill,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(modifier = Modifier.width(30.dp))
                    Text(
                        "Loading...",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            },
            shape = RoundedCornerShape(20.dp),
        )

    if (status.value.status == Constants.Status.Failure)
        ErrorDialog(errorFromApi = status.value.error) {
            status.value = StatusData(Constants.Status.Success, "")
        }
}