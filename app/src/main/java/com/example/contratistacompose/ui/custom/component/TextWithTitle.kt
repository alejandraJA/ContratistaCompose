package com.example.contratistacompose.ui.custom.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.contratistacompose.R
import com.invoice.contratista.ui.theme.Alpha
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.ui.theme.ModifierFieldImagesSmall
import com.example.contratistacompose.utils.Constants
import com.example.contratistacompose.utils.MONEY
import com.example.contratistacompose.utils.MoneyUtils.moneyFormat
import com.example.contratistacompose.utils.TextValidation.toOnlyNumbers

@Composable
fun TextWithTitle(
    title: String,
    text: String? = null,
    modifier: Modifier = Modifier,
    iconResource: Int? = null,
    format: Constants.Format = Constants.Format.Text,
    doubleMutableState: MutableState<Double>? = null,
) = Column(modifier = modifier) {
    Text(text = title, modifier = Alpha, style = MaterialTheme.typography.bodySmall)
    Row(verticalAlignment = Alignment.CenterVertically) {
        val textFormat: String =
            if (doubleMutableState != null) {
                when (format) {
                    Constants.Format.Text -> doubleMutableState.value.toString()
                    Constants.Format.Money -> doubleMutableState.value.moneyFormat()
                    Constants.Format.Number -> doubleMutableState.value.toInt().toString()
                    else -> doubleMutableState.value.toString()
                }
            } else {
                when (format) {
                    Constants.Format.Text -> text!!
                    Constants.Format.Money -> text!!.toDouble().moneyFormat()
                    Constants.Format.Number -> text!!.toOnlyNumbers().toInt().toString()
                    else -> text!!
                }
            }
        if (iconResource != null || format == Constants.Format.Money) Icon(
            painter = painterResource(if (format == Constants.Format.Money) R.drawable.money else iconResource!!),
            modifier = if (format == Constants.Format.Money) ModifierFieldImages else ModifierFieldImagesSmall,
            contentDescription = if (format == Constants.Format.Money) MONEY else ""
        )
        if (format == Constants.Format.Money)
            Spacer(modifier = Modifier.weight(1f))
        Text(text = textFormat)
    }
}

@Composable
fun TextWithTitle(
    title: String,
    text: String? = null,
    modifier: Modifier = Modifier,
    iconResource: Int = 0,
    format: Constants.Format = Constants.Format.Text,
    doubleMutableState: MutableState<Double>? = null,
) = Column(modifier = modifier) {
    Text(text = title, modifier = Alpha, style = MaterialTheme.typography.bodySmall)
    Row(verticalAlignment = Alignment.CenterVertically) {
        val textFormat: String =
            if (doubleMutableState != null) {
                when (format) {
                    Constants.Format.Text -> doubleMutableState.value.toString()
                    Constants.Format.Money -> doubleMutableState.value.moneyFormat()
                    Constants.Format.Number -> doubleMutableState.value.toInt().toString()
                    else -> doubleMutableState.value.toString()
                }
            } else {
                when (format) {
                    Constants.Format.Text -> text!!
                    Constants.Format.Money -> text!!.toDouble().moneyFormat()
                    Constants.Format.Number -> text!!.toOnlyNumbers().toInt().toString()
                    else -> text!!
                }
            }
        if (iconResource != 0 || format == Constants.Format.Money) Icon(
            painter = painterResource(if (format == Constants.Format.Money) R.drawable.money else iconResource),
            modifier = if (format == Constants.Format.Money) ModifierFieldImages else ModifierFieldImagesSmall,
            contentDescription = "icon"
        )
        if (format == Constants.Format.Money)
            Spacer(modifier = Modifier.weight(1f))
        Text(text = textFormat)
    }
}