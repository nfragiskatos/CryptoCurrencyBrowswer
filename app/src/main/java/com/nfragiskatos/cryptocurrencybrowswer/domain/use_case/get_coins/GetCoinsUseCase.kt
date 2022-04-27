package com.nfragiskatos.cryptocurrencybrowswer.domain.use_case.get_coins

import com.nfragiskatos.cryptocurrencybrowswer.common.Resource
import com.nfragiskatos.cryptocurrencybrowswer.domain.model.Coin
import com.nfragiskatos.cryptocurrencybrowswer.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    suspend operator fun invoke() : Flow<Resource<List<Coin>>> {
        return repository.getCoins()
    }
}