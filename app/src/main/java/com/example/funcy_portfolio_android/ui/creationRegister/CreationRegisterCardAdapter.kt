package com.example.funcy_portfolio_android.ui.creationRegister

import android.content.DialogInterface
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.R
import com.example.funcy_portfolio_android.databinding.ItemAddimageCardBinding


class CreationRegisterCardAdapter (private val initList: List<Uri>) : RecyclerView.Adapter<CreationRegisterCardAdapter.ViewHolder>(){

    var itemList: MutableList<Uri> = initList.toMutableList()


    class ViewHolder(val binding: ItemAddimageCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val layoutInfater = LayoutInflater.from(parent.context)
        val binding: ItemAddimageCardBinding = DataBindingUtil.inflate<ItemAddimageCardBinding>(LayoutInflater.from(parent.context), R.layout.item_addimage_card, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.ivThumbnail.setImageURI(item)
        holder.binding.btCancel.setImageResource(R.drawable.bt_cancel_48dp)
        //holder.binding.btCancel.setOnClickListener{
          //  onButtonClick(position)
        //}

        // 削除ボタンをクリック
        holder.binding.btCancel.setOnClickListener() {
            fun onClick(view: View?) {
                val adapterPosition = holder.adapterPosition
                if (adapterPosition != -1) {
                    itemList.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                }
            }
        }


    }
    override fun getItemCount() = itemList.size

    //private fun onButtonClick(position: Int){
      //  Log.i("Button", position.toString())
       // itemList.removeAt(position)
    //}
}

private fun ImageButton.setOnClickListener(onClickListener: DialogInterface.OnClickListener) {

}


// ビューホルダー
class CreationRegisterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // ビューに配置されたウィジェットへの参照を保持しておくためのフィールド
    var bt_del: ImageButton

    init {
        // ウィジェットへの参照を取得
        bt_del = view.findViewById(R.id.btCancel)
    }
}


