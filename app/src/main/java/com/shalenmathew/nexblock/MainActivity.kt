package com.shalenmathew.nexblock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shalenmathew.nexblock.crypto.presentation.coin_list.CoinListViewModel
import com.shalenmathew.nexblock.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
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

                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.coinState.collectAsStateWithLifecycle()

                    CoinListScreen(modifier = Modifier.padding(innerPadding),
                        state = state)


                }

            }
        }
    }
}
