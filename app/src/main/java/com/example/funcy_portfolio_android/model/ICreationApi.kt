package com.example.funcy_portfolio_android.model

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ICreationApi {
    /* 作品の投稿（個人） */
    @POST("article")
    fun registerCreationData(@Body creation:CreationData): Call<CreationData>

}