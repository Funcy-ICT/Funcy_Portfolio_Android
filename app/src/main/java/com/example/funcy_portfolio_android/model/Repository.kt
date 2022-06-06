package com.example.funcy_portfolio_android.model

import android.util.Log
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class Repository{
    /* （本物のサーバができるまで）自分のPCのローカルipアドレスにする */
    val URL = "http://10.124.58.139:8080/"

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
                    /* TODO: res内のarticleIDを表示や保存したい場合，別の処理を加える */
                    res = response.toString()
                    Log.e("res",res)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return res
    }

}
