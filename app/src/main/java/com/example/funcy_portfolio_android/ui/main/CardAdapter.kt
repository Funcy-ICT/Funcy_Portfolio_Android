package com.example.funcy_portfolio_android.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.funcy_portfolio_android.databinding.CreateCardBinding
import com.example.funcy_portfolio_android.databinding.FragmentMainBinding

class CardAdapter:ListAdapter<ArticleData, CardAdapter.ArticleDataViewHolder>(DiffCallBack){

    class ArticleDataViewHolder(private var binding: CreateCardBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(articleData: ArticleData){
                    binding.article = articleData
                    binding.executePendingBindings()
                }
            }

    companion object DiffCallBack : DiffUtil.ItemCallback<ArticleData>(){
        override fun areItemsTheSame(oldItem: ArticleData, newItem: ArticleData): Boolean {
            return oldItem.articleID == newItem.articleID
        }

        override fun areContentsTheSame(oldItem: ArticleData, newItem: ArticleData): Boolean {
            return oldItem.image == newItem.image
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardAdapter.ArticleDataViewHolder {
        return ArticleDataViewHolder(
            CreateCardBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CardAdapter.ArticleDataViewHolder, position: Int) {
        val articleData = getItem(position)
        holder.bind(articleData)
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