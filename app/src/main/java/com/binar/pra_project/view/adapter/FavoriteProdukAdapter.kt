package com.binar.pra_project.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.pra_project.Util
import com.binar.pra_project.databinding.ItemFavouriteBinding
import com.binar.pra_project.databinding.ItemNewsBinding
import com.binar.pra_project.model.FavouriteItem
import com.bumptech.glide.Glide

class FavoriteProdukAdapter(private val listfavourite  : List<FavouriteItem>) : RecyclerView.Adapter<FavoriteProdukAdapter.ViewHolder>() {
    class ViewHolder(private var binding : ItemFavouriteBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(favorite:FavouriteItem){
            Glide.with(itemView).load(favorite.productImage).into(binding.imgFavorite)
            binding.txtFavNamaProduk.text = favorite.name
            val price = Util.getPriceIdFormat(favorite.price.toString())
            binding.txtFavHarga.text = price
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(listfavourite[position])

    }

    override fun getItemCount(): Int {
        return listfavourite.size

    }
}