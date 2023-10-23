package com.example.funcy_portfolio_android.model.data

/**
 * 作品一覧画面 (main) のデータクラス
 */

data class WorkDataList(
    val works: List<WorkData>
)
data class WorkData(
    val workID: String,
    val userID: String,
    val user_name: String,
    val title: String,
    val thumbnail: String,
    val description: String,
    val icon: String,
)
