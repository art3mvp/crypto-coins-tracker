package com.example.cryptocoinstracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.criptocoinstracker.R
import com.example.cryptocoinstracker.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

     var coinsInfoList: List<CoinPriceInfo> = arrayListOf<CoinPriceInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.coin_item,
            parent,
            false
        )
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinsInfoList[position]

        with (holder) {
            textViewSymbols.text = coin.fromsymbol + " // " + coin.tosymbol
            textViewPrice.text = coin.price.toString()
            textViewLastUpdate.text = coin.getFormattedTime()
            Picasso.get().load(coin.getFullImageUrl()).into(imageViewLogo)
        }
    }

    override fun getItemCount() = coinsInfoList.size

    inner class CoinInfoViewHolder(itemView: View) : ViewHolder(itemView) {
        val imageViewLogo = itemView.findViewById<ImageView>(R.id.imageViewLogo)
        val textViewLastUpdate = itemView.findViewById<TextView>(R.id.textViewLastUpdate)
        val textViewSymbols = itemView.findViewById<TextView>(R.id.textViewSymbols)
        val textViewPrice = itemView.findViewById<TextView>(R.id.textViewPrice)
    }
}