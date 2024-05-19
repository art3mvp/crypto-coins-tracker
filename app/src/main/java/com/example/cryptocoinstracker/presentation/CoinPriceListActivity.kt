package com.example.cryptocoinstracker.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoinstracker.databinding.ActivityCoinPriceListBinding
import com.example.cryptocoinstracker.domain.CoinInfo
import com.example.cryptocoinstracker.presentation.adapters.CoinInfoAdapter


class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var coinInfoAdapter: CoinInfoAdapter
    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        binding.recyclerViewCoinPriceList.itemAnimator = null

        viewModel.coinInfoList.observe(this) {
            coinInfoAdapter.submitList(it)
        }

    }
}