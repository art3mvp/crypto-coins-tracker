package com.example.cryptocoinstracker.presentation

import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoinstracker.databinding.CoinItemBinding

class CoinInfoViewHolder(binding: CoinItemBinding) : RecyclerView.ViewHolder(binding.root) {

    val imageViewLogo = binding.imageViewLogo
    val textViewLastUpdate = binding.textViewLastUpdate
    val textViewSymbols = binding.textViewSymbols
    val textViewPrice = binding.textViewPrice
}