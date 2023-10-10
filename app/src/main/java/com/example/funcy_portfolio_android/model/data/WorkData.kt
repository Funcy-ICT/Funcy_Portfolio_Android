package com.example.funcy_portfolio_android.model.data

import com.google.gson.annotations.SerializedName

data class WorkData(
    val title: String,
    val description: String,
    val images: List<ImageData>,
    @SerializedName("work_url")
    val workUrl: String,
    @SerializedName("movie_url")
    val movieUrl: String,
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