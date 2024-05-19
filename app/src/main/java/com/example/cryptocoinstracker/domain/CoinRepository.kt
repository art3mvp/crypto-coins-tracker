package com.example.cryptocoinstracker.domain

import androidx.lifecycle.LiveData

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    fun getCoinInfo(fSym: String): LiveData<CoinInfo>

    fun loadData()
}