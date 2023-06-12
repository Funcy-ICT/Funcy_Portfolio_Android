package com.example.funcy_portfolio_android.model.repository

import com.example.funcy_portfolio_android.model.data.CreationData
import com.example.funcy_portfolio_android.network.ApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.io.IOException

class Repository() {
    val service = ApiService.service
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
