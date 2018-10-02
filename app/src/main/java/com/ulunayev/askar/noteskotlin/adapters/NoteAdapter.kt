package com.ulunayev.askar.noteskotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulunayev.askar.noteskotlin.R
import com.ulunayev.askar.noteskotlin.models.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(private val notes: List<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
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
    }
  }

  class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)


}