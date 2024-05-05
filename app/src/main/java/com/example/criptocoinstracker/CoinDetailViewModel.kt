package com.example.criptocoinstracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.criptocoinstracker.database.AppDatabase
import com.example.criptocoinstracker.pojo.CoinPriceInfo
import io.reactivex.rxjava3.disposables.CompositeDisposable

class CoinDetailViewModel(application: Application): AndroidViewModel(application){

    private val database: AppDatabase = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo> {
        return database.coinPriceInfoDao().getCoinPriceInfo(fSym)
    }
}