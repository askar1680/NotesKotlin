package com.ulunayev.askar.noteskotlin.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ulunayev.askar.noteskotlin.fragments.NotesFragment
import java.util.*

class NotesPagerAdapter(fm: FragmentManager, private val numberOfTabs: Int) : FragmentStatePagerAdapter(fm) {
  private val fragments = ArrayList<NotesFragment>()

  init {
    // TODO: get names from db
    for (i in 0 until numberOfTabs) fragments.add(NotesFragment())
  }

  override fun getItem(position: Int): Fragment {
    return fragments[position]
  }

  override fun getCount(): Int {
    return numberOfTabs
  }
}
