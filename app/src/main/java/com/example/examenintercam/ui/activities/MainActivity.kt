package com.example.examenintercam.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.examenintercam.R
import com.example.examenintercam.databinding.ActivityMainBinding
import com.example.examenintercam.utils.dialog.AlertDialogLogOut

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val navHostController =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostController.navController

        mBinding.apply {

            appBarConfiguration = AppBarConfiguration(setOf(R.id.loginFragment))

            //Toolbar
            setSupportActionBar(toolbar)

            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setHomeButtonEnabled(false)
            setupActionBarWithNavController(navController, appBarConfiguration)
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.loginFragment -> {
                    (this as AppCompatActivity?)!!.supportActionBar!!.hide()
                }
                else -> {
                    (this as AppCompatActivity?)!!.supportActionBar!!.show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onBackPressed() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logOut) {
            AlertDialogLogOut.showDialog(this) {
                finish()
                return@showDialog
            }
        } else if (item.itemId == R.id.favoritesFragment && navController.currentDestination?.id == R.id.principalFragment) {
            navController.navigate(R.id.favoritesFragment)
            return false
        }
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return when (navController.currentDestination?.id) {
            R.id.fragmentDetails -> {
                navController.navigateUp(appBarConfiguration)
            }
            R.id.favoritesFragment -> {
                navController.navigate(R.id.principalFragment)
                false
            }
            else -> {
                super.onSupportNavigateUp()
            }
        }
    }
}