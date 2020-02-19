package com.example.events.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.codemobiles.pospos.fragment.DialogDetailFragment
import com.example.events.fragment.EventFragment
import com.example.events.R
import com.example.events.Util
import com.google.android.material.navigation.NavigationView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var headerView: View
    private lateinit var hamburgerIcon: Toolbar
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_drawer)

        nitInstanceDrawer()

        val newFragment = EventFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    //open drawer
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun nitInstanceDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view) as NavigationView
        hamburgerIcon = findViewById(R.id.toolbar) as Toolbar
        headerView = navView.getHeaderView(0) as View

        setSupportActionBar(hamburgerIcon)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setHomeAsUpIndicator(R.drawable.ic_hamburger)

        navView.setNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                item.setChecked(true)
                drawerLayout.closeDrawers()

                val fragmentManager = this@MainActivity.supportFragmentManager
                val fragments = fragmentManager.fragments
                val fragment = fragments[0] as EventFragment

                when (item.itemId) {
                    R.id.menu_advisor -> {
                        val fragmentDialog = DialogDetailFragment.Builder().setPosition(Random.nextInt(0,Util.mData!!.size-1)).builder()
                        fragmentDialog.setTargetFragment(fragment, 1)
                        fragmentDialog.show(fragment.fragmentManager!!, null)
                    }
                    R.id.menu_update -> {
                        fragment.feedData()
                    }
                    R.id.menu_contract_us -> {
                        Toast.makeText(this@MainActivity, "contract us", Toast.LENGTH_LONG).show()
                    }
                    R.id.menu_setting -> {
                        Toast.makeText(this@MainActivity, "setting", Toast.LENGTH_LONG).show()
                    }
                    R.id.menu_logout -> {
                        Toast.makeText(this@MainActivity, "log out", Toast.LENGTH_LONG).show()
                    }

                }
                return true
            }
        })
    }

}
