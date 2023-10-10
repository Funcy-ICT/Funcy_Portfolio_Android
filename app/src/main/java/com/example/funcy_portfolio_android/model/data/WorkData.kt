package com.example.funcy_portfolio_android.model.data

data class WorkData(
    val title: String,
    val description: String,
    val thumbnail: String,
    val user_icon: String,
    val user_name: String,
    val userID: String,
    val images: List<ImageData>,
    val work_url: String,
    val movie_url: String,
    val tags: List<TagData>,
    val group: String,
    val security: Int,
)

data class ImageData(
    val image: String
)

data class TagData(
    val tag: String
)