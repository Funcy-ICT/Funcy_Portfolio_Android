package com.example.funcy_portfolio_android.ui.main

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.funcy_portfolio_android.model.data.WorkDataList

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: WorkDataList?) {
    val adapter = recyclerView.adapter as CardAdapter
    adapter.submitList(data?.works ?: listOf())
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }

}