package com.binar.pra_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binar.pra_project.R
import com.binar.pra_project.databinding.FragmentProdukBinding


class ProdukFragment : Fragment() {

    private lateinit var binding: FragmentProdukBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProdukBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}