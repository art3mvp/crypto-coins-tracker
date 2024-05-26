package com.example.cryptocoinstracker.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoinstracker.R
import com.example.cryptocoinstracker.databinding.ActivityCoinPriceListBinding
import com.example.cryptocoinstracker.domain.CoinInfo
import com.example.cryptocoinstracker.presentation.adapters.CoinInfoAdapter
import com.example.cryptocoinstracker.presentation.viewModels.CoinViewModel
import com.example.cryptocoinstracker.presentation.viewModels.ViewModelFactory
import javax.inject.Inject


class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var coinInfoAdapter: CoinInfoAdapter
    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as CoinApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]

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