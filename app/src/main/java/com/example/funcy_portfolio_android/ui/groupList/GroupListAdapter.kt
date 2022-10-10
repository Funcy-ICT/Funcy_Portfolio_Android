package com.example.funcy_portfolio_android.ui.groupList

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class GroupListAdapter(
    private val groupImages: ArrayList<Int>,
    private val groupNames: ArrayList<String>
) : RecyclerView.Adapter<GroupListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}