package com.example.cryptocoinstracker.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(fSym: String): LiveData<CoinInfo> = repository.getCoinInfo(fSym)
}