package com.example.criptocoinstracker

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.criptocoinstracker.api.ApiFactory
import com.example.criptocoinstracker.database.AppDatabase
import com.example.criptocoinstracker.pojo.CoinPriceInfo
import com.example.criptocoinstracker.pojo.CoinPriceInfoRawData
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val database: AppDatabase = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    val priceList = database.coinPriceInfoDao().getPriceList()

    fun loadData() {
        val disposable: Disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 10)
            //in CoinInfoDataList -> Datum -> CoinInfo.name. out BTC,ETH....
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",").toString() }
            // out BTC: { USD: {TYPE:5...
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            //in jsonObject out List<CoinPriceInfo>
            .map { getPriceListFromRawData(it) }
            .subscribeOn(Schedulers.io())
            .subscribe({
                database.coinPriceInfoDao().insertPriceList(it)
                Log.d("my_tag", "Success: $it")
            }, {
                Log.d("my_tag", "Failure: $it")
            }
            )
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData,
    ): List<CoinPriceInfo> {
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return emptyList()


        // [BTC: { USD: {TYPE:5}] ( getAs(BTC) ) -> [USD: {TYPE: 5}, ] -> ( getAs(USD) ) -> [type:5, type...]
        return jsonObject.keySet().flatMap { coinKey ->
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            currencyJson.keySet().map { currencyKey ->
                Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}