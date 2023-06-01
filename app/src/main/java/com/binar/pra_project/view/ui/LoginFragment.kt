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
    private lateinit var vmuser: UserViewModel
    private var isSuccesLogin = false

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
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        vmuser = ViewModelProvider(this).get(UserViewModel::class.java)

        vmuser.getlogin()

        binding.tvDaftar.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            if (binding.tvEmail.text.toString().isEmpty()) {
                binding.tvEmail.setError("Isi Username")
            } else if (binding.tvPassword.text.toString().isEmpty()) {
                binding.tvPassword.setError("Isi Password")
            } else {
                login()
            }
        }
    }

    lateinit var listlogin: List<UsersItem>
    private fun login() {

        vmuser = ViewModelProvider(this).get(UserViewModel::class.java)
        vmuser.getlivedatauserlogin().observe(viewLifecycleOwner, Observer {
            listlogin = it
            auth(listlogin)
        })
        vmuser.getlogin()

//        val email = binding.tvEmail.text.toString()
//        val password = binding.tvPassword.text.toString()
//        auth(email,password)
//        Log.d("Login Fragment", isSuccesLogin.toString())
//
//        if(isSuccesLogin){
//            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_homeFragment)
//            Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
//            vmuser.saveLoginState(true)
//        } else {
//            Toast.makeText(requireContext(), "Pastikan email dan Password anda benar", Toast.LENGTH_SHORT).show()
//        }

//        if (email.isNotEmpty() && password.isNotEmpty()){
//            Toast.makeText(context,"Login Berhasil", Toast.LENGTH_LONG).show()
//            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_produkFragment)
//
//        }else{
//            Toast.makeText(context, "Coba Cek Email dan password kembali", Toast.LENGTH_LONG).show()
//        }


    }

    private fun auth(userDataList: List<UsersItem>) {
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)


        //get all data from user input
        val inputEmail = binding.tvEmail.text.toString()
        val inputPassword = binding.tvPassword.text.toString()

        //checking email and password of user to authenticate
        for (i in userDataList.indices) {
            if (inputPassword == userDataList[i].password && inputEmail == userDataList[i].email) {
                Toast.makeText(requireContext(), "Berhasil login", Toast.LENGTH_SHORT).show()
                //bundling all information about user
                navigationBundlingSf(userDataList[i])
                break
            } else if (i == userDataList.lastIndex && inputPassword != userDataList[i].password && inputEmail != userDataList[i].email) {
                binding.tvEmail.error = "Password Tidak Sesuai"
                binding.tvPassword.error = "Email Tidak Sesuai"
            }
        }


//        vmuser.getlivedatauserlogin().observe(requireActivity()) {
//            //when get data success, validate email and password to login
//            for (i in it.indices) {
//                //validate email and password using index data
//                val emailValidate = it[i]
//                val passValidate = it[i]
//
//                //create conditional to make sure email and password is available in response data
//                if (email == emailValidate.email && password == passValidate.password) {
//                    Log.d("Login Fragment", "email validate: $emailValidate ")
//                    Log.d("Login Fragment", email)
//                    Log.d("Login Fragment", "pass validate: $passValidate ")
//                    Log.d("Login Fragment", password)
//                    //get id
//                    val id = it[i].idUsers
//                    Log.d("Login Fragment", id.toString())
//                    vmuser.saveIdPreferences("id", id!!)
//
//                    isSuccesLogin = true
//                    break
//                } else if (email != emailValidate.email && password == passValidate.password) {
//                    isSuccesLogin = false
//                } else if (email == emailValidate.email && password!= passValidate.password) {
//                    isSuccesLogin = false
//                } else {
//                    isSuccesLogin = false
//                }
//            }
//        }
    }

    private fun navigationBundlingSf(currentUser: UsersItem) {
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        //shared pref to save log in history
        val sharedPref = pref.edit()
        sharedPref.putString("email", currentUser.email)
        sharedPref.putString("password", currentUser.password)
        sharedPref.putString("username", currentUser.name)
        sharedPref.putString("image", currentUser.image)
        sharedPref.putString("id", currentUser.idUsers)
        sharedPref.apply()
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }


//        RetrofitClient.instance.getAllUser().enqueue(object : Callback<List<UsersItem>>{
//            override fun onResponse(
//                call: Call<List<UsersItem>>,
//                response: Response<List<UsersItem>>
//            ) {
//                if (response.isSuccessful){
//                    val resBody = response.body()
//                    if (resBody != null){
//                        Log.d(tag,"RESPONSE : ${resBody.toString()}")
//                        for (i in 0 until resBody.size) {
//                            if(resBody[i].email.equals(email) && resBody[i].password.equals(password)) {
//                                var addData = pref.edit()
//                                addData.putString("email", resBody[i].email)
//                                addData.putString("username",resBody[i].name)
//                                addData.putString("password",resBody[i].password)
//                                addData.putString("id",resBody[i].idUsers)
//                                addData.apply()
//                                // Clear error text
//                                binding.etEmail.error = null
//                                binding.etPassword.error = null
//
//                                Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_produkFragment)
//                                Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
//                            } else {
//                                // Set error text
//                                binding.etEmail.error = "Password Salah"
//                                binding.etPassword.error ="Email Salah"
//                                Toast.makeText(context, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                    }
//                }else{
//                    Toast.makeText(context, "Gagal Load Data", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
//                Toast.makeText(context, "salah", Toast.LENGTH_SHORT).show()
//
//            }
//
//        })



}


