package com.berke.internshipproject.utils

import android.app.Dialog
import android.content.Context
import android.widget.LinearLayout
import com.berke.internshipproject.R

class ProgressBar(context: Context) {

    private var mDialog: Dialog = Dialog(context)

    init {
        mDialog.setContentView(R.layout.progress_bar)
        mDialog.setCancelable(false)
        mDialog.window?.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }

    fun show() {
        if (!mDialog.isShowing) mDialog.show()
    }

    fun hide() {
        if (mDialog.isShowing) mDialog.dismiss()
    }
}