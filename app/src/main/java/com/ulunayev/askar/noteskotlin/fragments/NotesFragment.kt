package com.ulunayev.askar.noteskotlin.fragments

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulunayev.askar.noteskotlin.R
import com.ulunayev.askar.noteskotlin.adapters.NotesPagerAdapter
import kotlinx.android.synthetic.main.fragment_notes.*


class NotesFragment: Fragment() {


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment

    return inflater.inflate(R.layout.fragment_notes, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    setupTabLayout()
    setupViewPager()

  }


  private fun setupTabLayout(){
    tablayout.addTab(tablayout.newTab().setText("first"))
    tablayout.addTab(tablayout.newTab().setText("second"))
    tablayout.addTab(tablayout.newTab().setText("third"))
    tablayout.addTab(tablayout.newTab().setText("fourth"))

    tablayout.addOnTabSelectedListener(getOnSelectedListener())
  }

  private fun setupViewPager(){
    notesViewPAger.adapter = NotesPagerAdapter(fragmentManager!!, tablayout.tabCount)

    notesViewPAger.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))
  }

  private fun getOnSelectedListener(): TabLayout.BaseOnTabSelectedListener<*> {
    return object : TabLayout.OnTabSelectedListener {
      override fun onTabSelected(tab: TabLayout.Tab) {
        notesViewPAger.setCurrentItem(tab.position)
      }

      override fun onTabUnselected(tab: TabLayout.Tab) {}

      override fun onTabReselected(tab: TabLayout.Tab) {}
    }
  }


}
