package com.shalenmathew.nexblock.crypto.presentation.coin_list

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shalenmathew.nexblock.core.presentation.ObserveAsEvents
import com.shalenmathew.nexblock.crypto.presentation.coin_list.components.CoinListItem
import com.shalenmathew.nexblock.crypto.presentation.coin_list.components.previewCoin
import com.shalenmathew.nexblock.crypto.presentation.model.toCoinUi
import com.shalenmathew.nexblock.ui.theme.CryptoTrackerTheme


@Composable
fun CoinListScreen(
    modifier: Modifier= Modifier,
    state:CoinListState
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
            verticalArrangement = Arrangement.spacedBy(12.dp)){
            items(state.listOfCoins){ coinUi->
                CoinListItem(coinUi = coinUi, onClick = {})
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

