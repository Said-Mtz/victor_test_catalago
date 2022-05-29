package com.example.examenintercam.ui.fragments.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.examenintercam.R
import com.example.examenintercam.databinding.FragmentFavoritesBinding
import com.example.examenintercam.ui.fragments.principal.BeerAdapter


class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    val mBinding get() = _binding!!

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}