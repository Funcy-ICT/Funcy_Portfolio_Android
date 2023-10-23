package com.example.funcy_portfolio_android.model.data

/**
 * サインアップ（ユーザ情報登録） (signup) のデータクラス
 */
data class SignupData(
    val icon: String,
    val familyName: String,
    val firstName: String,
    val mail: String,
    val password: String,
    val grade: String,
    val course: String,
    val displayName: String
)