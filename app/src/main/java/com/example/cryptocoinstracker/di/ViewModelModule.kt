package com.example.cryptocoinstracker.di

import androidx.lifecycle.ViewModel
import com.example.cryptocoinstracker.presentation.viewModels.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    fun bindCoinViewModule(viewModel: CoinViewModel): ViewModel
}