package com.example.cryptocoinstracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.criptocoinstracker.R
import com.example.criptocoinstracker.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {


    private lateinit var viewModel: CoinDetailViewModel
    private lateinit var binding: ActivityCoinDetailBinding
    private lateinit var textViewTitle: TextView
    private lateinit var textViewDayMin: TextView
    private lateinit var textViewDayMax: TextView
    private lateinit var textViewCoinPrice: TextView
    private lateinit var textViewCoinLastUpdate: TextView
    private lateinit var imageViewCoinImage: ImageView
    private lateinit var textViewCoinLastDeal: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViews()

        val fSym = intent.getStringExtra(EXTRA_FSYM)?.let {
            viewModel = ViewModelProvider(this)[CoinDetailViewModel::class.java]
            viewModel.getDetailInfo(it).observe(this, Observer {
                with(this.resources) {
                    textViewCoinPrice.text = String.format(getString(R.string.current_price), it.price)
                    textViewTitle.text = String.format(getString(R.string.symbols_template), it.fromSymbol, it.toSymbol)
                    textViewDayMin.text = String.format(getString(R.string.min_price), it.lowDay)
                    textViewDayMax.text = String.format(getString(R.string.max_price), it.highDay)
                    textViewCoinLastUpdate.text = String.format(getString(R.string.last_update_template), it.getFormattedTime())
                    textViewCoinLastDeal.text = String.format(getString(R.string.last_deal, it.lastMarket))
                    Picasso.get().load(it.getFullImageUrl()).into(imageViewCoinImage)
                }
            })
        }

    }

    private fun initViews() {
        textViewTitle = binding.textViewCoinTitle
        textViewDayMin = binding.textViewDayMin
        textViewDayMax = binding.textViewDayMax
        textViewCoinPrice = binding.textViewCoinPrice
        textViewCoinLastUpdate = binding.textViewCoinLastUpdate
        imageViewCoinImage = binding.imageViewCoinImage
        textViewCoinLastDeal = binding.textViewCoinLastDeal
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