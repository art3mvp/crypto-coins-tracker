package com.example.cryptocoinstracker.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoinstracker.R
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
                if (isOnePaneMode()) {
                    launchDetailActivity(coinInfo.fromSymbol)
                } else {
                    launchDetailFragment(coinInfo.fromSymbol)
                }
            }
        }
        binding.recyclerViewCoinPriceList.adapter = coinInfoAdapter
        binding.recyclerViewCoinPriceList.itemAnimator = null

        viewModel.coinInfoList.observe(this) {
            coinInfoAdapter.submitList(it)
        }
    }

    private fun isOnePaneMode() = binding.fragmentContainer == null

    private fun launchDetailActivity(fSym: String) {
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
            fSym
        )
        startActivity(intent)
    }

    private fun launchDetailFragment(fSym: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, CoinDetailFragment.newInstance(fSym))
            .addToBackStack(null)
            .commit()
    }
}