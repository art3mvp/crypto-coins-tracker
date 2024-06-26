package com.example.cryptocoinstracker.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptocoinstracker.data.network.model.CoinInfoDto


@Dao
interface CoinInfoDao {

    @Query("SELECT * FROM full_price_list WHERE price > 1 ORDER BY lastupdate DESC ")
    fun getPriceList(): LiveData<List<CoinInfoDbModel>>

    @Query("SELECT * FROM full_price_list WHERE fromsymbol == :fSym LIMIT 1")
    fun getCoinPriceInfo(fSym: String): LiveData<CoinInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(priceList: List<CoinInfoDbModel>)

}