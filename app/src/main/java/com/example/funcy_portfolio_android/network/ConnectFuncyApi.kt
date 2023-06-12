package com.example.funcy_portfolio_android.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val URL = "http://10.124.56.217:9000/"

val gson = GsonBuilder()
    .serializeNulls()
    .create()

private val retrofit = Retrofit.Builder()
    .baseUrl(URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

object ApiService {
    val service: FuncyApi by lazy {
        retrofit.create(FuncyApi::class.java)
    }
}