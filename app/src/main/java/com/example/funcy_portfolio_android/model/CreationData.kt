package com.example.funcy_portfolio_android.model

data class CreationData(
    val images: List<ImageData>,
    val security: Int,
    val description: String,
    val title: String,
    val tags: List<TagData>,
    val group: String?
)

data class ImageData(
    val image: String
)

data class TagData(
    val tag: String
)