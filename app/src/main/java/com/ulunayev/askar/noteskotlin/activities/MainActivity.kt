package com.ulunayev.askar.noteskotlin.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.ulunayev.askar.noteskotlin.R
import com.ulunayev.askar.noteskotlin.fragments.BasketFragment
import com.ulunayev.askar.noteskotlin.fragments.NotesMainFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

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

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_settings -> return true
      else -> return super.onOptionsItemSelected(item)
    }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.nav_notes -> {
        supportFragmentManager.beginTransaction().replace(R.id.container, NotesMainFragment()).commit()
      }
      R.id.nav_basket -> {
        supportFragmentManager.beginTransaction().replace(R.id.container, BasketFragment()).commit()
      }
      R.id.nav_settings -> {

      }
      R.id.nav_about_us -> {

      }
    }
    item.isChecked = true
    drawer_layout.closeDrawer(GravityCompat.START)
    return true
  }
}
