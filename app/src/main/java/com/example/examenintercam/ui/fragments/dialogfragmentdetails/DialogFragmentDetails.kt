package com.example.examenintercam.ui.fragments.dialogfragmentdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.examenintercam.databinding.FragmentDialogDetailsBinding

class DialogFragmentDetails : DialogFragment() {

    lateinit var mBinding: FragmentDialogDetailsBinding

    //https://code.luasoftware.com/tutorials/android/android-dialogfragment-fullscreen-like-activity/
    /*companion object {
        private const val FRAGMENT_TAG = "gift_dialog"
        fun newInstance() = DialogFragmentExample()

        fun show(fragmentManager: FragmentManager): DialogFragmentExample {
            val dialog = newInstance()
            // dialog.isCancelable = false
            dialog.show(fragmentManager, FRAGMENT_TAG)
            return dialog
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle( STYLE_NO_TITLE, com.google.android.material.R.style.ShapeAppearanceOverlay_MaterialComponents_MaterialCalendar_Window_Fullscreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDialogDetailsBinding.inflate(inflater, container, false).apply {
        mBinding = this
        initElements()
    }.root

}