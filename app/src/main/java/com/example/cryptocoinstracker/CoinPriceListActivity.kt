package com.example.cryptocoinstracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.criptocoinstracker.databinding.ActivityCoinPriceListBinding
import com.example.cryptocoinstracker.adapters.CoinInfoAdapter
import com.example.cryptocoinstracker.pojo.CoinPriceInfo
import io.reactivex.rxjava3.disposables.CompositeDisposable


class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var coinInfoAdapter: CoinInfoAdapter
    private lateinit var binding: ActivityCoinPriceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        coinInfoAdapter = CoinInfoAdapter(this)

        coinInfoAdapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClickListener(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        recyclerView.adapter = coinInfoAdapter

        viewModel.priceList.observe(this, Observer {
            coinInfoAdapter.coinsInfoList = it
        })

    }

    private fun initViews() {
        recyclerView = binding.recyclerViewCoinPriceList
    }
}