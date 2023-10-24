package com.example.funcy_portfolio_android.model.repository

import com.example.funcy_portfolio_android.model.data.WorkData
import com.example.funcy_portfolio_android.network.ApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import java.io.File
import java.io.IOException

class WorkRepository() {
    private val service = ApiService.service
    private val serviceImage = ApiService.serviceImage

    suspend fun registerWork(work: WorkData): String? {
        val data = work
        var res = ""

        withContext(IO) {
            try {
                val response = service.registerWorkData(data).execute().body()
                if (response != null) {
                    res = response.toString()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return res
    }

    suspend fun getWorkDetail(token: String, workId:String) = service.getWorkDetail(token = token, workId =  workId)

    suspend fun getWork(token: String) = service.getWorks(token = token)

    suspend fun convertImageToUrl(file: List<MultipartBody.Part>): String {
        var res = ""
        withContext(IO) {
            try {
                val response = serviceImage.convertImageToUrl(file).body()
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
