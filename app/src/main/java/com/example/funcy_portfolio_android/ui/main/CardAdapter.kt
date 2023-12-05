package com.example.funcy_portfolio_android.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.databinding.CreateCardBinding
import com.example.funcy_portfolio_android.model.data.WorkData

class CardAdapter : ListAdapter<WorkData, CardAdapter.WorkDataViewHolder>(DiffCallBack) {

    class WorkDataViewHolder(private var binding: CreateCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(workData: WorkData) {
            binding.work = workData
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<WorkData>() {
        override fun areItemsTheSame(oldItem: WorkData, newItem: WorkData): Boolean {
            return oldItem.workID == newItem.workID
        }

        override fun areContentsTheSame(oldItem: WorkData, newItem: WorkData): Boolean {
            return oldItem.thumbnail == newItem.thumbnail
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardAdapter.WorkDataViewHolder {
        return WorkDataViewHolder(
            CreateCardBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CardAdapter.WorkDataViewHolder, position: Int) {
        val workData = getItem(position)
        holder.bind(workData)
    }
}