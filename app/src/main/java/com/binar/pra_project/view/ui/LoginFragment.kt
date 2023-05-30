package com.binar.pra_project.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.binar.pra_project.R
import com.binar.pra_project.databinding.FragmentLoginBinding
import com.binar.pra_project.model.UsersItem
import com.binar.pra_project.network.RestfulApi
import com.binar.pra_project.network.RetrofitClient
import com.binar.pra_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var pref: SharedPreferences
    private lateinit var vmuser : UserViewModel

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
        vmuser = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.tvDaftar.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        login()
    }

    private fun login() {
        val email = binding.tvEmail.text.toString()
        val password = binding.tvPassword.text.toString()

        auth(email,password)

//        if (email.isNotEmpty() && password.isNotEmpty()){
//            Toast.makeText(context,"Login Berhasil", Toast.LENGTH_LONG).show()
//            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_produkFragment)
//
//        }else{
//            Toast.makeText(context, "Coba Cek Email dan password kembali", Toast.LENGTH_LONG).show()
//        }


    }

    private fun auth(email : String, password : String){

        RetrofitClient.instance.getAllUser().enqueue(object : Callback<List<UsersItem>>{
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                if (response.isSuccessful){
                    val resBody = response.body()
                    if (resBody != null){
                        Log.d(tag,"RESPONSE : ${resBody.toString()}")
                        for (i in 0 until resBody.size) {
                            if(resBody[i].email.equals(email) && resBody[i].password.equals(password)) {
                                var addData = pref.edit()
                                addData.putString("email", resBody[i].email)
                                addData.putString("username",resBody[i].name)
                                addData.putString("password",resBody[i].password)
                                addData.putString("id",resBody[i].idUsers)
                                addData.apply()
                                // Clear error text
                                binding.etEmail.error = null
                                binding.etPassword.error = null

                                Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_produkFragment)
                                Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
                            } else {
                                // Set error text
                                binding.etEmail.error = "Password Salah"
                                binding.etPassword.error ="Email Salah"
                                Toast.makeText(context, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }else{
                    Toast.makeText(context, "Gagal Load Data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                Toast.makeText(context, "salah", Toast.LENGTH_SHORT).show()

            }

        })







    }


}