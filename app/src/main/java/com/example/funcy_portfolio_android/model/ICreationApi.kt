package com.example.funcy_portfolio_android.model

import retrofit2.Call
import retrofit2.http.*

interface ICreationApi {
    /* 作品の投稿（個人） */
    @Headers("token:Token1")
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

    //作品検索結果取得
    @GET("search/{word}")
    suspend fun getSearchResult(
        @Header("token") token: String
    ): List<WorkData>

}