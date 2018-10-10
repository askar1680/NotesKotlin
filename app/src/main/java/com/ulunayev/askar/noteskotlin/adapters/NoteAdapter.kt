package com.ulunayev.askar.noteskotlin.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulunayev.askar.noteskotlin.R
import com.ulunayev.askar.noteskotlin.models.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(private val notes: List<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
  lateinit var context: Context
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
    context = parent.context
    return ViewHolder(view)
  }

  override fun getItemCount(): Int {
    return notes.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val note = notes[position]
    with(holder) {
      view.titleTV.text = note.title
      view.noteTV.text = note.note
      view.darkView.visibility = if (note.isSelected) View.VISIBLE else View.GONE
      view.doneImageView.visibility = if (note.isSelected) View.VISIBLE else View.GONE

      view.noteBackground.setBackgroundColor(ContextCompat.getColor(context, note.color.color))
      view.titleTV.setTextColor(ContextCompat.getColor(context, note.color.textColor))
      view.noteTV.setTextColor(ContextCompat.getColor(context, note.color.textColor))

    }
  }

  fun clickNote(position: Int){
    notes[position].isSelected = !notes[position].isSelected
    notifyItemChanged(position)
  }

  fun deletingFinished(){
    for (note in notes) note.isSelected = false
    notifyDataSetChanged()
  }

  class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)


}