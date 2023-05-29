package com.binar.pra_project.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.binar.pra_project.R
import com.binar.pra_project.databinding.FragmentRegisterBinding
import com.binar.pra_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
//    private lateinit var pref: SharedPreferences

    private lateinit var vmuser : UserViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        vmuser= ViewModelProvider(this)[UserViewModel::class.java]

        binding.btnDaftar.setOnClickListener {
            register()

        }

    }

    private fun register() {
        val username = binding.txtInputLayoutNama.text.toString()
        val email = binding.txtInputLayoutEmail.text.toString()
        val pass = binding.txtInputLayoutPassword.text.toString()
        val confirmpass = binding.txtInputLayoutConfirmpass.text.toString()
//        val addAkun = pref.edit()
//        addAkun.putString("username", username)




        if (username.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty()) {
            if (pass == confirmpass) {
//                addAkun.apply()
                vmuser.addDataUser(username, email, pass)
                Navigation.findNavController(binding.root).navigate(R.id.action_registerFragment_to_loginFragment)

            } else {
                Toast.makeText(context, "Password doesn't match", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(context, "Sorry, Your Data's Incomplete", Toast.LENGTH_SHORT).show()
        }
    }
}