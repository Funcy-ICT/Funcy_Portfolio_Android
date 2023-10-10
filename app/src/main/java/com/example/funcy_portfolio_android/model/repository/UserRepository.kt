package com.example.funcy_portfolio_android.model.repository

import android.content.Context
import com.example.funcy_portfolio_android.model.data.AuthData
import com.example.funcy_portfolio_android.model.data.SignupData
import com.example.funcy_portfolio_android.model.localDataSource.SharedPref
import com.example.funcy_portfolio_android.network.ConnectFuncyApi

class UserRepository {
    private val service = ConnectFuncyApi.ApiService.service

    fun saveUserId(context: Context, key: String, value: String) = SharedPref(context = context).saveSharedPrefString(key, value)

    fun readUserId(context: Context, key: String) = SharedPref(context = context).readSharedPref(key)

    suspend fun userRegistration(signupData: SignupData) = service.sendUserRegistration(signupData = signupData)

    suspend fun userAuthentication(authData: AuthData) = service.sendAuthCode(authData = authData)
}