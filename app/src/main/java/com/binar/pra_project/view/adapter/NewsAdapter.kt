package com.binar.pra_project.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.binar.pra_project.R
import com.binar.pra_project.databinding.ItemNewsBinding
import com.binar.pra_project.databinding.ItemProdukBinding
import com.binar.pra_project.model.NewsUpdateItem
import com.bumptech.glide.Glide

class NewsAdapter(private var listNews : List<NewsUpdateItem>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(var binding : ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindNews(itemNews: NewsUpdateItem) {
            binding.news = itemNews
            binding.cardView.setOnClickListener {
                val bundle = Bundle().apply {
                    putInt("ID", itemNews.idNews.toString().toInt())
                }
               it.findNavController().navigate(R.id.action_homeFragment_to_detailNewsFragment, bundle)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindNews(listNews[position])
        Glide.with(holder.itemView).load(listNews[position].newsImage).into(holder.binding.imgNewsUpdate)




    }

    override fun getItemCount(): Int {
        return listNews.size
    }
}