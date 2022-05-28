package com.example.examenintercam.utils.dialog

import android.app.Activity
import com.example.examenintercam.R
import com.example.examenintercam.databinding.AlertDialogLoginObjectBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object AlertDialogLoginObject {

    fun showDialog(context: Activity) {
        val dialogView = context.layoutInflater.inflate(R.layout.alert_dialog_login_object, null, false)
        val dialog = MaterialAlertDialogBuilder(context, com.google.android.material.R.style.AlertDialog_AppCompat).setView(dialogView).show()
        val bindingDialog = AlertDialogLoginObjectBinding.bind(dialogView!!)
        bindingDialog.apply {
           btnAccept.setOnClickListener {
               dialog.dismiss()
           }
        }
    }
}