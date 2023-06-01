package com.binar.pra_project

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.binar.pra_project.databinding.ActivityMainBinding
import com.binar.pra_project.viewmodel.CartViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding
    private val cartViewModel: CartViewModel by viewModels()
    private var cartQuantity:Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavView = findViewById<BottomNavigationView>(R.id.navigation)
        //navcontroler,nacdestination,bundle
        navController.addOnDestinationChangedListener { navcontroler, destination, bundle ->

            when (destination.id) {
                R.id.loginFragment, R.id.registerFragment, R.id.detailNewsFragment, R.id.detailProdukFragment,R.id.cartFragment -> {
                    bottomNavView.visibility = View.GONE
                }
                else -> {
                    bottomNavView.visibility = View.VISIBLE
                }
            }
        }
        setupWithNavController(bottomNavView, navController)
    }



//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
//        navController = navHostFragment.navController
//        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        setupWithNavController(bottomNavView, navController)

//        val crashButton = Button(this)
//        crashButton.text = "Test Crash"
//        crashButton.setOnClickListener {
//            throw RuntimeException("Test Crash") // Force a crash
//        }
//
//        addContentView(crashButton, ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT))


//        val homeFragment = HomeFragment()
//        val favoriteFragment = FavoriteFragment()
//        val keranjangFragment = KeranjangFragment()
//        val profileFragment = ProfileFragment()
//
//        val bottomNavigationView = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNavigationView)
//        setCurrentFragment(homeFragment)
//
//
//
//        bottomNavigationView.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.home->setCurrentFragment(homeFragment)
//                R.id.favorite->setCurrentFragment(favoriteFragment)
//                R.id.cart->setCurrentFragment(keranjangFragment)
//                R.id.account->setCurrentFragment(profileFragment)
//            }
//            true
//        }
//    }
//    private fun setCurrentFragment(fragment: Fragment)=
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.container,fragment)
//            commit()
//
  }




