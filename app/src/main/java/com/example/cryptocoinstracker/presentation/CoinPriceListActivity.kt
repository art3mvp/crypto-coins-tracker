package com.example.cryptocoinstracker.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoinstracker.databinding.ActivityCoinPriceListBinding
import com.example.cryptocoinstracker.domain.CoinInfo
import com.example.cryptocoinstracker.presentation.adapters.CoinInfoAdapter


class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private val recyclerView by lazy {
        binding.recyclerViewCoinPriceList
    }
    private lateinit var coinInfoAdapter: CoinInfoAdapter
    private lateinit var binding: ActivityCoinPriceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        coinInfoAdapter = CoinInfoAdapter(this)

        coinInfoAdapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClickListener(coinInfo: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        binding.recyclerViewCoinPriceList.adapter = coinInfoAdapter

        viewModel.coinInfoList.observe(this) {
            coinInfoAdapter.coinInfoList = it
        }

    }
}