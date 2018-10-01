package com.ulunayev.askar.noteskotlin.fragments

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ulunayev.askar.noteskotlin.R
import com.ulunayev.askar.noteskotlin.adapters.NotesPagerAdapter
import kotlinx.android.synthetic.main.fragment_notes_main.*

class NotesMainFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment

    return inflater.inflate(R.layout.fragment_notes_main, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    notesViewPager.adapter = NotesPagerAdapter(fragmentManager!!, arrayListOf("first", "second", "third"))
    tablayout.setupWithViewPager(notesViewPager)
    notesViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))

  }
}
