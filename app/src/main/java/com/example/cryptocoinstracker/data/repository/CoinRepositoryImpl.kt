package com.example.cryptocoinstracker.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptocoinstracker.data.database.AppDatabase
import com.example.cryptocoinstracker.data.mapper.CoinMapper
import com.example.cryptocoinstracker.data.workers.RefreshDataWorker
import com.example.cryptocoinstracker.domain.CoinInfo
import com.example.cryptocoinstracker.domain.CoinRepository

class CoinRepositoryImpl(private val application: Application) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val mapper = CoinMapper()

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

    override fun loadData() {
        val workerManager = WorkManager.getInstance(application)
        workerManager.enqueueUniqueWork(
            RefreshDataWorker.UNIQUE_NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}