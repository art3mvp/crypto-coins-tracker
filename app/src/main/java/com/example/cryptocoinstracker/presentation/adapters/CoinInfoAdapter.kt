package com.example.cryptocoinstracker.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoinstracker.R
import com.example.cryptocoinstracker.databinding.CoinItemBinding
import com.example.cryptocoinstracker.domain.CoinInfo
import com.example.cryptocoinstracker.presentation.CoinInfoViewHolder
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinInfoViewHolder>() {

    var coinInfoList: List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = CoinItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return coinInfoList.size
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]

        with(holder) {
            val symbolsTemplate = context.resources.getString(R.string.symbols_template)
            val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)

            textViewSymbols.text = String.format(symbolsTemplate, coin.fromSymbol, coin.toSymbol)
            textViewPrice.text = coin.price.toString()
            textViewLastUpdate.text =
                String.format(lastUpdateTemplate, coin.lastUpdate)

            Picasso.get().load(coin.imageUrl).into(imageViewLogo)

            itemView.setOnClickListener {
                onCoinClickListener?.onCoinClickListener(coin)
            }
        }
    }

    interface OnCoinClickListener {
        fun onCoinClickListener(coinInfo: CoinInfo)
    }
}