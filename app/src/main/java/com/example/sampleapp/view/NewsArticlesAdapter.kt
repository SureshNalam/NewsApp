package com.example.sampleapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sampleapp.R
import com.example.sampleapp.databinding.NewsItemBinding
import com.example.sampleapp.models.Article
import com.example.sampleapp.models.TopHeadLines

class NewsArticlesAdapter(private val data: TopHeadLines) :
    RecyclerView.Adapter<NewsArticlesAdapter.NewsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(data.articles[position])
    }

    override fun getItemCount(): Int {
        return data.articles.size
    }

    class NewsItemViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            with(article) {
                title?.let {
                    binding.textViewTitle.apply {
                        text = it
                        visibility = View.VISIBLE
                    }
                }
                description?.let {
                    binding.textViewDescription.apply {
                        text = it
                        visibility = View.VISIBLE
                    }
                }
                urlToImage?.let {
                    binding.imageViewArticle.visibility = View.VISIBLE
                    Glide.with(binding.root.context)
                        .load(it)
                        .into(binding.imageViewArticle)
                }

            }
        }
    }
}