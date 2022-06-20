package com.example.funcy_portfolio_android.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ICreationApi {
    /* 作品の投稿（個人） */
    @Headers("token:Token1")
    @POST("work")
    fun registerCreationData(@Body creation:CreationData): Call<CreationData>

}