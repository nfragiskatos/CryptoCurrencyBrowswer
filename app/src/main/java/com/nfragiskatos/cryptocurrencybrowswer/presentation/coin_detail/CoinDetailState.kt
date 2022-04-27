package com.nfragiskatos.cryptocurrencybrowswer.presentation.coin_detail

import com.nfragiskatos.cryptocurrencybrowswer.domain.model.Coin
import com.nfragiskatos.cryptocurrencybrowswer.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String = ""
)
