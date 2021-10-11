package com.example.funcy_portfolio_android.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.R

class CardAdapter(private val worklist: List<WorkData>) : RecyclerView.Adapter<CardAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.image_view)
        val maintitle: TextView = view.findViewById(R.id.title_text)
        val subtitle: TextView = view.findViewById(R.id.name_text)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutView = LayoutInflater.from(viewGroup.context).inflate(R.layout.create_card, viewGroup, false)
        return ViewHolder(layoutView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val work = worklist[position]
        viewHolder.image.setImageResource(work.image)
        viewHolder.maintitle.text = work.main_title
        viewHolder.subtitle.text = work.sub_title
    }
    override fun getItemCount() = worklist.size
}