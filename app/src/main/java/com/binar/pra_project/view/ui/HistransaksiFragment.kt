package com.binar.pra_project.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binar.pra_project.databinding.FragmentHistransaksiBinding
import com.binar.pra_project.view.adapter.HistoryTransactionAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class HistransaksiFragment : Fragment() {
    private lateinit var binding: FragmentHistransaksiBinding
    private lateinit var historyadapter: HistoryTransactionAdapter
    private lateinit var pref: SharedPreferences
    lateinit var idUser: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHistransaksiBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        idUser = pref.getString("id","").toString()


    }

    private fun setHistoryTransaction() {

//        binding.rvHistoryTransaction.apply {
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            adapter = historyadapter
//        }
//       pref..asLiveData().observe(viewLifecycleOwner){
//            if(it != 0){
//                historyTransactionViewModel.getDataAllHistoryTransaction(it)
//
//                historyTransactionViewModel.allHistoryTransaction.observe(viewLifecycleOwner){dataHistoryTrans->
//                    if(dataHistoryTrans != null){
//                        historyTransactionAdapter.setDataListNewProduct(dataHistoryTrans)
//                    }
//                }
//            }
//        }
    }

    private fun checkActiveUser() {
//        pref.isLoggin().asLiveData().observe(viewLifecycleOwner){
//            if(it != null){
//                if (it){
//                    binding.btnLogin.visibility = View.GONE
//                    binding.rvHistoryTransaction.visibility = View.VISIBLE
//                }else{
//                    binding.btnLogin.visibility = View.VISIBLE
//                    binding.rvHistoryTransaction.visibility = View.GONE
//                }
//            }
//        }
    }

    fun convertToRupiah(number: Int): String{

        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }





}