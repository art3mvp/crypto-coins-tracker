package com.example.cryptocoinstracker.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_price_list")
data class CoinInfoDbModel (

    @PrimaryKey
    val fromSymbol: String,

    val toSymbol: String?,
    val lastMarket: String?,
    val price: Double?,
    val lastUpdate: Long?,
    val imageUrl: String,
    val highDay: String?,
    val lowDay: String?,

)