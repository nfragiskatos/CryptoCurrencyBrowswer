package com.nfragiskatos.cryptocurrencybrowswer.presentation.coin_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nfragiskatos.cryptocurrencybrowswer.common.Resource
import com.nfragiskatos.cryptocurrencybrowswer.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    var state by mutableStateOf(CoinListState())

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase().collect { result ->
                state = when (result) {
                    is Resource.Error -> {
                        state.copy(error = result.message ?: "An unexpected error occurred")
                    }
                    is Resource.Loading -> {
                        state.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success -> {
                        state.copy(coins = result.data ?: emptyList())
                    }
                }
            }
        }
    }
}