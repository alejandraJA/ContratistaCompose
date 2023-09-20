package com.example.contratistacompose.ui.custom.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.contratistacompose.R
import com.example.contratistacompose.utils.MoneyUtils.moneyFormat
import com.invoice.contratista.ui.theme.Alpha
import com.invoice.contratista.ui.theme.ModifierFieldImages
import com.invoice.contratista.ui.theme.ModifierFieldImagesSmall

@Composable
fun MoneyText(
    indicator: String = "",
    money: Double,
    factor: String = "",
    gain: Boolean = false,
    withholding: Boolean = false,
    modifier: Modifier = Modifier
) = Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
    val modifierIcon =
        if (factor == stringResource(id = R.string.exento) || gain) ModifierFieldImages.alpha(0.5f)
        else ModifierFieldImages
    if (factor.isNotEmpty() || indicator.isNotEmpty() || factor.isNotEmpty()) Row(
        modifier = Modifier.weight(
            1f
        ), verticalAlignment = Alignment.CenterVertically
    ) {
        if (factor.isNotEmpty()) Icon(
            painter = painterResource(
                if (withholding) R.drawable.remove else R.drawable.add
            ), contentDescription = "withholding", modifier = ModifierFieldImagesSmall.alpha(0.5f)
        )
        Spacer(modifier = Modifier.weight(1f))
        if (indicator.isNotEmpty()) Text(
            text = indicator,
            style = MaterialTheme.typography.bodySmall,
            modifier = if (factor == stringResource(id = R.string.exento) || gain) Alpha
            else Modifier
        )
        if (factor.isNotEmpty()) Text(
            text = factor,
            modifier = Alpha.padding(start = 4.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(R.drawable.money),
            contentDescription = "money",
            modifier = modifierIcon
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = money.moneyFormat(),
            modifier = if (factor == stringResource(id = R.string.exento) || gain) Alpha else Modifier
        )
    }
}