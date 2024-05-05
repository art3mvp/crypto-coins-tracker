package com.example.cryptocoinstracker.pojo

import android.annotation.SuppressLint
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptocoinstracker.api.ApiFactory.BASE_IMAGE_URL
import com.example.cryptocoinstracker.utils.convertTimeStampToTime
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinPriceInfo (

    @SerializedName("TYPE")
    @Expose
    var type: String? = null,

    @SerializedName("MARKET")
    @Expose
    var market: String? = null,

    @SerializedName("FROMSYMBOL")
    @Expose
    @PrimaryKey
    var fromsymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    var tosymbol: String? = null,

    @SerializedName("FLAGS")
    @Expose
    var flags: String? = null,

    @SerializedName("LASTMARKET")
    @Expose
    var lastmarket: String? = null,

    @SerializedName("MEDIAN")
    @Expose
    var median: Double? = null,

    @SerializedName("TOPTIERVOLUME24HOUR")
    @Expose
    var toptiervolume24hour: Double? = null,

    @SerializedName("TOPTIERVOLUME24HOURTO")
    @Expose
    var toptiervolume24hourto: Double? = null,

    @SerializedName("LASTTRADEID")
    @Expose
    var lasttradeid: String? = null,

    @SerializedName("PRICE")
    @Expose
    var price: Double? = null,

    @SerializedName("LASTUPDATE")
    @Expose
    var lastupdate: Long? = null,

    @SerializedName("IMAGEURL")
    @Expose
    var imageUrl: String? = null

) {
    fun getFormattedTime(): String {
        return convertTimeStampToTime(lastupdate)
    }

    fun getFullImageUrl(): String {
        return BASE_IMAGE_URL + imageUrl
    }
}
