package com.shalenmathew.nexblock

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shalenmathew.nexblock.crypto.presentation.coin_list.CoinListViewModel
import com.shalenmathew.nexblock.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.shalenmathew.nexblock.core.navigation.AdaptiveCoinListDetailPane
import com.shalenmathew.nexblock.core.presentation.ObserveAsEvents
import com.shalenmathew.nexblock.core.presentation.toString
import com.shalenmathew.nexblock.crypto.presentation.coin_detail.CoinDetailScreen
import com.shalenmathew.nexblock.crypto.presentation.coin_list.CoinListEvent
import com.shalenmathew.nexblock.crypto.presentation.coin_list.CoinListScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {

                //  Material theme generator
                /// https://material-foundation.github.io/material-theme-builder/

                Scaffold(modifier = Modifier.fillMaxSize(),) { innerPadding ->
                    AdaptiveCoinListDetailPane(
                        modifier = Modifier.padding(innerPadding)
                    )
                }

            }
        }
    }
}
