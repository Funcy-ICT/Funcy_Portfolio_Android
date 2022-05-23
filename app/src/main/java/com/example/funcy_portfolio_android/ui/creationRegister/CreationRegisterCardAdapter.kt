package com.example.funcy_portfolio_android.ui.creationRegister

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.ItemAddimageCardBinding

class CreationRegisterCardAdapter (private val initList: List<Uri>) : RecyclerView.Adapter<CreationRegisterCardAdapter.ViewHolder>(){

    private var itemList: MutableList<Uri> = initList.toMutableList()

    class ViewHolder(val binding: ItemAddimageCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemAddimageCardBinding = DataBindingUtil.inflate<ItemAddimageCardBinding>(LayoutInflater.from(parent.context), R.layout.item_addimage_card, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.ivThumbnail.setImageURI(item)
        holder.binding.btCancel.setImageResource(R.drawable.bt_cancel_48dp)
        holder.binding.btCancel.setOnClickListener{
            onButtonClick(holder)
        }
    }
    override fun getItemCount() = itemList.size

    //サムネ削除ボタン
    private fun onButtonClick(holder: ViewHolder) {
        if (itemList.size != 1) {
            itemList.removeAt(holder.getAdapterPosition())
            notifyItemRemoved(holder.getAdapterPosition())
        } else {
            //選択した画像が0になる場合に初期画像[no_image]を設定
            itemList.set(0, Uri.parse("android.resource://com.example.funcy_portfolio_android/drawable/img_no_image"))
            notifyItemChanged(holder.getAdapterPosition())
        }
    }
}



