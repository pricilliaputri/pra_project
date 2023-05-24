package com.binar.pra_project.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.binar.pra_project.R
import com.binar.pra_project.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var pref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)

        login()
    }

    private fun login() {
        val email = binding.tvEmail.text.toString()
        val password = binding.tvPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){
            Toast.makeText(context,"Login Berhasil", Toast.LENGTH_LONG).show()
            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_produkFragment)

        }else{
            Toast.makeText(context, "Coba Cek Email dan password kembali", Toast.LENGTH_LONG).show()
        }


    }


}