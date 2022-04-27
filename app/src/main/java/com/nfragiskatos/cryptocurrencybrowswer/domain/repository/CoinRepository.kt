package com.nfragiskatos.cryptocurrencybrowswer.domain.repository

import com.nfragiskatos.cryptocurrencybrowswer.common.Resource
import com.nfragiskatos.cryptocurrencybrowswer.domain.model.Coin
import com.nfragiskatos.cryptocurrencybrowswer.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoins() : Flow<Resource<List<Coin>>>
    suspend fun getCoin(coinId: String) : Resource<CoinDetail>
}