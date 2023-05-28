package com.binar.pra_project.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.binar.pra_project.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding
    lateinit var pref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        val getUser = pref.getString("username", "")

//        if(getUser != "") {
//            val dataUsername = pref.getString("username", "username")
//            val bundle = Bundle()
//            bundle.putString("username", dataUsername)
//
//            Handler().postDelayed({
//                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment, bundle)
//            }, 2000)
//        }
//        else {
//            Handler().postDelayed({
//                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment)
//            }, 2000)
//        }
//    }


    }
}