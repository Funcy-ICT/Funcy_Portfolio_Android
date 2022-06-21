package com.example.funcy_portfolio_android.ui.creationDetail.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Creation(
    val title: String,
    val description: String,
    val images: List<String>,
    val url: String,
    val movieUrl: String,
    val tags: List<String>,
    val security: Int
)
