package com.example.funcy_portfolio_android.model

import android.accounts.AuthenticatorDescription

data class WorksData(
    val works: List<WorkData>
)

data class WorkData(
    val work_id: Int,
    val title: String,
    val images: String
)
