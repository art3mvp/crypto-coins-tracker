package com.example.cryptocoinstracker.domain

import androidx.lifecycle.LiveData

class GetCoinInfoUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke(fSym: String): LiveData<CoinInfo> = repository.getCoinInfo(fSym)
}