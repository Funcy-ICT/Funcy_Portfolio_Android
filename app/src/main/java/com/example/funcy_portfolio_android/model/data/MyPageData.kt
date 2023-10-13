package com.example.funcy_portfolio_android.model.data

/**
 * マイページ (mypage) のデータクラス
 * @param works: 中身はWorkDataList.ktを参照してください
 */
data class MyPageData(
    val icon: String,
    val header: String,
    val user_description: String,
    val sns: List<String>,
    val group: List<String>,
    val skills: List<String>,
    val displayName: String,
    val works: WorkDataList
)