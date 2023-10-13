package com.example.funcy_portfolio_android.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val URL = "http://192.168.11.10:9000/"
private val IMAGE_URL = "http://192.168.11.10:3004/"

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

private val retrofitImage = Retrofit.Builder()
    .baseUrl(IMAGE_URL)
    .client(httpClient)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

object ApiService {
    val service: FuncyApi by lazy {
        retrofit.create(FuncyApi::class.java)
    }

    val serviceImage: FuncyApi by lazy {
        retrofitImage.create(FuncyApi::class.java)
    }
}