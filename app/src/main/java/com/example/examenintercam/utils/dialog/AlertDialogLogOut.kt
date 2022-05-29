package com.example.examenintercam.utils.dialog

import android.app.Activity
import com.example.examenintercam.R
import com.example.examenintercam.databinding.AlertDialogLogOutObjectBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object AlertDialogLogOut {

    fun showDialog(context: Activity, btnAccept: () -> Unit){
        val dialogView = context.layoutInflater.inflate(R.layout.alert_dialog_log_out_object, null, false)
        val dialog = MaterialAlertDialogBuilder(context, androidx.appcompat.R.style.AlertDialog_AppCompat ).setView(dialogView).show()
        val bindingDialog = AlertDialogLogOutObjectBinding.bind(dialogView!!)
        bindingDialog.apply {
            btnOk.setOnClickListener {
                btnAccept()
                dialog.dismiss()
            }

            btnExit.setOnClickListener {
                dialog.dismiss()
            }

            dialog.setCancelable(false)
        }
    }

}