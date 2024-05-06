package com.example.cryptocoinstracker

import android.app.Application
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.criptocoinstracker.R
import com.example.criptocoinstracker.databinding.ActivityCoinDetailBinding
import com.example.cryptocoinstracker.database.AppDatabase
import com.example.cryptocoinstracker.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.disposables.CompositeDisposable

class CoinDetailViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var textViewTitle: TextView
    private lateinit var textViewDayMin: TextView
    private lateinit var textViewDayMax: TextView
    private lateinit var textViewCoinPrice: TextView
    private lateinit var textViewCoinLastUpdate: TextView
    private lateinit var imageViewCoinImage: ImageView
    private lateinit var textViewCoinLastDeal: TextView


    private val database: AppDatabase = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo> {
        return database.coinPriceInfoDao().getCoinPriceInfo(fSym)
    }
}