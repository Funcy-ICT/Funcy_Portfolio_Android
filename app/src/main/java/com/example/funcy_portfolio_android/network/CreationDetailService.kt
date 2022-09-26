package com.example.funcy_portfolio_android.network

import com.example.funcy_portfolio_android.model.CreationData
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CreationDetailService {
    @GET("work/{workID}")
    suspend fun getCreationDetail(
        @Header("token") token: String,
        @Path("workID") creationID: String
    ): CreationData
}

private val gson = GsonConverterFactory.create(GsonBuilder().serializeNulls().create())

private val retrofit = Retrofit.Builder()
    .baseUrl("http://10.124.58.15:8080/")
    .addConverterFactory(gson)
    .build()

object CreationDetailNetwork{
    val creationDetail: CreationDetailService by lazy{
        retrofit.create(CreationDetailService::class.java)
    }
}