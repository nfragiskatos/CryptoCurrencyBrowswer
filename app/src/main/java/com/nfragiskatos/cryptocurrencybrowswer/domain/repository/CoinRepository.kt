package com.nfragiskatos.cryptocurrencybrowswer.domain.repository

import com.nfragiskatos.cryptocurrencybrowswer.common.Resource
import com.nfragiskatos.cryptocurrencybrowswer.domain.model.Coin
import com.nfragiskatos.cryptocurrencybrowswer.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins() : Flow<Resource<List<Coin>>>
    fun getCoin(coinId: String) : Flow<Resource<CoinDetail>>
}