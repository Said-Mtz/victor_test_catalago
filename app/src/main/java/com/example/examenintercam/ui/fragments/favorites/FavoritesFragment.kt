package com.example.examenintercam.ui.fragments.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.examenintercam.databinding.FragmentFavoritesBinding
import com.example.examenintercam.ui.StatusRequest
import com.example.examenintercam.viewmodel.MainViewModel


class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    val mBinding get() = _binding!!
    val viewModel by activityViewModels<MainViewModel>()

    val myAdapter: FavoritesAdapter by lazy { FavoritesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initElements()
        initObservers()
    }

    private fun initObservers() {
        viewModel.favoriteBeerData.observe(viewLifecycleOwner){
            when(it.first){
                StatusRequest.LOADING -> {

                }
                StatusRequest.SUCCESS ->{
                    initRecycler(it.second)
                }
                StatusRequest.FAILURE -> {

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}