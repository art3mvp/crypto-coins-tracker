package com.example.cryptocoinstracker.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoinstracker.databinding.FragmentCoinDetailBinding
import com.example.cryptocoinstracker.presentation.viewModels.CoinViewModel
import com.example.cryptocoinstracker.presentation.viewModels.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CoinDetailFragment : Fragment() {



    private lateinit var viewModel: CoinViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as CoinApp).component
    }

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding: FragmentCoinDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding is null")


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSymbol().let { it ->
            viewModel = ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
            viewModel.getCoinInfo(it).observe(viewLifecycleOwner) {
                binding.textViewCoinPrice.text = it.price.toString()
                binding.textViewCoinTitleFromSymbol.text = it.fromSymbol
                binding.textViewCoinTitleToSymbol.text = it.toSymbol
                binding.textViewDayMin.text = it.lowDay.toString()
                binding.textViewDayMax.text = it.highDay.toString()
                binding.textViewCoinLastUpdate.text = it.lastUpdate
                binding.textViewCoinLastDeal.text = it.lastMarket

                Picasso.get().load(it.imageUrl).into(binding.imageViewCoinImage)
            }
        }
    }

    private fun getSymbol(): String {
        return requireArguments().getString(EXTRA_FSYM, "")
    }

    companion object {

        private const val EXTRA_FSYM = "fSym"

        fun newInstance(fSym: String): Fragment {
            return CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FSYM, fSym)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}