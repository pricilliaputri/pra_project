package com.binar.pra_project.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.pra_project.R
import com.binar.pra_project.databinding.FragmentCartBinding
import com.binar.pra_project.view.adapter.CartAdapter
import com.binar.pra_project.viewmodel.CartViewModel


class CartFragment : Fragment() {

    private lateinit var binding : FragmentCartBinding
    private lateinit var vmCart : CartViewModel
    lateinit var pref : SharedPreferences
    lateinit var idUser: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vmCart = ViewModelProvider(this).get(CartViewModel::class.java)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        idUser = pref.getString("id","").toString()

    }

    fun getDataCart(userId : String){
        vmCart = ViewModelProvider(this).get(CartViewModel::class.java)
        vmCart.getCart(userId)
        vmCart.getlivedatacart().observe(viewLifecycleOwner, Observer{
            binding.rvCart.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,false)
            binding.rvCart.adapter = CartAdapter(it)
        })

    }

    override fun onStart() {
        super.onStart()
        getDataCart(idUser)

    }


}