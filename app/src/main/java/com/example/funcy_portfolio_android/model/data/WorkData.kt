package com.example.funcy_portfolio_android.model.data

data class WorksData(
    val works: List<WorkData>
)
data class WorkData(
    val work_id: Int,
    val title: String,
    val images: String
)
