package com.example.examenintercam.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.examenintercam.core.model.BeerRequestModel
import com.example.examenintercam.databinding.FragmentPrincipalBinding
import com.example.examenintercam.ui.StatusRequest
import com.example.examenintercam.utils.loading.LoadingDialogFragment
import com.example.examenintercam.utils.log
import com.example.examenintercam.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentPrincipalBinding? = null
    val mBinding get() = _binding!!

    val viewModel by activityViewModels<MainViewModel>()

    val beerAdapter: BeerAdapter by lazy { BeerAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrincipalBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initElements()
        initObservers()
    }

    private fun initObservers() {
        viewModel.beerData.observe(viewLifecycleOwner) {
            when (it.first) {
                StatusRequest.LOADING -> {
                    "Cargando cervezas".log("HOME")
                    mBinding.shimmer.visibility = View.VISIBLE
                    mBinding.recyclerView.visibility = View.GONE
                }
                StatusRequest.SUCCESS -> {
                    "SUCCESS cervezas".log("HOME")
                    beerAdapter.setDataAndFavoriteList(
                        it.second?.first ?: BeerRequestModel(),
                        it.second?.second ?: arrayListOf()
                    )
                    mBinding.shimmer.visibility = View.GONE
                    mBinding.recyclerView.visibility = View.VISIBLE
                }
                StatusRequest.FAILURE -> {
                    "FAILURE cervezas".log("HOME")
                    mBinding.shimmer.visibility = View.GONE
                    mBinding.recyclerView.visibility = View.VISIBLE
                }
                StatusRequest.NONE -> {
                    "NONE cervezas".log("HOME")
                    mBinding.shimmer.visibility = View.GONE
                    mBinding.recyclerView.visibility = View.VISIBLE
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}