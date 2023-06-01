package com.binar.pra_project.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.binar.pra_project.R
import com.binar.pra_project.databinding.ItemProdukBinding
import com.binar.pra_project.model.ProductsItem
import com.bumptech.glide.Glide

class SecondProdukAdapter(private var listsecond : List<ProductsItem>) : RecyclerView.Adapter<SecondProdukAdapter.ViewHolder>()  {
    class ViewHolder(var binding : ItemProdukBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindProduct(itemProduct : ProductsItem){
            binding.product = itemProduct
            binding.cardView.setOnClickListener {
                val bundle = Bundle().apply {
                    putInt("id", itemProduct.idProduct.toString().toInt())
                }
                it.findNavController().navigate(R.id.action_homeFragment_to_detailSecondFragment, bundle)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProdukBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindProduct(listsecond[position])
        Glide.with(holder.itemView).load(listsecond[position].productImage).into(holder.binding.imgProduct)



    }

    override fun getItemCount(): Int {
        return listsecond.size
    }


}