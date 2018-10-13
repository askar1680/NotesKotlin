package com.ulunayev.askar.noteskotlin.activities

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.ActionMode
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.ulunayev.askar.noteskotlin.R
import com.ulunayev.askar.noteskotlin.fragments.BasketFragment
import com.ulunayev.askar.noteskotlin.fragments.NotesFragment
import com.ulunayev.askar.noteskotlin.fragments.NotesMainFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.support.v4.media.session.MediaButtonReceiver.handleIntent



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      val item = navigationView.menu.getItem(0)
      onNavigationItemSelected(item)
    }
    navigationView.setNavigationItemSelectedListener(this)

    setSupportActionBar(toolbar)

    val toggle = ActionBarDrawerToggle(
      this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
    drawer_layout.addDrawerListener(toggle)
    toggle.syncState()

  }




  override fun onBackPressed() {
    if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
      drawer_layout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.nav_notes -> {
        navigationView.menu.getItem(0).isChecked = true
        supportFragmentManager.beginTransaction().replace(R.id.container, NotesMainFragment()).commit()
      }
      R.id.nav_basket -> {
        navigationView.menu.getItem(0).isChecked = true
        supportFragmentManager.beginTransaction().replace(R.id.container, BasketFragment()).commit()
      }
      R.id.nav_settings -> {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
      }
      R.id.nav_about_us -> {
      }
    }
    drawer_layout.closeDrawer(GravityCompat.START)
    return true

  }

}
