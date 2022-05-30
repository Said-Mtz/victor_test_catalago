package com.example.examenintercam.ui.fragments.login

import android.util.Patterns
import androidx.navigation.fragment.findNavController
import com.example.examenintercam.R
import com.example.examenintercam.utils.dialog.AlertDialogLoginObject
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

fun LoginFragment.initElements() {
    mBinding.apply {

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

private fun LoginFragment.validEmail(email: String): Boolean {
    val pattern: Pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}
