package com.example.funcy_portfolio_android.model.data

data class WorkDataList(
    val works: List<WorkData>,
)

data class WorkData(
    val workID: String,
    val title: String,
    val thumbnail: String
)
