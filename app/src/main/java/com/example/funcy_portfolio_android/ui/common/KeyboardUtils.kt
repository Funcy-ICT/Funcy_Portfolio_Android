package com.example.funcy_portfolio_android.ui.common

import android.content.Context
import android.os.IBinder
import android.view.inputmethod.InputMethodManager

object KeyboardUtils {
    fun showoffKeyboard(context: Context, windowToken: IBinder) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}