package com.example.funcy_portfolio_android.ui.groupMypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.model.data.WorkData

class CardAdapterBefore (private val worklist: List<WorkData>) : RecyclerView.Adapter<CardAdapterBefore.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.image_view)
        val title: TextView = view.findViewById(R.id.title_text)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutView = LayoutInflater.from(viewGroup.context).inflate(R.layout.create_card, viewGroup, false)
        return ViewHolder(layoutView)
    }

    override fun onBindViewHolder(viewHolder: CardAdapterBefore.ViewHolder, position: Int) {
        val work = worklist[position]
        viewHolder.image.setImageResource(work.work_id)
        viewHolder.title.text = work.title
    }
    override fun getItemCount() = worklist.size

}