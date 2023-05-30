package com.binar.pra_project.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.binar.pra_project.databinding.FragmentProfileBinding
import com.binar.pra_project.model.UsersItem
import com.binar.pra_project.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    lateinit var listuser: List<UsersItem>
    private lateinit var pref : SharedPreferences
    private lateinit var vmpro : ProfileViewModel
    private lateinit var oldPassword : String
    private lateinit var id : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        vmpro = ViewModelProvider(this).get(ProfileViewModel::class.java)

        val name = pref.getString("username","username")

        id = pref.getString("id", "").toString()

        getProfile()
    }


    fun getProfile() {
        vmpro.profile(id)
        vmpro.getlivedataprofile().observe(viewLifecycleOwner){
            if (it != null){
                binding.txtInputLayoutNamaProf.setText(it.name)
                binding.txtEmail.setText(it.email)
                binding.txtInputLayoutPass.setText(it.password)
                oldPassword = it.password
            }
        }
    }

    override fun onStart() {
        super.onStart()
        getProfile()
    }

}