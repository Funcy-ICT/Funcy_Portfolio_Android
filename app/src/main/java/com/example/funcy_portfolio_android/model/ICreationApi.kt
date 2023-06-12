package com.example.funcy_portfolio_android.model

import retrofit2.Call
import retrofit2.http.*


interface ICreationApi {
    /* 作品の投稿（個人） */
    @Headers("token:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNmI4ZDgwMWQtZDE1My00MzQyLThlYzEtZDdhOTRiMGRhMzNiIiwiaWF0IjoxNjg2NTUzMzMzfQ.8qdMdDwu_irdE4xvXZOWH5U4GLrE7rWvbj0zZTiqjSc")
    @POST("work")
    fun registerCreationData(@Body creation:CreationData): Call<CreationData>

    //作品詳細取得
    @GET("work/{workID}")
    suspend fun getCreationDetail(
        @Header("token") token: String,
        @Path("workID") creationID: String
    ): CreationData

    //登録データ送信
    @POST("sign/up")
    suspend fun sendUserRegistration(
        @Header("token") token: String,
        @Body signupData : SignupData
    ): SignupData
}