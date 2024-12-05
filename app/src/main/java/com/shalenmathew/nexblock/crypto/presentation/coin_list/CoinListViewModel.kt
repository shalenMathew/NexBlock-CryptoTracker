package com.shalenmathew.nexblock.crypto.presentation.coin_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shalenmathew.nexblock.core.domain.util.onError
import com.shalenmathew.nexblock.core.domain.util.onSuccess
import com.shalenmathew.nexblock.crypto.domain.repository.CoinRepository
import com.shalenmathew.nexblock.crypto.presentation.coin_detail.DataPoint
import com.shalenmathew.nexblock.crypto.presentation.model.CoinUi
import com.shalenmathew.nexblock.crypto.presentation.model.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.text.toFloat

class CoinListViewModel(private val coinRepository: CoinRepository): ViewModel(){

    private val _state  = MutableStateFlow(CoinListState())
    val state = _state.onStart {
        loadCoins()
    }.stateIn(viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        CoinListState())


    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    fun onEvent(coinListActions: CoinListActions){
        when(coinListActions){
            is CoinListActions.onClick ->{
//                _state.update {
//                    it.copy(selectedCoin = coinListActions.coinUi)
//                }
                selectCoin(coinListActions.coinUi)
            }
        }
    }

    fun selectCoin(coinUi: CoinUi){

        _state.update { it.copy(selectedCoin = coinUi) }

        viewModelScope.launch{
            coinRepository.getCoinHistory(  coinId = coinUi.id,
                start = ZonedDateTime.now().minusDays(5),
                end = ZonedDateTime.now())
                .onSuccess { history ->
                    val dataPoints = history
                        .sortedBy { it.dateTime }
                        .map {
                            DataPoint(
                                x = it.dateTime.hour.toFloat(),
                                y = it.priceUsd.toFloat(),
                                xLabel = DateTimeFormatter
                                    .ofPattern("ha\nM/d")
                                    .format(it.dateTime)
                            )
                        }

                    _state.update {
                        it.copy(
                            selectedCoin = it.selectedCoin?.copy(
                                coinPriceHistory = dataPoints
                            )
                        )
                    }
                }
                .onError { error ->
                    _events.send(CoinListEvent.Error(error))
                }
        }
    }

    fun loadCoins(){

        viewModelScope.launch{

            _state.update {
                it.copy(isLoading = true)
            }

            coinRepository
                .coinList()
                .onSuccess { result->
                    _state.update {
                        it.copy(isLoading = false, listOfCoins =  result.map {it.toCoinUi() })
                    }
                }
                .onError { error->
                    _state.update { it.copy(isLoading = false)}
                    _events.send(CoinListEvent.Error(error))
                }

        }
    }


}