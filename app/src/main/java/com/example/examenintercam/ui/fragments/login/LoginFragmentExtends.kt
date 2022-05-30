package com.example.examenintercam.ui.fragments.login

import android.util.Patterns
import androidx.navigation.fragment.findNavController
import com.example.examenintercam.R
import com.example.examenintercam.utils.dialog.AlertDialogLoginObject
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

fun LoginFragment.initElements() {
    mBinding.apply {

        /*validFielFocusedUser()
        validFielFocusedPassword()*/

        btnLogin.setOnClickListener {
            val txtUser = edTxtUser.text.toString().trim().trimIndent()
            val txtPassword = edTxtPassword.text.toString().trim().trimIndent()

            val validEmail = validEmail(txtUser)

            if(txtUser.isNotEmpty() && validEmail && txtPassword.isNotEmpty()){
                viewModel.requestUser(txtUser,txtPassword)
                /**Navegar a lista de cerveza**/

            }else{
                validField(txtUser, inputLayoutEdTxtUser)
                validField(txtPassword, inputLayoutEdTxtPassword)
            }

        }
    }
}

fun LoginFragment.validField(string: String, edTxt: TextInputLayout) {
    if (string.isNullOrEmpty()) {
        edTxt.helperText = getString(R.string.txt_empty_field)
    }
}

/*private fun LoginFragment.validFielFocusedUser() {
    mBinding.edTxtUser.setOnFocusChangeListener { _, focused ->
        if (focused) {
            mBinding.inputLayoutEdTxtUser.helperText = null
        }
    }
}

private fun LoginFragment.validFielFocusedPassword() {
    mBinding.edTxtPassword.setOnFocusChangeListener { _, focused ->
        if (focused) {
            mBinding.inputLayoutEdTxtPassword.helperText = null
        }
    }
}

private fun LoginFragment.validFieldUser() {
    mBinding.edTxtUser.setOnFocusChangeListener { _, focused ->
        if (!focused) {
            mBinding.inputLayoutEdTxtUser.helperText = validTxtUser()
        }else{
            mBinding.inputLayoutEdTxtUser.helperText = null
        }
    }
}

private fun LoginFragment.validTxtUser(): String? {
    val txtUser = mBinding.edTxtUser.text.toString()
    if (txtUser.isNullOrEmpty()) {
        return getString(R.string.txt_empty_field)
    }
    return null
}

private fun LoginFragment.validFieldPassword() {
    mBinding.edTxtPassword.setOnFocusChangeListener { _, focused ->
        if (!focused) {
            mBinding.inputLayoutEdTxtUser.helperText = validTxtPassword()
        }else{
            mBinding.inputLayoutEdTxtUser.helperText = null
        }
    }
}

private fun LoginFragment.validTxtPassword(): String? {
    val txtUser = mBinding.edTxtUser.text.toString()
    if (txtUser.isNullOrEmpty()) {
        return getString(R.string.txt_empty_field)
    }
    return null
}*/

private fun LoginFragment.validEmail(email: String): Boolean {
    val pattern: Pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}
