package com.example.cryptocoinstracker.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.cryptocoinstracker.data.database.AppDatabase
import com.example.cryptocoinstracker.data.mapper.CoinMapper
import com.example.cryptocoinstracker.data.network.ApiFactory
import com.example.cryptocoinstracker.data.network.ApiService
import com.example.cryptocoinstracker.domain.CoinInfo
import com.example.cryptocoinstracker.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(application: Application) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val mapper = CoinMapper()
    private val apiService = ApiFactory.apiService

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return coinInfoDao.getPriceList().map {
            mapper.mapDbModelListToEntityList(it)
        }
    }

    override fun getCoinInfo(fSym: String): LiveData<CoinInfo> {
        return coinInfoDao.getCoinPriceInfo(fSym).map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {

            try {
                val topCoins = ApiFactory.apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = ApiFactory.apiService.getFullPriceList(fSyms = fSyms)
                val dbModelList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                    .map {
                        mapper.mapDtoToDbModel(it)
                    }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
}