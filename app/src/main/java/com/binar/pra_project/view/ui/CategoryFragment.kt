package com.binar.pra_project.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.binar.pra_project.databinding.FragmentCategoryBinding
import com.binar.pra_project.view.adapter.ProdukAdapter
import com.binar.pra_project.viewmodel.CategoryViewModel
import com.binar.pra_project.wrap.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {
    lateinit var binding: FragmentCategoryBinding
    private val  listItemCategoryyyVM : CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getProductId = arguments?.getInt("ID_CATEGORY")
        val getNameCategory = arguments?.getString("NAME_CATEGORY")
        binding.tvCategory.text = getNameCategory
        print(getProductId)
        print(getNameCategory)
        setLayoutCategory(getProductId!!)
    }

    private fun setLayoutCategory(id : Int){
        listItemCategoryyyVM.setCategoryList(id)

        binding.rvListCategory.layoutManager = GridLayoutManager(requireContext(), 2)
        listItemCategoryyyVM.listProduct.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading ->{
                    binding.progressCategory.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.progressCategory.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Success -> {
                    binding.progressCategory.visibility = View.GONE
                    binding.contentCategory.visibility = View.VISIBLE
                    binding.rvListCategory.adapter = ProdukAdapter(it.data!!)
                }

            }
        }
    }

}