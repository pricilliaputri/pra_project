package com.binar.pra_project.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.binar.pra_project.databinding.FragmentDetailProductBinding
import com.binar.pra_project.model.ProductsItem
import com.binar.pra_project.viewmodel.HomeViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailProdukFragment : Fragment() {
    private lateinit var binding: FragmentDetailProductBinding
    private lateinit var homeVm: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeVm = ViewModelProvider(this).get(HomeViewModel::class.java)


        val id = arguments?.getInt("ID")
        if (id != null) {
            homeVm.getDetailProduk(id)
            observeDetailProduct()
        }

//        val getData = arguments?.getSerializable("detail_product") as ProductsItem
//        val nama = getData.name
//        val image = getData.productImage
//        val harga = getData.price
//        val description = getData.description
//
//        binding.NamaBarang.text = nama
//        binding.HargaBarang.text = harga
//        binding.DescripBarang.text = description
//
//        Glide.with(binding.root).load(image).into(binding.ImageProduct)

    }

    private fun observeDetailProduct() {
        homeVm.liveDetailProduk.observe(viewLifecycleOwner) {
            binding.apply {
                if (it != null) {
                    binding.NamaBarang.text = it.name.toString()
                    binding.HargaBarang.text = it.price.toString()
                    binding.DescripBarang.text = it.description.toString()
                    Glide.with(requireContext())
                        .load("${it.productImage}")
                        .into(binding.ImageProduct)
                }
            }
        }
    }
}