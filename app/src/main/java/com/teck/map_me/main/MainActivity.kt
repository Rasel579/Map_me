package com.teck.map_me.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.teck.map_me.R
import com.teck.map_me.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by viewBinding()
    private val navController: NavController by lazy { findNavController(R.id.navigation_container) }
    private val drawerLayout: DrawerLayout by lazy { viewBinding.drawerLayout }
    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.nav_map,
                R.id.nav_labels
            ),
            drawerLayout
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: MaterialToolbar = viewBinding.appBarMain.toolBar
        setSupportActionBar(toolbar)
        initNavigation()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (navController.currentDestination?.id != R.id.nav_map) {
                    navController.navigate(R.id.nav_map)
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initNavigation() {
        val navView: NavigationView = viewBinding.navView
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.labels_item_menu -> {
                    navController.navigate(R.id.nav_labels)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.main_screen_item_menu -> {
                    navController.navigate(R.id.nav_map)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}