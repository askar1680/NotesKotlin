package com.ulunayev.askar.noteskotlin.dialogs

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulunayev.askar.noteskotlin.R
import kotlinx.android.synthetic.main.alert_color.view.*
import kotlinx.android.synthetic.main.item_color.view.*
import android.support.v4.content.ContextCompat
import com.ulunayev.askar.noteskotlin.models.Color


class ColorDialog(val context: Context?): ColorAdapter.ColorDialogListener{

  interface ColorDialogListener{
    fun colorClicked(color: Color?)
  }

  override fun colorClicked(color: Color) {
    dialog?.dismiss()
    colorDialogListener?.colorClicked(color)
  }

  var dialog: AlertDialog? = null

  var colorDialogListener: ColorDialogListener? = null

  val layoutInflater = LayoutInflater.from(context)
  fun showDialog() {

    val builder = AlertDialog.Builder(context)
    val view = layoutInflater.inflate(R.layout.alert_color, null)
    builder.setView(view)
    builder.setTitle("New Section")

    view.colorRV.layoutManager = GridLayoutManager(context, 4)
    val adapter = ColorAdapter()
    adapter.colorDialogListener = this
    view.colorRV.adapter = adapter

    builder.setNegativeButton("Cancel") { dialog, _ ->
      colorDialogListener?.colorClicked(null)
      dialog.dismiss()
    }

    dialog = builder.create()
    dialog?.show()
  }
}


class ColorAdapter : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

  interface ColorDialogListener{
    fun colorClicked(color: Color)
  }

  var colorDialogListener: ColorDialogListener? = null
//R.color.colorPrimary, R.color.grey, R.color.brown, R.color.white,
//  R.color.colorPrimary, R.color.grey, R.color.brown, R.color.white
  private lateinit var context: Context
  private val colors = listOf(Color(R.color.colorPrimary, R.color.white), Color(R.color.grey, R.color.white),
                              Color(R.color.brown, R.color.white), Color(R.color.white, R.color.black),
                              Color(R.color.colorPrimary, R.color.white), Color(R.color.grey, R.color.white),
                              Color(R.color.brown, R.color.white), Color(R.color.white, R.color.black))

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
    context = parent.context
    return ViewHolder(view)
  }

  override fun getItemCount(): Int {
    return colors.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    with(holder) {
      val background = view.colorBGView.background as GradientDrawable
      background.setColor(ContextCompat.getColor(context, colors[position].color))

      view.colorClickView.setOnClickListener { _ ->
        colorDialogListener?.colorClicked(colors[position])
      }

    }
  }
  class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
