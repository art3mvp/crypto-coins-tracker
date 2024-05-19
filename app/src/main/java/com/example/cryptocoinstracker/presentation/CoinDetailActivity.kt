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
    private lateinit var binding: ActivityCoinDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        intent.getStringExtra(EXTRA_FSYM)?.let { it ->
            viewModel = ViewModelProvider(this)[CoinDetailViewModel::class.java]
            viewModel.getCoinInfo(it).observe(this) {
                binding.textViewCoinPrice.text =
                    String.format(getString(R.string.current_price), it.price.toString())
                binding.textViewCoinTitle.text =
                    String.format(getString(R.string.symbols_template), it.fromSymbol, it.toSymbol)
                binding.textViewDayMin.text =
                    String.format(getString(R.string.min_price), it.lowDay.toString())
                binding.textViewDayMax.text =
                    String.format(getString(R.string.max_price), it.highDay.toString())
                binding.textViewCoinLastUpdate.text =
                    String.format(getString(R.string.last_update_template), it.lastUpdate)
                binding.textViewCoinLastDeal.text =
                    String.format(getString(R.string.last_deal), it.lastMarket)

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