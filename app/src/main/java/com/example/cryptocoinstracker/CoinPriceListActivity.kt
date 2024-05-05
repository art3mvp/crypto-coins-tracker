package com.example.cryptocoinstracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.criptocoinstracker.R
import com.example.criptocoinstracker.databinding.ActivityCoinPriceListBinding
import com.example.criptocoinstracker.databinding.CoinItemBinding
import com.example.cryptocoinstracker.adapters.CoinInfoAdapter
import com.example.cryptocoinstracker.pojo.CoinPriceInfo
import io.reactivex.rxjava3.disposables.CompositeDisposable


class CoinPriceListActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var viewModel: CoinViewModel
    private lateinit var detailViewModel: CoinDetailViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var coinInfoAdapter: CoinInfoAdapter
    private lateinit var binding: ActivityCoinPriceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        detailViewModel = ViewModelProvider(this)[CoinDetailViewModel::class.java]

        coinInfoAdapter = CoinInfoAdapter(this)

        coinInfoAdapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClickListener(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromsymbol
                )
                startActivity(intent)
            }
        }
        recyclerView.adapter = coinInfoAdapter

        viewModel.priceList.observe(this, Observer {
//            Log.d("my_tag", "price list" + it.toString())
            coinInfoAdapter.coinsInfoList = it
        })

        detailViewModel.getDetailInfo("BTC").observe(this, Observer {
//            Log.d("my_tag", "detail view model" + it.toString())
        })
    }

    private fun initViews() {
        recyclerView = binding.recyclerViewCoinPriceList
    }
}