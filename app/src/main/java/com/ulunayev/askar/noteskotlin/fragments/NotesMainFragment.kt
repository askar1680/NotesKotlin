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
import kotlinx.android.synthetic.main.alert_new_section.*
import android.app.Dialog
import android.content.Context


class NotesMainFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_notes_main, container, false)
  }

  private lateinit var sectionNames: MutableList<String>
  private lateinit var sectionsAdapter: NotesPagerAdapter

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
      R.id.add_new_section -> {
        val newSectionDialog = NewSectionDialog(context!!)
        newSectionDialog.showDialog {
          sectionNames.add(it)
          sectionsAdapter.fragments.add(NotesFragment())
          sectionsAdapter.notifyDataSetChanged()
        }
      }
      R.id.search -> {

      }
    }

    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    inflater?.inflate(R.menu.menu_notes_main, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  fun setupTabLayoutAndViewPager() {
    sectionNames = arrayListOf("first", "second", "third")
    sectionsAdapter = NotesPagerAdapter(fragmentManager!!, sectionNames)
    notesViewPager.adapter = sectionsAdapter

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

class NewSectionDialog(val context: Context) {
  fun showDialog(onSuccess: (String) -> (Unit)) {
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    dialog.setContentView(R.layout.alert_new_section)

    dialog.okButton.setOnClickListener {
      if (!dialog.nameEditText.text.isEmpty()){ onSuccess(dialog.nameEditText.text.toString()); dialog.dismiss() }
    }
    dialog.cancelButton.setOnClickListener {
      dialog.dismiss()
    }

    dialog.show()

  }
}
