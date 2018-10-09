package com.ulunayev.askar.noteskotlin.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LevelListDrawable
import android.graphics.drawable.ShapeDrawable
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulunayev.askar.noteskotlin.R
import com.ulunayev.askar.noteskotlin.models.Note
import kotlinx.android.synthetic.main.alert_color.view.*
import kotlinx.android.synthetic.main.item_color.view.*
import android.support.v4.content.ContextCompat
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable



class ColorDialog(val context: Context?){
  val layoutInflater = LayoutInflater.from(context)
  fun showDialog() {

    val builder = AlertDialog.Builder(context)
    val view = layoutInflater.inflate(R.layout.alert_color, null)
    builder.setView(view)
    builder.setTitle("New Section")

    view.colorRV.layoutManager = GridLayoutManager(context, 4)
    view.colorRV.adapter = ColorAdapter(listOf("", "", "", "", "", "" ,"", ""))


    builder.setPositiveButton("OK") { dialog, _ ->

    }
    builder.setNegativeButton("Cancel") { dialog, which ->

    }

    val dialog = builder.create()
    dialog.setCancelable(false)
    dialog.show()

  }
}



class ColorAdapter(private val notes: List<String>) : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

  val colors = listOf(R.color.colorPrimary, R.color.grey, R.color.brown, R.color.white,
    R.color.colorPrimary, R.color.grey, R.color.brown, R.color.white)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
    val innerDrawable = ResourcesCompat.getDrawable(parent.context.resources, R.drawable.item_color_bg, null)
    val strokeWidth = 1

    return ViewHolder(view)
  }

  override fun getItemCount(): Int {
    return notes.size
  }

  @SuppressLint("ResourceAsColor")
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val note = notes[position]

    with(holder) {
      val background = view.colorView.background
      when (background) {
        is ShapeDrawable -> background.paint.color = colors[position]
        is GradientDrawable -> background.setColor( colors[position])
        is ColorDrawable -> background.color =  colors[position]
      }

    }
  }


  class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)


}