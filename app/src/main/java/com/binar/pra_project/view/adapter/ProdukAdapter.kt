package com.binar.pra_project.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.pra_project.databinding.ItemProdukBinding
import com.binar.pra_project.model.ProductsItem

class ProdukAdapter(private var listproduk : List<ProductsItem>) : RecyclerView.Adapter<ProdukAdapter.ViewHolder>() {
    class ViewHolder(var binding : ItemProdukBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProdukBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.namaProduk.text = listproduk[position].name
        holder.binding.hargaProduk.text = listproduk[position].price
    }

    override fun getItemCount(): Int {
        return listproduk.size
    }
}