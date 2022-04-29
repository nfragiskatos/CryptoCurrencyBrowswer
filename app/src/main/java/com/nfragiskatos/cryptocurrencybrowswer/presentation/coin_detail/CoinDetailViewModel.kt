package com.nfragiskatos.cryptocurrencybrowswer.presentation.coin_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nfragiskatos.cryptocurrencybrowswer.common.Constants
import com.nfragiskatos.cryptocurrencybrowswer.common.Resource
import com.nfragiskatos.cryptocurrencybrowswer.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state: CoinDetailState by mutableStateOf(CoinDetailState())

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)
            ?.let { coinId ->
                getCoin(coinId)
            }
    }

    private fun getCoin(coinId: String) {
        viewModelScope.launch {
            getCoinUseCase(coinId)
                .collect { result ->
                    state = when (result) {
                        is Resource.Error -> {
                            state.copy(
                                error = result.message ?: "An unexpected error occurred"
                            )
                        }
                        is Resource.Loading -> {
                            state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Success -> {
                            state.copy(coinDetail = result.data)
                        }
                    }
                }
        }
    }
}