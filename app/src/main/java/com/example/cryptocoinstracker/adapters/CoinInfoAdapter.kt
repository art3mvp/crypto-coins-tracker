package com.example.cryptocoinstracker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.criptocoinstracker.R
import com.example.criptocoinstracker.databinding.CoinItemBinding
import com.example.cryptocoinstracker.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

        var onCoinClickListener: OnCoinClickListener? = null

    var coinsInfoList: List<CoinPriceInfo> = arrayListOf<CoinPriceInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = CoinItemBinding.inflate(
           LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinsInfoList[position]

        with(holder) {
            val symbolsTemplate = context.resources.getString(R.string.symbols_template)
            val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)

            textViewSymbols.text = String.format(symbolsTemplate, coin.fromsymbol, coin.tosymbol)
            textViewPrice.text = coin.price.toString()
            textViewLastUpdate.text = String.format(lastUpdateTemplate, coin.getFormattedTime())
            Picasso.get().load(coin.getFullImageUrl()).into(imageViewLogo)

            itemView.setOnClickListener{
                onCoinClickListener?.onCoinClickListener(coin)
            }
        }
    }

    override fun getItemCount() = coinsInfoList.size

    inner class CoinInfoViewHolder(binding: CoinItemBinding) : ViewHolder(binding.root) {

        val imageViewLogo = binding.imageViewLogo
        val textViewLastUpdate = binding.textViewLastUpdate
        val textViewSymbols = binding.textViewSymbols
        val textViewPrice = binding.textViewPrice
    }

    interface OnCoinClickListener {
        fun onCoinClickListener(coinPriceInfo: CoinPriceInfo)
    }
}