package com.binar.pra_project.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.pra_project.databinding.ItemHistoryTransactionBinding
import com.binar.pra_project.model.TransHistoryItem
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.*

class HistoryTransactionAdapter(private var history : List <TransHistoryItem>) : RecyclerView.Adapter<HistoryTransactionAdapter.ViewHolder>() {
    class ViewHolder(var binding : ItemHistoryTransactionBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHistory(itemhistory : TransHistoryItem ){
            binding.itemHistory = itemhistory

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemHistoryTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHistory(history[position])

        Glide.with(holder.itemView)
            .load(history[position].picture)
            .into(holder.binding.imgProduct)

        holder.binding.tvTotal.text = convertToRupiah(history[position].total)
    }

    override fun getItemCount(): Int {
        return history.size
    }


    fun setDataListNewProduct(listProduct : List<TransHistoryItem>){
       history = listProduct
        notifyDataSetChanged()
    }

    fun convertToRupiah(number: Int): String{

        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }
}