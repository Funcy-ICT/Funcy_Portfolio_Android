package com.example.funcy_portfolio_android.model.localDataSource

import android.content.Context
import android.content.SharedPreferences

enum class Keys {
    USERID,
}

class UserIdSavePref(
    private val context: Context
) {

    companion object{
        const val KEY_USER_DATA = "funcy_user_setting"
    }

    private lateinit var sharedPreferences: SharedPreferences

    fun savePrefUserId(key: String, value: String){
        sharedPreferences = context.getSharedPreferences(KEY_USER_DATA, Context.MODE_PRIVATE)
        val prefEditor = sharedPreferences.edit().apply {
            putString(key, value)
            apply()
        }
    }

    fun readPrefUserId(key: String) : String? {
        val defValue: String? = null
        sharedPreferences = context.getSharedPreferences(KEY_USER_DATA, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, defValue)
    }
}