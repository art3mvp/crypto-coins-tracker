package com.example.cryptocoinstracker.domain

data class CoinInfo(

    val fromSymbol: String,
    val toSymbol: String?,
    val lastMarket: String?,
    val price: String?,
    val lastUpdate: String?,
    val imageUrl: String,
    val highDay: String?,
    val lowDay: String?
)