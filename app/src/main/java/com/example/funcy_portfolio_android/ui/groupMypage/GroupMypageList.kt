package com.example.funcy_portfolio_android.ui.groupMypage

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.model.data.WorkDataList

@Composable
fun GroupMyPageList(workDataList: List<WorkDataList>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 先頭のmargin用
        items(2) {}

        items(workDataList) {
            GroupMyPageItem(it)
        }

        // 最後のmargin用
        items(2) {}
    }
}

@Composable
fun GroupMyPageItem(workDataList: WorkDataList, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .background(Color(0xFFF0F3F5), RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Column {
            Image(
                painter = painterResource(id = workDataList.work_id),
                contentDescription = null,
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
                    text = workDataList.title,
                    maxLines = 1,
                    fontSize = 14.sp
                )
                Text(
                    text = workDataList.images,
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
    val workDataList = listOf<WorkDataList>(
        WorkDataList(R.drawable.garden_strand, "garden_strand", "ネックレス"),
        WorkDataList(R.drawable.gatsby_hat, "gatsby_hat", "ハット"),
        WorkDataList(R.drawable.stella_sunglasses, "stella_sunglasses", "グラス"),
        WorkDataList(R.drawable.strut_earrings, "strut_earrings", "イヤリング"),
        WorkDataList(R.drawable.vagabond_sack, "vagabond_sack", "リュックサック"),
        WorkDataList(R.drawable.varsity_socks, "varsity_sovks", "ソックス"),
        WorkDataList(R.drawable.whitey_belt, "whitey_belt", "ベルト"),
        WorkDataList(R.drawable.copper_wire_rack, "whitey_belt", "ラック"),
        WorkDataList(R.drawable.gilt_desk_trio, "whitey_belt", "小物入れ"),
        WorkDataList(R.drawable.shrug_bag, "whitey_belt", "バッグ")
    )
    MaterialTheme {
        GroupMyPageList(workDataList = workDataList)
    }
}
