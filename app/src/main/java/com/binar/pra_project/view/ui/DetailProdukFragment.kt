package com.binar.pra_project.view.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.binar.pra_project.R
import com.binar.pra_project.databinding.FragmentDetailProductBinding
import com.binar.pra_project.model.DataDetailProductItem
import com.binar.pra_project.model.ProductsItem
import com.binar.pra_project.viewmodel.CartViewModel
import com.binar.pra_project.viewmodel.FavouriteViewModel
import com.binar.pra_project.viewmodel.HomeViewModel
import com.binar.pra_project.viewmodel.UserViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailProdukFragment : Fragment() {
    private lateinit var binding: FragmentDetailProductBinding
    private lateinit var homeVm: HomeViewModel
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var favViewModel: FavouriteViewModel
    private lateinit var cartViewModel: CartViewModel
    private var isProductFavorite = false
    private var idFavorite: String? = null
    private lateinit var pref: SharedPreferences
    private lateinit var idUser: String
    private lateinit var idProduct: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        val getiiuser = pref.getString("id", "").toString()
        favViewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        homeVm = ViewModelProvider(this)[HomeViewModel::class.java]
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        var getData = arguments?.getSerializable("ID") as ProductsItem
        idProduct = getData.idProduct
        binding.DetailProduct.text = getiiuser
        if (idProduct != null) {
            homeVm.getDetailProduk(idProduct)
            observeDetailProduct()
        }

        homeVm = ViewModelProvider(this).get(HomeViewModel::class.java)
    }




        private fun observeDetailProduct() {
            homeVm.liveDetailProduk.observe(viewLifecycleOwner) { detailproduct ->
                binding.apply {
                    if (detailproduct != null) {
                        binding.NamaBarang.text = detailproduct.name
                        binding.HargaBarang.text = detailproduct.price
                        binding.DescripBarang.text = detailproduct.description.toString()
                        Glide.with(requireContext())
                            .load("${detailproduct.productImage}")
                            .into(binding.ImageProduct)
                    }

                    pref = requireContext().getSharedPreferences("LOGGED_IN", Context.MODE_PRIVATE)
                    val idUserforDetail = pref.getString("id", "").toString()
                    favViewModel.getFavouriteProducts(idUserforDetail)
                    favViewModel.listFavourite.observe(viewLifecycleOwner) { favproduct ->
                        for (i in favproduct.indices) {
                            if (favproduct[i].name == detailproduct!!.name.toString()) {
                                isProductFavorite = true
                                binding.btnCart.setImageResource(R.drawable.favorite)
                                idFavorite = favproduct[i].idFav
                                break
                            } else {
                                isProductFavorite = false
                                binding.btnCart.setImageResource(R.drawable.favorite)
                            }
                        }
                    }
                    setFavoriteListener()
                    getPostCart()
                }
            }


        }

        private fun setFavoriteListener() {
            binding.btnCart.apply {
                setOnClickListener {
                    isProductFavorite = if (!isProductFavorite) {
                        addItemfavorite()
                        binding.btnCart.setImageResource(R.drawable.favorite)
                        true
                    } else {
                        deleteFromFavorite()
                        binding.btnCart.setImageResource(R.drawable.favorite)
                        false
                    }
                }
            }
        }

        private fun addToFavorite(
            id: String,
            name: String,
            productImage: String,
            price: Int,
            desc: String
        ) {
            favViewModel.postFavouriteProducts(id, name, productImage, price, desc)
            favViewModel.listFavourite.observe(requireActivity()) {
                if (it != null) {
                    Toast.makeText(
                        requireContext(),
                        "Berhasil menambahkan ke favorit",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

        private fun addItemfavorite() {
            pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
            val idUser = pref.getString("id", "").toString()
            homeVm.liveDetailProduk.observe(viewLifecycleOwner) {
                val name = it.name.toString()
                val productImage = it.productImage.toString()
                val price = it.price!!.toInt()
                val desc = it.description.toString()
                val id = idUser
                addToFavorite(id, name, productImage, price, desc,)
                binding.btnCart.setImageResource(R.drawable.favorite)
            }
        }

        private fun deleteFromFavorite() {
            pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
            val idUser = pref.getString("id", "").toString()
            favViewModel.deleteFavouriteProducts(idUser, idFavorite!!)
            favViewModel.itemFavourite.observe(viewLifecycleOwner) {
                if (it != null) {
                    Toast.makeText(requireContext(), "Sukses menghapus favorit", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "Gagal menghapus favorit", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }


        private fun addToCart(
            id: String,
            name: String,
            productImage: String,
            price: Int,
            desc: String
        ) {
            cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
            cartViewModel.addCart(id, name, productImage, price, desc)
        }

        private fun addItemCart() {
            pref = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
            val idUser = pref.getString("id", "").toString()
            homeVm.liveDetailProduk.observe(viewLifecycleOwner) {
                val name = it.name.toString()
                val productImage = it.productImage.toString()
                val price = it.price!!.toInt()
                val desc = it.description.toString()
                val id = idUser
                addToCart(id, name, productImage, price, desc)
                if (it != null) {
                    Toast.makeText(
                        requireContext(),
                        "Berhasil menambahkan ke cart",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Gagal menambahkan ke cart",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }

        private fun getPostCart() {
            binding.btnAddToCart.apply {
                setOnClickListener {
                    addItemCart()
//                findNavController().navigate(R.id.action_detailProductFragment_to_cartFragment)
                }
            }
        }
    }







