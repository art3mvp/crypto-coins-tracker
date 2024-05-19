package com.example.cryptocoinstracker.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocoinstracker.data.repository.CoinRepositoryImpl
import com.example.cryptocoinstracker.domain.GetCoinInfoListUseCase
import com.example.cryptocoinstracker.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    init {
        loadDataUseCase()
    }
}