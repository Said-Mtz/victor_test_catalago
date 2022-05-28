package com.example.examenintercam.ui.fragments.login

import androidx.navigation.fragment.findNavController
import com.example.examenintercam.R
import com.example.examenintercam.utils.dialog.AlertDialogLoginObject
import com.google.android.material.textfield.TextInputLayout

fun LoginFragment.initElements() {
    mBinding.apply {
        btnLogin.setOnClickListener {
            /*val txtUser = edTxtUser.text.toString()
            val txtPassword = edTxtPassword.text.toString()
            validField(txtUser, inputLayoutEdTxtUser)
            validField(txtPassword, inputLayoutEdTxtPassword)*/

            //findNavController().navigate(R.id.action_loginFragment_to_principalFragment)

            val dialog = AlertDialogLoginObject
            dialog.showDialog(requireActivity())
        }

        /*validFieldUser()*/
    }
}

fun LoginFragment.validField(string: String, edTxt: TextInputLayout) {
    if (string.isNullOrEmpty()) {
        edTxt.helperText = getString(R.string.txt_empty_field)
    }
}

private fun LoginFragment.validFieldUser() {
    mBinding.edTxtUser.setOnFocusChangeListener { _, focused ->
        if (!focused) {
            mBinding.inputLayoutEdTxtUser.helperText = validTxtUser()
        }
    }
}

private fun LoginFragment.validTxtUser(): String {
    val txtUser = mBinding.edTxtUser.text.toString()
    if (txtUser.isNullOrEmpty()) {
        return getString(R.string.txt_empty_field)
    }
    return ""
}
