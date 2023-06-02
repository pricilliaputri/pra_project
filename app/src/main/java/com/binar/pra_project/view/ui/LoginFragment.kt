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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
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
import kotlin.math.log

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var pref: SharedPreferences
    private lateinit var userVM: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM = ViewModelProvider(this).get(UserViewModel::class.java)
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)

        binding.tvDaftar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.txtInputLayoutEmail.text.toString()
            val password = binding.txtInputLayoutPassword.text.toString()
            login(email, password)
        }
    }
    private fun login(email: String, password: String) {
        RetrofitClient.instance.getAllUsers()
            .enqueue(object : Callback<List<UsersItem>> {
                override fun onResponse(
                    call: Call<List<UsersItem>>,
                    response: Response<List<UsersItem>>,
                ) {
                    if (response.isSuccessful){
                        val resBody = response.body()
                        if (resBody != null){
                            Log.d(tag,"RESPONSE : ${resBody.toString()}")
                            for (log in 0 until resBody.size) {
                                if(resBody[log].email.equals(email) && resBody[log].password.equals(password)) {
                                    var addData = pref.edit()
                                    addData.putString("email", resBody[log].email)
                                    addData.putString("username",resBody[log].name)
                                    addData.putString("password",resBody[log].password)
                                    addData.putString("id",resBody[log].idUsers)
                                    addData.apply()

                                    Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment)
                                    Toast.makeText(context, "Berhasil Masuk", Toast.LENGTH_SHORT).show()
                                } else {
                                    binding.txtInputLayoutEmail.error = "Email Tidak Sesuai"
                                    binding.txtInputLayoutPassword.error ="Password Tidak Sesuai"
                                }
                            }
                        }
                    }else{
                        Toast.makeText(context, "Gagal Load Data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                    Toast.makeText(context, "Kesalahan Saat Mencoba Masuk", Toast.LENGTH_SHORT).show()
                }

            })
    }
}




