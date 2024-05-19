package com.example.cryptocoinstracker.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cryptocoinstracker.data.repository.CoinRepositoryImpl
import com.example.cryptocoinstracker.domain.GetCoinInfoUseCase

class CoinDetailViewModel(application: Application) : AndroidViewModel(application) {


    private val repository = CoinRepositoryImpl(application)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)

    fun getCoinInfo(fSym: String) = getCoinInfoUseCase(fSym)

}