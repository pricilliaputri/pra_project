package com.binar.pra_project.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binar.pra_project.databinding.FragmentDetailBinding
import com.binar.pra_project.model.ProductsItem
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding : FragmentDetailBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getData = arguments?.getSerializable("detail_product") as ProductsItem
        val nama = getData.name
        val image = getData.productImage
        val harga = getData.price
        val description = getData.description

        binding.NamaBarang.text = nama
        binding.HargaBarang.text = harga
        binding.DescripBarang.text = description

        Glide.with(binding.root).load(image).into(binding.ImageProduct)




    }
}