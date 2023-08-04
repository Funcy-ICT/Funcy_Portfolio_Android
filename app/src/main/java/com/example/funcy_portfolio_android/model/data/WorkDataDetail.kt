package com.example.funcy_portfolio_android.model.data

data class WorkDetails(
    val title: String,
    val description: String,
    val images: List<ImageData>,
    val URL: String,
    val movie_url: String,
    val tags: List<TagData>,
    val group: String?,
    val security: Int,
)

data class ImageData(
    val Image: String
)

data class TagData(
    val Tag: String
)