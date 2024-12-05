package com.shalenmathew.nexblock.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shalenmathew.nexblock.crypto.presentation.model.CoinUi
import com.shalenmathew.nexblock.crypto.domain.model.Coin
import com.shalenmathew.nexblock.crypto.presentation.model.DisplayableNumber
import com.shalenmathew.nexblock.crypto.presentation.model.toCoinUi
import com.shalenmathew.nexblock.ui.theme.CryptoTrackerTheme
import com.shalenmathew.nexblock.ui.theme.DarkGreen
import com.shalenmathew.nexblock.ui.theme.LightGreen
import com.shalenmathew.nexblock.ui.theme.LightRed
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
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = yellowBackground)
            .padding(horizontal = 12.dp, vertical = 8.dp)
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
                fontSize = 18.sp,
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
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "$ ${coinUi.priceUsd.formatted}",
                color = Color.Black,
                fontSize = 18.sp,
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
    change: DisplayableNumber,
    modifier: Modifier = Modifier
) {
    val contentColor = if(change.value < 0.0) {
        Color.Red
    } else {
        DarkGreen
    }
    val backgroundColor = if(change.value < 0.0) {
       LightRed
    } else {
        LightGreen
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
            text = "${change.formatted} %",
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
            coinUi =previewCoin,
            onClick = {}
        )
    }
}

internal val previewCoin = Coin(id="1", rank = 1, name = "Bitcoin",
    symbol = "BTC", marketCapUsd = 123456789.0,
    priceUsd = 98000.0, changePercent24Hr =25.00).toCoinUi()


