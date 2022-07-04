package com.example.funcy_portfolio_android.ui.creationDetail.network

data class Creation(
    val URL: String,
    val description: String,
    val images: List<Image>,
    val movie_url: String,
    val security: Int,
    val tags: List<Tag>,
    val title: String
)

data class Image(
    val Image: String
)

data class Tag(
    val Tag: String
)