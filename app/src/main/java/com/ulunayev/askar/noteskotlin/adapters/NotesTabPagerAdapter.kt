package com.ulunayev.askar.noteskotlin.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ulunayev.askar.noteskotlin.fragments.NotesFragment

class NotesPagerAdapter(fm: FragmentManager, names: ArrayList<String>) : FragmentStatePagerAdapter(fm) {
  private val fragments = ArrayList<NotesFragment>()
  var names = ArrayList<String>()

  init {
    this.names = names
    for (i in 0 until names.size) fragments.add(NotesFragment())
  }

  override fun getItem(position: Int): Fragment {
    return fragments[position]
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return names.get(position)
  }

  override fun getCount(): Int {
    return names.size
  }
}
