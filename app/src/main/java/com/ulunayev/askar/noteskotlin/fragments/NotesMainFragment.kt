package com.ulunayev.askar.noteskotlin.fragments

import android.app.Activity
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
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.ActionMode


class NotesMainFragment : Fragment(), ActionBarCallbackListener {
  override fun deleteClicked() {
    sectionsAdapter?.deleteNotes()
    actionMode?.finish()
  }

  override fun changeColorClicked() {

  }

  override fun finishClicked() {
    sectionsAdapter?.deleteClicked(false)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_notes_main, container, false)
  }

  private var actionMode: ActionMode? = null

  private lateinit var sectionNames: MutableList<String>
  private var sectionsAdapter: NotesPagerAdapter? = null


  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setHasOptionsMenu(true)
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
          sectionsAdapter?.fragments?.add(NotesFragment())
          sectionsAdapter?.notifyDataSetChanged()
        }
      }
      R.id.search -> {

      }
      R.id.delete -> {
        activity?.let { activity ->
          val actionBarCallback = ActionBarCallback(activity)
          actionBarCallback.actionBarCallbackListener = this
          actionMode = (activity as AppCompatActivity).startSupportActionMode(actionBarCallback)
          sectionsAdapter?.deleteClicked(true)
        }
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

interface ActionBarCallbackListener{
  fun deleteClicked()
  fun changeColorClicked()
  fun finishClicked()
}

class ActionBarCallback(val activity: Activity): ActionMode.Callback {
  var actionBarCallbackListener: ActionBarCallbackListener? = null

  override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.delete -> {
        actionBarCallbackListener?.deleteClicked()
        mode?.finish()
      }
      R.id.changeColor -> {
        actionBarCallbackListener?.changeColorClicked()
          mode?.finish()
      }
    }
    return true
  }

  override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
    mode?.menuInflater?.inflate(R.menu.menu_action_mode, menu)
    return true
  }

  override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      activity.window.statusBarColor = ContextCompat.getColor(activity, R.color.colorPrimaryDark)
    }
    mode?.title = "Удалить"
    return true
  }

  override fun onDestroyActionMode(mode: ActionMode?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      activity.window.statusBarColor = ContextCompat.getColor(activity, android.R.color.transparent)
    }
    actionBarCallbackListener?.finishClicked()
    mode?.finish()
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
