package com.example.examenintercam.utils.loading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.examenintercam.R
import com.example.examenintercam.databinding.FragmentLoadingDialogBinding

class LoadingDialogFragment : DialogFragment() {

    lateinit var mBinding: FragmentLoadingDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE,  com.google.android.material.R.style.ShapeAppearanceOverlay_MaterialComponents_MaterialCalendar_Window_Fullscreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLoadingDialogBinding.inflate(inflater, container, false).apply {
        mBinding = this
        mBinding.apply {  }
    }.root

}