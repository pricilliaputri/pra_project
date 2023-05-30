package com.binar.pra_project.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.pra_project.databinding.ItemCartBinding
import com.binar.pra_project.databinding.ItemHistoryTransactionBinding
import com.binar.pra_project.model.CartItem
import com.bumptech.glide.Glide

class CartAdapter (private var listCart : List<CartItem>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(var binding : ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindCart(itemcart : CartItem){
            binding.cart = itemcart
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCart(listCart[position])
        Glide.with(holder.itemView)
            .load(listCart[position].productImage)
            .into(holder.binding.imgProduct)
    }

    override fun getItemCount(): Int {
       return listCart.size
    }
}