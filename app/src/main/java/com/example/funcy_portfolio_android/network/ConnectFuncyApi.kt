package com.example.funcy_portfolio_android.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val URL = "https://funcy.tennkyustudio.com/"

private val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

private val httpClient = OkHttpClient.Builder()
    .addInterceptor(logger)
    .build()

val gson = GsonBuilder()
    .serializeNulls()
    .create()

private val retrofit = Retrofit.Builder()
    .baseUrl(URL)
    .client(httpClient)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

object ApiService {
    val service: FuncyApi by lazy {
        retrofit.create(FuncyApi::class.java)
    }
}