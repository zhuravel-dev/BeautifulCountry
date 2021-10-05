package com.example.studyprojectrnc.presentation

import android.app.AlertDialog
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.presentation.secondScreen.SecondFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_detail_screen.view.*
import timber.log.Timber

class ImageDetailScreenDialog(private val secondFragment: SecondFragment) {

    private lateinit var isDialog: AlertDialog

    fun startShowing(imageView: String?, views: Int?) {
        val inflater = secondFragment.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_detail_screen, null)
        Picasso.get().load(imageView).into(dialogView.ivDetailImage, object : Callback {
            override fun onSuccess() {
                Timber.i("DialogDetail: onSuccess")
            }
            override fun onError(e: Exception?) {
                Timber.i("DialogDetail: onError")
            }
        })
        dialogView.btnExitDialog.setOnClickListener {
            isDismiss()
        }
        dialogView.tvViews.text = views.toString()
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