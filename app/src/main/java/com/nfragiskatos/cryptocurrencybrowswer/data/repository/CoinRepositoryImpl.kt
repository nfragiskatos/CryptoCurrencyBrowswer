package com.nfragiskatos.cryptocurrencybrowswer.data.repository

import com.nfragiskatos.cryptocurrencybrowswer.common.Resource
import com.nfragiskatos.cryptocurrencybrowswer.data.mapper.toCoin
import com.nfragiskatos.cryptocurrencybrowswer.data.mapper.toCoinDetail
import com.nfragiskatos.cryptocurrencybrowswer.data.remote.CoinPaprikaApi
import com.nfragiskatos.cryptocurrencybrowswer.data.remote.dto.CoinDto
import com.nfragiskatos.cryptocurrencybrowswer.domain.model.Coin
import com.nfragiskatos.cryptocurrencybrowswer.domain.model.CoinDetail
import com.nfragiskatos.cryptocurrencybrowswer.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): Flow<Resource<List<Coin>>> {

        return flow {
            emit(Resource.Loading(true))

            val remote: List<CoinDto>? = try {
                api.getCoins()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Error loading coins"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't reach server. Please check your internet connection."))
                null
            }

            remote?.let { coinDto ->
                emit(Resource.Success(data = coinDto.map { it.toCoin() }))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getCoin(coinId: String): Resource<CoinDetail> {
        return Resource.Success<CoinDetail>(
            api.getCoinById(coinId)
                .toCoinDetail()
        )
    }

}