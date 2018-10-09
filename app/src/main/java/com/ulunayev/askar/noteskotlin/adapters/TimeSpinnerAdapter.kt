package com.ulunayev.askar.noteskotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.ulunayev.askar.noteskotlin.R
import com.ulunayev.askar.noteskotlin.dialogs.Time
import kotlinx.android.synthetic.main.item_spinner_with_one_tv.view.*
import kotlinx.android.synthetic.main.item_spinner_with_two_tv.view.*

class TimeSpinnerAdapter(val context: Context?, var times: Array<Time>): BaseAdapter() {

  val layoutInflater = LayoutInflater.from(context)

  override fun getView(postition: Int, convertView: View?, parent: ViewGroup?): View {
    var view = layoutInflater.inflate(R.layout.item_spinner_with_one_tv, parent, false)

    view.tvSpinner.text = times[postition].time

    return view!!
  }

  override fun getItem(postition: Int): Any {
    return times[postition]
  }

  override fun getItemId(postition: Int): Long {
    return 0
  }

  override fun getCount(): Int {
    return times.size
  }

  override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val twoTVView = layoutInflater.inflate(R.layout.item_spinner_with_two_tv, parent, false)
    twoTVView.tv1.text = times[position].name
    twoTVView.tv2.text = times[position].time
    return twoTVView
  }
}

class DateSpinnerAdapter(val context: Context?, var dates: Array<String>): BaseAdapter() {
  val layoutInflater = LayoutInflater.from(context)
  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val view = layoutInflater.inflate(R.layout.item_spinner_with_one_tv, parent, false)
    view.tvSpinner.text = dates[position]
    return view!!
  }

  override fun getItem(postition: Int): Any {
    return dates[postition]
  }

  override fun getItemId(postition: Int): Long {
    return 0
  }

  override fun getCount(): Int {
    return dates.size
  }

  override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val view = super.getDropDownView(position, convertView, parent)
    view.tvSpinner.text = if (position != dates.size - 1) dates[position] else "Выберите дату"
    return view
  }
}