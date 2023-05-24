package com.binar.pra_project.view.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.pra_project.R
import com.binar.pra_project.databinding.FragmentHomeBinding
import com.binar.pra_project.view.adapter.NewsAdapter
import com.binar.pra_project.view.adapter.ProdukAdapter
import com.binar.pra_project.viewmodel.HomeViewModel
import com.denzcoskun.imageslider.models.SlideModel


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var  homeVM : HomeViewModel


    val imageList = arrayListOf<SlideModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    private fun setLayoutNewsUpdate(){
        binding.rvNewsUpdate.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)


        homeVM.getUpdateNews()
        homeVM.getlivedatanews().observe(viewLifecycleOwner){
            if (it != null) {
                binding.rvNewsUpdate.adapter = NewsAdapter(it)
            }
        }
    }

    private fun setLayoutProduct(){
        binding.rvProduct.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)

        homeVM.getAllDataProduk()
        homeVM.getlivedataproduk().observe(viewLifecycleOwner){
            if (it != null){
                binding.rvProduct.adapter = ProdukAdapter(it)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.bottom_nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){

        R.id.cart -> {
            findNavController().navigate(R.id.action_homeFragment_to_keranjangFragment)
            return true
        }
            R.id.favorite -> {
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
                return true
            }
            R.id.account -> {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                return true
            }

            else -> {super.onOptionsItemSelected(item)}
        }
    }


}