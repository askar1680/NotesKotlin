package com.ulunayev.askar.noteskotlin.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulunayev.askar.noteskotlin.R
import com.ulunayev.askar.noteskotlin.activities.NewNoteActivity
import com.ulunayev.askar.noteskotlin.adapters.NoteAdapter
import com.ulunayev.askar.noteskotlin.models.Note
import com.ulunayev.askar.noteskotlin.utils.RecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_notes.*


class NotesFragment : Fragment() {

  fun deleteSelectedNotes() {
    isMultiSelect = false
    notes.removeAll(selectedNotes)
    selectedNotes.clear()
    adapter?.notifyDataSetChanged()
  }

  fun finishDeleting(){
    selectedNotes.clear()
    adapter?.deletingFinished()
  }

  var isMultiSelect = false


  private var selectedNotes = mutableListOf<Note>()
  private var adapter: NoteAdapter? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_notes, container, false)
  }

  var notes = mutableListOf<Note>()
  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    notes.add(Note(0, "Title1", "Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(0, "Title2", "Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(0, "Title3", "Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(0, "Title4", "Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(0, "Title5", "Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(0, "Title6", "Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(0, "Title7", "Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))

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
          } else {
            adapter?.clickNote(position)
            if (!selectedNotes.contains(notes[position])) {
              selectedNotes.add(notes[position])
            }
            else {
              selectedNotes.remove(notes[position])
            }
          }
        }

        override fun onLongItemClick(view: View, position: Int) {

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

