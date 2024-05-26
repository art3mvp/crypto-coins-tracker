package com.example.cryptocoinstracker.data.mapper

import com.example.cryptocoinstracker.data.database.CoinInfoDbModel
import com.example.cryptocoinstracker.data.network.model.CoinInfoDto
import com.example.cryptocoinstracker.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptocoinstracker.data.network.model.CoinNamesListDto
import com.example.cryptocoinstracker.domain.CoinInfo
import com.google.gson.Gson
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class CoinMapper @Inject constructor() {

    fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDbModel(
        fromSymbol = dto.fromSymbol,
        toSymbol = dto.toSymbol,
        lastMarket = dto.lastMarket,
        price = dto.price,
        lastUpdate = dto.lastUpdate,
        imageUrl = BASE_IMAGE_URL + dto.imageUrl,
        highDay = dto.highDay.toString(),
        lowDay = dto.lowDay.toString()
    )

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val jsonObject = jsonContainer.json ?: return emptyList()

        return jsonObject.keySet().flatMap { coinKey ->
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            currencyJson.keySet().map { currencyKey ->
                Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
            }
        }
    }

    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map {
            it.coinNameDto?.name
        }?.joinToString(",").toString()
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel) = CoinInfo(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        lastMarket = dbModel.lastMarket,
        price = convertDoubleToStringTwoDecimals(dbModel.price),
        lastUpdate = convertTimeStampToTime(dbModel.lastUpdate),
        imageUrl = dbModel.imageUrl,
        highDay = dbModel.highDay,
        lowDay = dbModel.lowDay
    )

    fun mapDbModelListToEntityList(list: List<CoinInfoDbModel>) = list.map {
        mapDbModelToEntity(it)
    }

    private fun convertTimeStampToTime(timestamp: Long?): String {
        if (timestamp == null) {
            return ""
        }
        val stamp = Timestamp(timestamp * 1000)
        val date =  Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    private fun convertDoubleToStringTwoDecimals(price: Double?): String {
        return String.format("%.02f", price)
    }

    companion object {
        private const val BASE_IMAGE_URL = "https://www.cryptocompare.com"
    }
}


