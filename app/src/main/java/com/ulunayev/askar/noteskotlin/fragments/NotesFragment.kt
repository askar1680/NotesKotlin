package com.ulunayev.askar.noteskotlin.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ulunayev.askar.noteskotlin.R
import kotlinx.android.synthetic.main.fragment_notes.*


class NotesFragment: Fragment() {


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_notes, container, false)

  }


}
