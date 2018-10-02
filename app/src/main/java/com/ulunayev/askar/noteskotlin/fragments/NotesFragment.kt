package com.ulunayev.askar.noteskotlin.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulunayev.askar.noteskotlin.R
import com.ulunayev.askar.noteskotlin.adapters.NoteAdapter
import com.ulunayev.askar.noteskotlin.models.Note
import kotlinx.android.synthetic.main.fragment_notes.*


class NotesFragment: Fragment() {


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_notes, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)


    var notes = mutableListOf<Note>()
    notes.add(Note(2121, 0, "Title1", "Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(2121, 0, "Title2", "Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(2121, 0, "Title3", "Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(2121, 0, "Title4", "Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(2121, 0, "Title5", "Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(2121, 0, "Title6", "Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))
    notes.add(Note(2121, 0, "Title7", "Blablabla Blablabla Blablabla Blablabla Blablabla ", "type"))


    notesRV.adapter = NoteAdapter(notes)
    notesRV.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)


  }


}
