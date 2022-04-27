package com.nfragiskatos.cryptocurrencybrowswer.data.remote

import com.nfragiskatos.cryptocurrencybrowswer.data.remote.dto.CoinDetailDto
import com.nfragiskatos.cryptocurrencybrowswer.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Query("coinId") coinId: String): CoinDetailDto


}