package com.example.cryptocoinstracker.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.cryptocoinstracker.domain.GetCoinInfoListUseCase
import com.example.cryptocoinstracker.domain.GetCoinInfoUseCase
import com.example.cryptocoinstracker.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val loadDataUseCase: LoadDataUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    ) : ViewModel() {


    val coinInfoList = getCoinInfoListUseCase()

    fun getCoinInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}