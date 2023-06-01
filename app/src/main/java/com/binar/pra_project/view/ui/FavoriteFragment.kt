package com.binar.pra_project.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.pra_project.R
import com.binar.pra_project.databinding.FragmentFavoriteBinding
import com.binar.pra_project.view.adapter.FavoriteProdukAdapter
import com.binar.pra_project.view.adapter.ProdukAdapter
import com.binar.pra_project.viewmodel.FavouriteViewModel
import com.binar.pra_project.viewmodel.UserViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var vmFav : FavouriteViewModel
    private lateinit var idUser : String
    private lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        idUser = pref.getString("id","").toString()
        getDataFav(idUser)


    }

    fun getDataFav(userId: String){
        vmFav = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        vmFav.getFavouriteProducts(userId)
        vmFav.listFavourite.observe(viewLifecycleOwner, Observer{
            binding.rvFavorite.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,false)
            binding.rvFavorite.adapter = FavoriteProdukAdapter(it)
        })
    }

    override fun onStart() {
        super.onStart()
        getDataFav(idUser)
    }

}