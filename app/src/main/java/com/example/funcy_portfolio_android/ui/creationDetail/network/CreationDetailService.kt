package com.example.funcy_portfolio_android.ui.creationDetail.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CreationDetailService {
    @GET("/work/{workID}")
    suspend fun getCreationDetail(
        @Path("workID") creationID:String
    )
}

object CreationDetailNetwork{
    private val retrofit = Retrofit.Builder()
        .baseUrl("10.124.59.236")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val creationDetail = retrofit.create(CreationDetailService::class.java)
}