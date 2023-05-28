package com.binar.pra_project.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)

        imageList.add(SlideModel("https://media.hitekno.com/thumbs/2023/04/15/50264-samsung-galaxy-a54-5g-vs-samsung-galaxy-a53-5g/730x480-img-50264-samsung-galaxy-a54-5g-vs-samsung-galaxy-a53-5g.jpg"))
        imageList.add(SlideModel("https://images.tokopedia.net/img/cache/500-square/VqbcmM/2021/11/23/58d62cd2-e918-48a8-b411-be80ca1aa068.jpg"))

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
}