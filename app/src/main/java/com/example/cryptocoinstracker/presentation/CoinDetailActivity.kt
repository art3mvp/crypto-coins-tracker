package com.example.cryptocoinstracker.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoinstracker.R
import com.example.cryptocoinstracker.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {


    private lateinit var viewModel: CoinDetailViewModel
    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        intent.getStringExtra(EXTRA_FSYM)?.let { it ->
            viewModel = ViewModelProvider(this)[CoinDetailViewModel::class.java]
            viewModel.getCoinInfo(it).observe(this) {
                binding.textViewCoinPrice.text = it.price.toString()
                binding.textViewCoinTitleFromSymbol.text = it.fromSymbol
                binding.textViewCoinTitleToSymbol.text = it.toSymbol
                binding.textViewDayMin.text = it.lowDay.toString()
                binding.textViewDayMax.text = it.highDay.toString()
                binding.textViewCoinLastUpdate.text = it.lastUpdate
                binding.textViewCoinLastDeal.text = it.lastMarket

                Picasso.get().load(it.imageUrl).into(binding.imageViewCoinImage)
            }
        }

    }

    companion object {

        private const val EXTRA_FSYM = "fSym"

        fun newIntent(context: Context, fSym: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FSYM, fSym)
            return intent
        }
    }
}