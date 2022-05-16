package com.example.funcy_portfolio_android.ui.creationRegister

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.ItemAddimageCardBinding

/* TODO:画像の型は適宜変更する(現在はIntにしとく)  */
class CreationRegisterCardAdapter (private val initList: List<Uri>) : RecyclerView.Adapter<CreationRegisterCardAdapter.ViewHolder>(){

    var itemList: List<Uri> = initList

    class ViewHolder(val binding: ItemAddimageCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInfater = LayoutInflater.from(parent.context)
        val binding: ItemAddimageCardBinding = DataBindingUtil.inflate<ItemAddimageCardBinding>(LayoutInflater.from(parent.context), R.layout.item_addimage_card, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.ivThumbnail.setImageURI(item)
        holder.binding.btCancel.setImageResource(R.drawable.bt_cancel_48dp)
    }
    override fun getItemCount() = itemList.size

    fun setImage(image: List<Uri>){
        this.itemList = image
        /** 削除処理 */
    }
}