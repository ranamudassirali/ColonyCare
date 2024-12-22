package com.fatima.colonycare.model.UI.HomeFragment

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.fatima.colonycare.R
import com.fatima.colonycare.model.DataSource.CloudinaryUploadHelper.Companion.initializeCloudinary
import com.fatima.colonycare.model.UI.Auth.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeCloudinary(this)


//        val floatingBtn = findViewById<FloatingActionButton>(R.id.floatingActionButton)
//        floatingBtn.setOnClickListener {
//            val intent = Intent(this, AddRequestActivity::class.java)
//            startActivity(intent)
//        }

        val toolbar : Toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setupWithNavController(navHostFragment.navController)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val imageView = findViewById<ImageView>(R.id.drawer_icon)
        imageView.setOnClickListener { view: View? ->
            if (drawer.isDrawerVisible(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START)
            } else {
                drawer.openDrawer(GravityCompat.START)
            }
        }




        val viewModel= MainViewModel()

        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener(){
            startActivity(Intent(this,AddServiceActivity::class.java))
        }

        lifecycleScope.launch {
            viewModel.isSucessfullySaved.collect{
                it?.let {
                    if(it==true)
                        Toast.makeText(this@MainActivity,"Successfully Saved", Toast.LENGTH_SHORT).show()
                }

            }
        }


        lifecycleScope.launch {
            viewModel.isFailure.collect{
                it?.let {
                    Toast.makeText(this@MainActivity,it, Toast.LENGTH_SHORT).show()
                }

            }
        }





    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val viewModel = MainViewModel()

        if (item.itemId == R.id.profile) {

        } else if (item.itemId == R.id.service) {

        } else if (item.itemId == R.id.help) {

        } else if (item.itemId == R.id.about) {

        } else if (item.itemId == R.id.logout) {

            lifecycleScope.launch {
                viewModel.currentUser.collect {
                    if (it == null) {
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                        finish()
                    }
                    //TODO:display user data in a nav drawer
                }
            }

        }

        return true
    }
}