package com.ulunayev.askar.noteskotlin.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.ulunayev.askar.noteskotlin.R
import kotlinx.android.synthetic.main.activity_new_note.*

class SettingsActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)

    setSupportActionBar(toolbar)
    supportActionBar?.let { it.setDisplayHomeAsUpEnabled(true) }

  }
  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId){
      android.R.id.home -> {
        finish()
      }
    }
    return super.onOptionsItemSelected(item)
  }
}
