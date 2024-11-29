package com.shalenmathew.nexblock.crypto.presentation.coin_list.components

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shalenmathew.nexblock.crypto.presentation.model.CoinUi
import  com.shalenmathew.nexblock.R
import com.shalenmathew.nexblock.crypto.domain.model.Coin
import com.shalenmathew.nexblock.crypto.presentation.model.DisplayedNumbers
import com.shalenmathew.nexblock.crypto.presentation.model.toCoinUi
import com.shalenmathew.nexblock.ui.theme.CryptoTrackerTheme
import com.shalenmathew.nexblock.ui.theme.greenBackground
import com.shalenmathew.nexblock.ui.theme.osFontFamily
import com.shalenmathew.nexblock.ui.theme.yellowBackground


@Composable
fun CoinListItem(
    coinUi: CoinUi,
    onClick:() -> Unit,
    modifier: Modifier= Modifier
) {

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = yellowBackground)
            .padding(8.dp)
            .clickable {
            onClick()
        },
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(modifier = Modifier.size(70.dp),
            imageVector = ImageVector.vectorResource(id = coinUi.iconRes),
            contentDescription = "icon",
            tint =Color.Black
        )


        // name n symbol
        Column( modifier = Modifier.weight(1f)) {
            Text(
                text = coinUi.name,
                color = Color.Black,
                fontSize = 20.sp,
               fontFamily = osFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = coinUi.symbol,
                color = Color.Black,
                fontSize = 15.sp,
                fontFamily = osFontFamily,
                fontWeight = FontWeight.Light
            )

        }


        // price n change
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$ ${coinUi.priceUsd.displayValue}",
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 12.dp),
                fontFamily = osFontFamily,
                fontWeight = FontWeight.Bold

            )

            PriceChange(change = coinUi.changePercent24Hr )
        }

    }
}



@Composable
fun PriceChange(
    change: DisplayedNumbers,
    modifier: Modifier = Modifier
) {
    val contentColor = if(change.value < 0.0) {
        MaterialTheme.colorScheme.onErrorContainer
    } else {
        Color.Green
    }
    val backgroundColor = if(change.value < 0.0) {
        MaterialTheme.colorScheme.errorContainer
    } else {
        greenBackground
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100f))
            .background(backgroundColor)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if(change.value < 0.0) {
                Icons.Default.KeyboardArrowDown
            } else {
                Icons.Default.KeyboardArrowUp
            },
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = contentColor
        )
        Text(
            text = "${change.displayValue} %",
            color = contentColor,
            fontSize = 14.sp,
            fontFamily = osFontFamily,
            fontWeight = FontWeight.Light
        )
    }
}



@Preview
@Composable
fun CoinListItemPreview(){
    CryptoTrackerTheme {
        CoinListItem(
            coinUi =previewCoin.toCoinUi(),
            onClick = {}
        )
    }
}

internal val previewCoin = Coin(id="1", rank = 1, name = "Bitcoin", symbol = "BTC",
    marketCapUsd = 123456789.0, priceUsd = 98000.0, changePercent24Hr = 25.00)


