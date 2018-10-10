package com.ulunayev.askar.noteskotlin.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ulunayev.askar.noteskotlin.R
import com.ulunayev.askar.noteskotlin.adapters.NoteAdapter
import com.ulunayev.askar.noteskotlin.models.Note
import kotlinx.android.synthetic.main.fragment_basket.*


class BasketFragment : Fragment() {


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.fragment_basket, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    setHasOptionsMenu(true)

    var notes = mutableListOf<Note>()
    var count = 0
    while (count < 10){
      notes.add(Note(0, "Title$count", "BlablablaBlablablaBlablablaBlablablaBlablablaBlablabla", "cdscds"))
      count ++
    }


    basketRC.adapter = NoteAdapter(notes)
    basketRC.layoutManager = LinearLayoutManager(context!!, LinearLayout.VERTICAL, false)

  }

}

