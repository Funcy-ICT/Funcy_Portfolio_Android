package com.example.funcy_portfolio_android.ui.creationDetail.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CreationDetailService {
    @GET("/work/{workID}")
    suspend fun getCreationDetail(
        @Header("token") token:String,
        @Path("workID") creationID:String
    ): Creation
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("http://10.124.59.236:8080")
    .build()

object CreationDetailNetwork{
    val creationDetail: CreationDetailService by lazy{
        retrofit.create(CreationDetailService::class.java)
    }
}