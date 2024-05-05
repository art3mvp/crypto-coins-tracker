package com.example.criptocoinstracker.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinPriceInfo (
    @SerializedName("TYPE")
    @Expose
    private var type: String? = null,

    @SerializedName("MARKET")
    @Expose
    private var market: String? = null,

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    private var fromsymbol: String? = null,

    @SerializedName("TOSYMBOL")
    @Expose
    private var tosymbol: String? = null,

    @SerializedName("FLAGS")
    @Expose
    private var flags: String? = null,

    @SerializedName("LASTMARKET")
    @Expose
    private var lastmarket: String? = null,

    @SerializedName("MEDIAN")
    @Expose
    private var median: Double? = null,

    @SerializedName("TOPTIERVOLUME24HOUR")
    @Expose
    private var toptiervolume24hour: Double? = null,

    @SerializedName("TOPTIERVOLUME24HOURTO")
    @Expose
    private var toptiervolume24hourto: Double? = null,

    @SerializedName("LASTTRADEID")
    @Expose
    private var lasttradeid: String? = null,

    @SerializedName("PRICE")
    @Expose
    private var price: Double? = null,

    @SerializedName("LASTUPDATE")
    @Expose
    private var lastupdate: Int? = null,

    @SerializedName("LASTVOLUME")
    @Expose
    private var lastvolume: Double? = null,

    @SerializedName("LASTVOLUMETO")
    @Expose
    private var lastvolumeto: Double? = null,

    @SerializedName("VOLUMEHOUR")
    @Expose
    private var volumehour: Double? = null,

    @SerializedName("VOLUMEHOURTO")
    @Expose
    private var volumehourto: Double? = null,

    @SerializedName("OPENHOUR")
    @Expose
    private var openhour: Double? = null,

    @SerializedName("HIGHHOUR")
    @Expose
    private var highhour: Double? = null,

    @SerializedName("LOWHOUR")
    @Expose
    private var lowhour: Double? = null,

    @SerializedName("VOLUMEDAY")
    @Expose
    private var volumeday: Double? = null,

    @SerializedName("VOLUMEDAYTO")
    @Expose
    private var volumedayto: Double? = null,

    @SerializedName("OPENDAY")
    @Expose
    private var openday: Double? = null,

    @SerializedName("HIGHDAY")
    @Expose
    private var highday: Double? = null,

    @SerializedName("LOWDAY")
    @Expose
    private var lowday: Double? = null,

    @SerializedName("VOLUME24HOUR")
    @Expose
    private var volume24hour: Double? = null,

    @SerializedName("VOLUME24HOURTO")
    @Expose
    private var volume24hourto: Double? = null,

    @SerializedName("OPEN24HOUR")
    @Expose
    private var open24hour: Double? = null,

    @SerializedName("HIGH24HOUR")
    @Expose
    private var high24hour: Double? = null,

    @SerializedName("LOW24HOUR")
    @Expose
    private var low24hour: Double? = null,

    @SerializedName("CHANGE24HOUR")
    @Expose
    private var change24hour: Double? = null,

    @SerializedName("CHANGEPCT24HOUR")
    @Expose
    private var changepct24hour: Double? = null,

    @SerializedName("CHANGEDAY")
    @Expose
    private var changeday: Double? = null,

    @SerializedName("CHANGEPCTDAY")
    @Expose
    private var changepctday: Double? = null,

    @SerializedName("CHANGEHOUR")
    @Expose
    private var changehour: Double? = null,

    @SerializedName("CHANGEPCTHOUR")
    @Expose
    private var changepcthour: Double? = null,

    @SerializedName("CONVERSIONTYPE")
    @Expose
    private var conversiontype: String? = null,

    @SerializedName("CONVERSIONSYMBOL")
    @Expose
    private var conversionsymbol: String? = null,

    @SerializedName("CONVERSIONLASTUPDATE")
    @Expose
    private var conversionlastupdate: Int? = null,

    @SerializedName("SUPPLY")
    @Expose
    private var supply: Int? = null,

    @SerializedName("MKTCAP")
    @Expose
    private var mktcap: Double? = null,

    @SerializedName("MKTCAPPENALTY")
    @Expose
    private var mktcappenalty: Int? = null,

    @SerializedName("CIRCULATINGSUPPLY")
    @Expose
    private var circulatingsupply: Int? = null,

    @SerializedName("CIRCULATINGSUPPLYMKTCAP")
    @Expose
    private var circulatingsupplymktcap: Double? = null,

    @SerializedName("TOTALVOLUME24H")
    @Expose
    private var totalvolume24h: Double? = null,

    @SerializedName("TOTALVOLUME24HTO")
    @Expose
    private var totalvolume24hto: Double? = null,

    @SerializedName("TOTALTOPTIERVOLUME24H")
    @Expose
    private var totaltoptiervolume24h: Double? = null,

    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    @Expose
    private var totaltoptiervolume24hto: Double? = null,

    @SerializedName("IMAGEURL")
    @Expose
    private var imageurl: String? = null

)
