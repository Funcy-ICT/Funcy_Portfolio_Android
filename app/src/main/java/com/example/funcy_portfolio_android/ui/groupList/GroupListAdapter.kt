package com.example.funcy_portfolio_android.ui.groupList

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.R

class GroupListAdapter(private val groupList: List<GroupListData>) : RecyclerView.Adapter<GroupListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val groupIcon: ImageView = view.findViewById(R.id.group_image)
        val groupName: TextView = view.findViewById(R.id.group_name)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutView = LayoutInflater.from(viewGroup.context).inflate(R.layout.create_group, viewGroup, false)
        return ViewHolder(layoutView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val group = groupList[position]
        viewHolder.image.setImageResource(group.)
    }
}