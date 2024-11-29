package com.shalenmathew.nexblock.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shalenmathew.nexblock.core.domain.util.onError
import com.shalenmathew.nexblock.core.domain.util.onSuccess
import com.shalenmathew.nexblock.crypto.domain.repository.CoinRepository
import com.shalenmathew.nexblock.crypto.presentation.model.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(private val coinRepository: CoinRepository): ViewModel(){

    private val _coinState  = MutableStateFlow(CoinListState())
    val coinState = _coinState.onStart {
        loadCoins()
    }.stateIn(viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        CoinListState())


    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    fun onEvent(coinListActions: CoinListActions){
        when(coinListActions){
            is CoinListActions.onClick ->{}
        }
    }

    fun loadCoins(){

        viewModelScope.launch{

            _coinState.update {
                it.copy(isLoading = true)
            }

            coinRepository
                .coinList()
                .onSuccess { result->
                    _coinState.update {
                        it.copy(isLoading = false, listOfCoins =  result.map {it.toCoinUi() })
                    }
                }
                .onError { error->
                    _coinState.update { it.copy(isLoading = false)}
                    _events.send(CoinListEvent.Error(error))
                }

        }
    }


}