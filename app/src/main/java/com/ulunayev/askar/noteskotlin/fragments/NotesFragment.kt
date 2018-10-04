package com.ulunayev.askar.noteskotlin.fragments

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.ulunayev.askar.noteskotlin.R
import com.ulunayev.askar.noteskotlin.activities.NewNoteActivity
import com.ulunayev.askar.noteskotlin.adapters.NoteAdapter
import com.ulunayev.askar.noteskotlin.models.Note
import com.ulunayev.askar.noteskotlin.utils.RecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_notes.*
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.ActionMode
import android.util.Log
import android.view.*


class NotesFragment: Fragment(), ActionBarCallbackListener {
  override fun finishClicked() {
    adapter.deletingFinished()
    isMultiSelect = false
  }

  override fun deleteClicked() {
    Log.d("NoteFragment", "Delete clicked")
    notes.removeAll(selectedNotes)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      activity?.window?.statusBarColor = ContextCompat.getColor(activity!!, android.R.color.transparent)
    }
    actionMode?.finish()
    adapter.notifyDataSetChanged()
  }

  override fun changeColorClicked() {

  }


  private lateinit var thisFragment: NotesFragment
  private var actionMode: ActionMode? = null
  private var isMultiSelect = false
  private var selectedNotes = mutableListOf<Note>()
  private lateinit var adapter: NoteAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_notes, container, false)
  }



  var notes = mutableListOf<Note>()
  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    thisFragment = this

    notes.add(Note(2121, 0, "Title1", "Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(2121, 0, "Title2", "Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(2121, 0, "Title3", "Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(2121, 0, "Title4", "Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(2121, 0, "Title5", "Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(2121, 0, "Title6", "Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(2121, 0, "Title7", "Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))

    adapter = NoteAdapter(notes)
    notesRV.adapter = adapter
    notesRV.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)

    val itemTouchHelper = ItemTouchHelper(createHelperCallback())
    itemTouchHelper.attachToRecyclerView(notesRV)

    notesRV.addOnItemTouchListener(
      RecyclerItemClickListener(context, notesRV, object : RecyclerItemClickListener.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
          if (!isMultiSelect) {
            val intent = Intent(context!!, NewNoteActivity::class.java)
            startActivity(intent)
          }
          else {
            adapter.clickNote(position)
            selectedNotes.add(notes[position])
          }
        }

        override fun onLongItemClick(view: View, position: Int) {
//          isMultiSelect = true
//          if (isMultiSelect) {
          isMultiSelect = true
          selectedNotes = ArrayList()
          activity?.let {
            val actionBarCallback = ActionBarCallback(it)
            actionBarCallback.actionBarCallbackListener = thisFragment
            actionMode = (activity as AppCompatActivity).startSupportActionMode(actionBarCallback)
            adapter.clickNote(position)
            selectedNotes.add(notes[position])
          }
//          }
//          else {
//
//          }
        }
      })
    )
  }



  private fun createHelperCallback(): ItemTouchHelper.Callback {
    return object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.ACTION_STATE_SWIPE) {

      override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        moveItem(viewHolder.adapterPosition, target.adapterPosition)
        return true
      }

      override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
      }

      override fun isLongPressDragEnabled(): Boolean {
        return true
      }

      override fun isItemViewSwipeEnabled(): Boolean {
        return true

      }
    }
  }

  private fun moveItem(oldPos: Int, newPos: Int) {
    val note = notes[oldPos]
    notes.removeAt(oldPos)
    notes.add(newPos, note)
    notesRV.adapter?.notifyItemMoved(oldPos, newPos)
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
      }
      R.id.changeColor -> {
        actionBarCallbackListener?.changeColorClicked()
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

