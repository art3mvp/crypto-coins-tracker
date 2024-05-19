package com.example.cryptocoinstracker.domain

class LoadDataUseCase(private val repository: CoinRepository) {

    operator fun invoke() = repository.loadData()
}