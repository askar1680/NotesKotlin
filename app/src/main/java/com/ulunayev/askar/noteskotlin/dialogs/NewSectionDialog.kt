package com.ulunayev.askar.noteskotlin.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.ulunayev.askar.noteskotlin.R
import kotlinx.android.synthetic.main.alert_new_section.view.*

class NewSectionDialog(val context: Context?) {
  private val layoutInflater = LayoutInflater.from(context)
  fun showDialog(onSuccess: (String?) -> (Unit)) {

    val builder = AlertDialog.Builder(context)
    val view = layoutInflater.inflate(R.layout.alert_new_section, null)
    builder.setView(view)
    builder.setTitle("New Section")


    builder.setPositiveButton("Create"){ dialog, which ->
      val text = view.nameEditText.text
      onSuccess(if (text.isNullOrEmpty())  null else text.toString())
    }
    builder.setNegativeButton("Cancel") { dialog, which ->

    }
    val dialog = builder.create()
    dialog.setCancelable(false)
    dialog.show()

  }
}
