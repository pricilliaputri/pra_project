package com.binar.pra_project.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.pra_project.databinding.ItemProdukBinding
import com.binar.pra_project.model.ProductsItem
import com.bumptech.glide.Glide

class SecondProdukAdapter(private var listsecond : List<ProductsItem>) : RecyclerView.Adapter<SecondProdukAdapter.ViewHolder>()  {
    var onClickItemSecondProduct : ((ProductsItem)->Unit)? = null
    class ViewHolder(var binding : ItemProdukBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProdukBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.namaProduk.text = listsecond[position].name
        holder.binding.hargaProduk.text = listsecond[position].price
        Glide.with(holder.itemView).load(listsecond[position].productImage).into(holder.binding.imgProduct)

        holder.binding.cardView.setOnClickListener {
            onClickItemSecondProduct?.invoke(listsecond[position])
        }

    }

    override fun getItemCount(): Int {
        return listsecond.size
    }


}