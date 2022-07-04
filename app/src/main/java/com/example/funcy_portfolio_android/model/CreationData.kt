package com.example.funcy_portfolio_android.model

data class CreationData(
    val title: String,
    val description: String,
    val images: List<ImageData>,
    val work_url: String,
    val movie_url: String,
    val tags: List<TagData>,
    val group: String?,
    val security: Int
)

data class ImageData(
    val image: String
)

data class TagData(
    val tag: String
)