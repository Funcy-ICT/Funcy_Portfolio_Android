package com.example.funcy_portfolio_android.model

import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class Repository{
    /* （本物のサーバができるまで）自分のPCのローカルipアドレスにする */
    val URL = "http://10.124.58.146:8003/"

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
