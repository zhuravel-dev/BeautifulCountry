package com.example.studyprojectrnc.ui.dialogDetail

import android.app.AlertDialog
import android.util.Log
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.ui.fragments.SecondFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_detail_screen.view.*
import java.lang.Exception

class DialogDetail(private val secondFragment: SecondFragment) {

    private lateinit var isDialog: AlertDialog

    fun startShowing(imageView: String?) {
        val inflater = secondFragment.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_detail_screen, null)
        Picasso.get().load(imageView).into(dialogView.ivDetailImage, object : Callback {
            override fun onSuccess() {
                Log.i("TAG", "onSuccess")
            }
            override fun onError(e: Exception?) {
                Log.i("TAG", "onError")
            }
        })
        dialogView.btnExitDialog.setOnClickListener {
            isDismiss()
        }
        val builder = AlertDialog.Builder(secondFragment.requireContext())
        builder.setView(dialogView)
        builder.setCancelable(false)
        isDialog = builder.create()
        isDialog.show()
    }

    private fun isDismiss() {
        isDialog.dismiss()
    }
}