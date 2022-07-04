package com.example.funcy_portfolio_android.ui.creationDetail.network

import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CreationDetailService {
    @Headers("token: Token1")
    @GET("work/{workID}")
    suspend fun getCreationDetail(
        @Path("workID") creationID: String
    ): Creation
}

private val gson = GsonConverterFactory.create(GsonBuilder().serializeNulls().create())

private val retrofit = Retrofit.Builder()
    .baseUrl("http://10.124.58.142:8080/")
    .addConverterFactory(gson)
    .build()

object CreationDetailNetwork{
    val creationDetail: CreationDetailService by lazy{
        retrofit.create(CreationDetailService::class.java)
    }
}