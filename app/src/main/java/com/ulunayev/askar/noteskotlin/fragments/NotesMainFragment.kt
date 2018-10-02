package com.ulunayev.askar.noteskotlin.fragments

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.view.*
import com.ulunayev.askar.noteskotlin.R
import com.ulunayev.askar.noteskotlin.activities.NewNoteActivity
import com.ulunayev.askar.noteskotlin.adapters.NotesPagerAdapter
import kotlinx.android.synthetic.main.fragment_notes_main.*


class NotesMainFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_notes_main, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setHasOptionsMenu(true);
    setupTabLayoutAndViewPager()

    fab.setOnClickListener { view ->
      intentToNewNoteActivityWithAnimation(view)
    }
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {

    when (item?.itemId){

    }

    return super.onOptionsItemSelected(item)

  }

  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    inflater?.inflate(R.menu.menu_notes_main, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }
//
//  fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//    inflater.inflate(R.menu.your_menu_xml, menu)
//    super.onCreateOptionsMenu(menu, inflater)
//  }

  fun setupTabLayoutAndViewPager() {
    notesViewPager.adapter = NotesPagerAdapter(fragmentManager!!, arrayListOf("first", "second", "third"))
    tablayout.setupWithViewPager(notesViewPager)
    notesViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))
  }

  fun intentToNewNoteActivityWithAnimation(view: View) {
    activity?.let { activity ->
      val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, "transition")
      val revealX = (view.getX() + view.getWidth() / 2).toInt()
      val revealY = (view.getY() + view.getHeight() / 2).toInt()

      val intent = Intent(context, NewNoteActivity::class.java)
      intent.putExtra(NewNoteActivity.EXTRA_CIRCULAR_REVEAL_X, revealX)
      intent.putExtra(NewNoteActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY)

      ActivityCompat.startActivity(context!!, intent, options.toBundle())
    }
  }
}