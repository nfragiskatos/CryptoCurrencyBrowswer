package com.nfragiskatos.cryptocurrencybrowswer.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nfragiskatos.cryptocurrencybrowswer.common.Constants
import com.nfragiskatos.cryptocurrencybrowswer.common.Resource
import com.nfragiskatos.cryptocurrencybrowswer.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

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
                    when (result) {
                        is Resource.Error -> {
                            _state.value = CoinDetailState(
                                error = result.message ?: "An unexpected error occurred"
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = CoinDetailState(isLoading = result.isLoading)
                        }
                        is Resource.Success -> {
                            _state.value = CoinDetailState(coinDetail = result.data)
                        }
                    }
                }
        }
    }
}