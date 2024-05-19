package com.example.cryptocoinstracker.data.network.model

import com.google.gson.annotations.SerializedName

data class CoinInfoDto (

    @SerializedName("TYPE")
    val type: String? = null,

    @SerializedName("MARKET")
    val market: String? = null,

    @SerializedName("FROMSYMBOL")
    val fromSymbol: String,

    @SerializedName("TOSYMBOL")
    val toSymbol: String? = null,

    @SerializedName("FLAGS")
    val flags: String? = null,

    @SerializedName("LASTMARKET")
    val lastMarket: String? = null,

    @SerializedName("PRICE")
    val price: Double? = null,

    @SerializedName("LASTUPDATE")
    val lastUpdate: Long? = null,

    @SerializedName("IMAGEURL")
    val imageUrl: String? = null,

    @SerializedName("HIGHDAY")
    val highDay: Double? = null,

    @SerializedName("LOWDAY")
    val lowDay: Double? = null

)