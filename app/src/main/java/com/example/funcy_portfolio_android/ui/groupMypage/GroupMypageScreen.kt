package com.example.funcy_portfolio_android.ui.groupMypage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.funcy_portfolio_android.model.data.WorkData
import com.example.funcy_portfolio_android.model.data.WorkDataList

@Composable
fun GroupMyPageScreen(viewModel: GroupMypageViewModel) {
    val workDataList by remember { mutableStateOf(viewModel.workList) }
    GroupMyPageList(workDataList = workDataList)
}

@Composable
fun GroupMyPageList(workDataList: WorkDataList){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 先頭のmargin用
        items(2) {}

        items(workDataList.works) {
            GroupMyPageItem(it)
        }

        // 最後のmargin用
        items(2) {}
    }
}

@Composable
fun GroupMyPageItem(workData: WorkData, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .background(Color(0xFFF0F3F5), RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Column {
            AsyncImage(
                model = workData.thumbnail,
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .height(140.dp),
                contentScale = ContentScale.Crop

            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = workData.title,
                    maxLines = 1,
                    fontSize = 14.sp
                )
                Text(
                    text = workData.title,
                    fontSize = 12.sp,
                    color = Color(0xFF707F89),
                    maxLines = 1,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGroupList() {
    val workDataList = WorkDataList(
        listOf(
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
    MaterialTheme {
        GroupMyPageList(workDataList = workDataList)
    }
}
