package com.example.funcy_portfolio_android.ui.workDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.ItemImageCarouselBinding
import com.example.funcy_portfolio_android.model.data.ImageData

class ImageCarouselAdapter(private val imageList: List<ImageData>) : RecyclerView.Adapter<ImageCarouselAdapter.ImageCarouselViewHolder>() {
    class ImageCarouselViewHolder(val binding: ItemImageCarouselBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageCarouselViewHolder {
        val binding = ItemImageCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageCarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageCarouselViewHolder, position: Int) {
        val item = imageList.getOrNull(position)
        if (item != null) {
            holder.binding.imageView.load(item.image) {
                error(R.drawable.img_work_detail_thumbnail)
            }
        } else {
            holder.binding.imageView.load(R.drawable.img_work_detail_thumbnail)
        }
    }

    override fun getItemCount() = imageList.size

    class MyDataDiffCallback : DiffUtil.ItemCallback<ImageData>() {
        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem == newItem
        }
    }
}