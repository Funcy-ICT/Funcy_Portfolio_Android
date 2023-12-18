package com.example.funcy_portfolio_android.model.data

/**
 * サインアップ（ユーザ情報登録） (signup) のデータクラス
 */
data class SignupData(
    val familyName: String,
    val course: String,
    val displayName: String,
    val firstName: String,
    val grade: String,
    val icon: String,
    val mail: String,
    val password: String
)