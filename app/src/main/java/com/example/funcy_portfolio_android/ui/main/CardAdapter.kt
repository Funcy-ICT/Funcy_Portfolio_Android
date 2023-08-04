package com.example.funcy_portfolio_android.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.databinding.CreateCardBinding
import com.example.funcy_portfolio_android.model.data.WorkData

class CardAdapter:ListAdapter<WorkData, CardAdapter.WorkDataViewHolder>(DiffCallBack){

    class WorkDataViewHolder(private var binding: CreateCardBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(workDataList: WorkData){
                    binding.work = workDataList
                    binding.executePendingBindings()
                }
            }

    companion object DiffCallBack : DiffUtil.ItemCallback<WorkData>(){
        override fun areItemsTheSame(oldItem: WorkData, newItem: WorkData): Boolean {
            return oldItem.work_id == newItem.work_id
        }

        override fun areContentsTheSame(oldItem: WorkData, newItem: WorkData): Boolean {
            return oldItem.images == newItem.images
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





//@BindingAdapter
//fun bindArticle(article: ImageView, articleUrl: String?){
//    articleUrl?.let{
//        val articleUrl = articleUrl.toUri().buildUpon().scheme("https").build()
//        article.load(articleUrl)
//    }
//}

/*
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
}*/