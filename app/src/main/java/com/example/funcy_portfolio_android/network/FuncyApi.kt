package com.example.funcy_portfolio_android.network

import com.example.funcy_portfolio_android.model.data.AuthData
import com.example.funcy_portfolio_android.model.data.SignupData
import com.example.funcy_portfolio_android.model.data.UserIdData
import com.example.funcy_portfolio_android.model.data.WorkData
import com.example.funcy_portfolio_android.model.data.WorkDataList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface FuncyApi {
    /* 作品の投稿（個人） */
    @Headers("token:Token1")
    @POST("work")
    fun registerWorkData(@Body work: WorkData): Call<WorkData>

    //作品詳細取得
    @GET("work/{workID}")
    suspend fun getWorkDetail(
        @Header("token") token: String,
        @Path("workID") workId: String
    ): WorkData

    //登録データ送信
    @POST("sign/up")
    suspend fun sendUserRegistration(
        @Body signupData: SignupData,
    ): Response<UserIdData>

    @POST("auth/code")
    suspend fun sendAuthCode(
        @Body authData: AuthData
    ): Response<Void>

    @GET("works/10") // 取得する作品数
    suspend fun getWorks(
        @Header("token") token: String
    ): List<WorkDataList>
}