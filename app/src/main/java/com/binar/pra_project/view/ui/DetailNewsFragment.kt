package com.binar.pra_project.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.binar.pra_project.R
import com.binar.pra_project.databinding.FragmentDetailNewsBinding
import com.binar.pra_project.viewmodel.HomeViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailNewsFragment : Fragment() {

    lateinit var binding: FragmentDetailNewsBinding
    lateinit var homeVm: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeVm = ViewModelProvider(this).get(HomeViewModel::class.java)
        val id = arguments?.getInt("ID")
        if (id != null) {
            homeVm.getDetailNews(id)
            DetailNews()


        }
    }


    private fun DetailNews() {
        homeVm.liveDetailNews.observe(viewLifecycleOwner) {
            binding.apply {
                if (it != null) {
                    binding.JudulBerita.text = it.title.toString()
                    binding.tanggalberita.text = it.createdAt.toString()
                    Glide.with(requireContext())
                        .load("${it.newsImage}")
                        .into(binding.ImageBerita)

                    binding.contentberita.text = it.content.toString()

                }
            }
        }


    }
}