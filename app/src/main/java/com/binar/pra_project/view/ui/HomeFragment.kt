package com.binar.pra_project.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.pra_project.R
import com.binar.pra_project.databinding.FragmentHomeBinding
import com.binar.pra_project.view.adapter.NewsAdapter
import com.binar.pra_project.view.adapter.ProdukAdapter
import com.binar.pra_project.viewmodel.HomeViewModel
import com.denzcoskun.imageslider.models.SlideModel
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pref: SharedPreferences

    val imageList =  arrayListOf<SlideModel>()

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
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)

        imageList.add(SlideModel("https://www.simplilearn.com/ice9/free_resources_article_thumb/what_is_image_Processing.jpg"))
        imageList.add(SlideModel("https://www.seiu1000.org/sites/main/files/main-images/camera_lense_0.jpeg"))

        val sliderLayout = binding.imageSlider
        sliderLayout.setImageList(imageList)

    }

    override fun onStart() {
        super.onStart()
        val viewModelNews = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModelNews.getUpdateNews()
        viewModelNews.livedatanews.observe(viewLifecycleOwner, Observer { newsList ->
            if (newsList != null) {
                val newsAdapter = NewsAdapter(newsList)
                binding.rvNewsUpdate.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvNewsUpdate.adapter = newsAdapter
            }
        })

        val viewModelProduct = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModelProduct.getAllDataProduk()
        viewModelProduct.livedataproduk.observe(viewLifecycleOwner, Observer { productList ->
            if (productList != null) {
                val productsAdapter = ProdukAdapter(productList)
                binding.rvProducts.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvProducts.adapter = productsAdapter
            }
        })
    }




    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.bottom_nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){

        R.id.cart -> {
            findNavController().navigate(R.id.action_homeFragment2_to_keranjangFragment)
            return true
        }
            R.id.favorite -> {
                findNavController().navigate(R.id.action_homeFragment2_to_favoriteFragment)
                return true
            }
            R.id.account -> {
                findNavController().navigate(R.id.action_homeFragment2_to_profileFragment)
                return true
            }

            else -> {super.onOptionsItemSelected(item)}
        }
    }


}