package com.example.cryptocoinstracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.criptocoinstracker.R

class CoinDetailActivity : AppCompatActivity() {


    private lateinit var viewModel: CoinDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coin_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fSym = intent.getStringExtra(EXTRA_FSYM)
        if (fSym == null) {
            finish()
            return
        }

        viewModel = ViewModelProvider(this)[CoinDetailViewModel::class.java]
        viewModel.getDetailInfo(fSym).observe(this, Observer {
            Log.d("other_tag", it.toString())
        })



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