package com.example.cryptocoinstracker.domain

import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//binding.textViewCoinPrice.text =
//String.format(getString(R.string.current_price), it.price)
//binding.textViewCoinTitle.text =
//String.format(getString(R.string.symbols_template), it.fromSymbol, it.toSymbol)
//binding.textViewDayMin.text =
//String.format(getString(R.string.min_price), it.lowDay)
//binding.textViewDayMax.text =
//String.format(getString(R.string.max_price), it.highDay)
//binding.textViewCoinLastUpdate.text =
//String.format(getString(R.string.last_update_template), it.getFormattedTime())
//binding.textViewCoinLastDeal.text =
//String.format(getString(R.string.last_deal), it.lastMarket)

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