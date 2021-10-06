package com.example.studyapp

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class ShowAlert(activity: Activity, Title: String, message: String) {
    init {
        val alert =  AlertDialog.Builder(activity)
        alert.setTitle(Title)
            .setMessage(message)
            .setPositiveButton("OK", DialogInterface.OnClickListener{
                dialog, id -> dialog.cancel()
            })
            .show()
    }

}