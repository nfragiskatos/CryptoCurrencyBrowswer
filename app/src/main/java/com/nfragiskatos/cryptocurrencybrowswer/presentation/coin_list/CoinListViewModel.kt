package com.nfragiskatos.cryptocurrencybrowswer.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nfragiskatos.cryptocurrencybrowswer.common.Resource
import com.nfragiskatos.cryptocurrencybrowswer.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf<CoinListState>(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value =
                            CoinListState(error = result.message ?: "An unexpected error occurred")
                    }
                    is Resource.Loading -> {
                        _state.value = CoinListState(isLoading = result.isLoading)
                    }
                    is Resource.Success -> {
                        _state.value = CoinListState(coins = result.data ?: emptyList())
                    }
                }
            }
        }
    }
}