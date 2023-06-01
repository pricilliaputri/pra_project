package com.binar.pra_project.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.binar.pra_project.R
import com.binar.pra_project.databinding.FragmentDetailSecondBinding
import com.binar.pra_project.viewmodel.HomeViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailSecondFragment : Fragment() {
    private lateinit var binding : FragmentDetailSecondBinding
    private lateinit var homeVm: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentDetailSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeVm = ViewModelProvider(this).get(HomeViewModel::class.java)
        val id = arguments?.getInt("id")
        if (id != null) {
            homeVm.getDetailProductSecond(id)
            observeDetailProductSecond()
        }
    }

    private fun observeDetailProductSecond() {
        homeVm.liveDetailProdukSecond.observe(viewLifecycleOwner) {
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