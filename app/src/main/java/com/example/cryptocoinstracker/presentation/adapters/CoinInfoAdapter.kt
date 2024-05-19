package com.example.cryptocoinstracker.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoinstracker.R
import com.example.cryptocoinstracker.databinding.CoinItemBinding
import com.example.cryptocoinstracker.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
    ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinDiffCallback) {

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = CoinItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }



    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin =  getItem(position)

        with(holder.binding) {
            val symbolsTemplate = context.resources.getString(R.string.symbols_template)
            textViewSymbols.text = String.format(symbolsTemplate, coin.fromSymbol, coin.toSymbol)
            textViewPrice.text = coin.price.toString()
            textViewLastUpdate.text = coin.lastUpdate

            Picasso.get().load(coin.imageUrl).into(imageViewLogo)

            root.setOnClickListener {
                onCoinClickListener?.onCoinClickListener(coin)
            }
        }
    }

    interface OnCoinClickListener {
        fun onCoinClickListener(coinInfo: CoinInfo)
    }
}