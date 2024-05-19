package com.example.cryptocoinstracker.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
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

        if (!intent.hasExtra(EXTRA_FSYM)) {
            finish()
            return
        }

        val fSym = intent.getStringExtra(EXTRA_FSYM) ?: EMPTY_SYMBOL

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, CoinDetailFragment.newInstance(fSym))
                .commit()
        }



    }

    companion object {

        private const val EXTRA_FSYM = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, fSym: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FSYM, fSym)
            return intent
        }
    }
}