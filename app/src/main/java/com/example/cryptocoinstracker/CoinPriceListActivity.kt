package com.example.cryptocoinstracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.criptocoinstracker.R
import com.example.cryptocoinstracker.adapters.CoinInfoAdapter
import io.reactivex.rxjava3.disposables.CompositeDisposable


class CoinPriceListActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var viewModel: CoinViewModel
    private lateinit var detailViewModel: CoinDetailViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var coinInfoAdapter: CoinInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)


        initViews()

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        detailViewModel = ViewModelProvider(this)[CoinDetailViewModel::class.java]

        coinInfoAdapter = CoinInfoAdapter()
        recyclerView.adapter = coinInfoAdapter

        viewModel.priceList.observe(this, Observer {
            Log.d("my_tag", "price list" + it.toString())
            coinInfoAdapter.coinsInfoList = it
        })

        detailViewModel.getDetailInfo("BTC").observe(this, Observer {
            Log.d("my_tag", "detail view model" + it.toString())
        })
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerViewCoinPriceList)
    }
}