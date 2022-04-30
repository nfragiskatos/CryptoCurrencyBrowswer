package com.nfragiskatos.cryptocurrencybrowswer.domain.use_case.get_coin

import com.nfragiskatos.cryptocurrencybrowswer.common.Resource
import com.nfragiskatos.cryptocurrencybrowswer.domain.model.CoinDetail
import com.nfragiskatos.cryptocurrencybrowswer.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String) : Flow<Resource<CoinDetail>> = repository.getCoin(coinId)
}