package com.binar.pra_project.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.binar.pra_project.R
import com.binar.pra_project.databinding.FragmentRegisterBinding
import com.binar.pra_project.model.UsersItem
import com.binar.pra_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var vmuser: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)
         vmuser = ViewModelProvider(this).get(UserViewModel::class.java)


        binding.btnDaftar.setOnClickListener {
            register()

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun register() {
        val username = binding.txtInputLayoutNama.text.toString()
        val email = binding.txtInputLayoutEmail.text.toString()
        val pass = binding.txtInputLayoutPassword.text.toString()
        val confirmpass = binding.txtInputLayoutConfirmpass.text.toString()
        val currentDateTime: LocalDateTime = LocalDateTime.now()
//        val addAkun = pref.edit()
//        addAkun.putString("username", username)


        if (username.isEmpty() || email.isEmpty() || pass.isEmpty() || confirmpass.isEmpty()) {
            Toast.makeText(requireContext(), "Isi Semua Kolom", Toast.LENGTH_SHORT).show()
        } else {
            if (pass== confirmpass) {
                vmuser.getregister(username, email, pass)
                Toast.makeText(requireContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            } else {
                Toast.makeText(requireContext(), "Kata Sandi Tidak Sama", Toast.LENGTH_SHORT).show()
            }
        }
    }
}