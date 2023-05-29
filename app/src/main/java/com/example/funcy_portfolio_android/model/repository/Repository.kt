package com.example.funcy_portfolio_android.model.repository

import com.example.funcy_portfolio_android.model.data.CreationData
import com.example.funcy_portfolio_android.network.ICreationApi
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class Repository {
    /* （本物のサーバができるまで）自分のPCのローカルipアドレスにする */
    val URL = "http://192.168.3.17:9000/"

    val gson = GsonBuilder()
        .serializeNulls()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val service = retrofit.create(ICreationApi::class.java)

    suspend fun registerCreation(creation: CreationData): String? {
        val data = creation
        var res = ""

        withContext(IO) {
            try {
                val response = service.registerCreationData(data).execute().body()
                if (response != null) {
                    res = response.toString()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return res
    }
}