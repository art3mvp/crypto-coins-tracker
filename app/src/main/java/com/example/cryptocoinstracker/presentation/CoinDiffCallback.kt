package com.example.cryptocoinstracker.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptocoinstracker.data.network.model.CoinInfoDto

class CoinDiffCallback: DiffUtil.ItemCallback<CoinInfoDto>(){
    override fun areItemsTheSame(oldItem: CoinInfoDto, newItem: CoinInfoDto): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfoDto, newItem: CoinInfoDto): Boolean {
        return oldItem == newItem
    }
}