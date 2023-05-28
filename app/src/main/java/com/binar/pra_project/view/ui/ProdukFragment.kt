package com.binar.pra_project.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binar.pra_project.databinding.FragmentProdukBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProdukFragment : Fragment() {

    private lateinit var binding: FragmentProdukBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProdukBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}