package com.nfragiskatos.cryptocurrencybrowswer.presentation.coin_list

import com.nfragiskatos.cryptocurrencybrowswer.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
