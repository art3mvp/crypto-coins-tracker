package com.example.cryptocoinstracker.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptocoinstracker.data.network.model.CoinInfoDto
import com.example.cryptocoinstracker.domain.CoinInfo

object CoinDiffCallback: DiffUtil.ItemCallback<CoinInfo>(){

    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}