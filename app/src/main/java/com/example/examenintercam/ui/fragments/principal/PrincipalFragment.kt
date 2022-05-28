package com.example.examenintercam.ui.fragments.principal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.examenintercam.viewmodel.MainViewModel
import com.example.examenintercam.core.api.ApiBeerService
import com.example.examenintercam.databinding.FragmentPrincipalBinding

class PrincipalFragment : Fragment() {

    private var _binding: FragmentPrincipalBinding? = null
    val mBinding get() = _binding!!

    val viewModel by activityViewModels<MainViewModel>()

    lateinit var apiService: ApiBeerService
    val myAdapter: BeerAdapter by lazy { BeerAdapter() }

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}