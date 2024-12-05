package com.shalenmathew.nexblock.crypto.presentation.coin_list


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shalenmathew.nexblock.crypto.presentation.coin_list.components.CoinListItem



@Composable
fun CoinListScreen(
    modifier: Modifier= Modifier,
    state:CoinListState,
    onAction:(CoinListActions)-> Unit
){
    if(state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    else {
        LazyColumn(modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)){
            items(state.listOfCoins){ coinUi->
                CoinListItem(coinUi = coinUi, onClick = {
                    onAction(CoinListActions.onClick(coinUi))
                })
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewList(){
//    CryptoTrackerTheme {
//       CoinListScreen(
//           state = CoinListState(isLoading = false,
//               listOfCoins = (1..10).map {
//                   previewCoin.toCoinUi()
//
//               })
//       )
//    }
//}

