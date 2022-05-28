package com.example.examenintercam.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment


class DialogGenericFragment<T : ViewDataBinding> constructor(
    @LayoutRes val layout: Int,
    val bindingView: (T, DialogFragment) -> Unit
) : DialogFragment() {

    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, layout, container, false) as T
        initView()
        return binding.root
    }

    private fun initView() {
        bindingView(binding, this)
    }

}