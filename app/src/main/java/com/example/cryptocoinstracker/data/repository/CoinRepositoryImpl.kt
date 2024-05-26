package com.example.cryptocoinstracker.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptocoinstracker.data.database.AppDatabase
import com.example.cryptocoinstracker.data.database.CoinInfoDao
import com.example.cryptocoinstracker.data.mapper.CoinMapper
import com.example.cryptocoinstracker.data.workers.RefreshDataWorker
import com.example.cryptocoinstracker.domain.CoinInfo
import com.example.cryptocoinstracker.domain.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val coinInfoDao: CoinInfoDao,
    private val mapper: CoinMapper
) : CoinRepository {

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