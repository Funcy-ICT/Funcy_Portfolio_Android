package com.example.funcy_portfolio_android.ui.groupMypage

import androidx.lifecycle.ViewModel
import com.example.funcy_portfolio_android.model.data.WorkData
import com.example.funcy_portfolio_android.model.data.WorkDataList

class GroupMypageViewModel: ViewModel() {
    val workList = WorkDataList(
        works = listOf(
            WorkData(
                workID = "",
                userID = "",
                user_name = "",
                title = "",
                thumbnail = "",
                icon = ""
            )
        )
    )
}