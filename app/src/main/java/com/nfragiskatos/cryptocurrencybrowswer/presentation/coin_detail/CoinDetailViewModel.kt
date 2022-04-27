package com.nfragiskatos.cryptocurrencybrowswer.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nfragiskatos.cryptocurrencybrowswer.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase
) : ViewModel() {

    private val _state: State<CoinDetailState> = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state
}