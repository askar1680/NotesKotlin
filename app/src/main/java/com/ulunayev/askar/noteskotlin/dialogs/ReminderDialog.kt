package com.ulunayev.askar.noteskotlin.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulunayev.askar.noteskotlin.R
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.alert_reminder.view.*
import kotlinx.android.synthetic.main.item_spinner_with_two_tv.view.*


class ReminderDialog(val context: Context?){
  val layoutInflater = LayoutInflater.from(context)
  fun showDialog(onSuccess: (String) -> (Unit)) {
    val builder = AlertDialog.Builder(context)
    val view = layoutInflater.inflate(R.layout.alert_reminder, null)
    builder.setView(view)


    val dates = arrayOf("Сегодня", "Завтра", "Выберите дату")
    val spinnerArrayAdapter = ArrayAdapter<String>(context, R.layout.item_spinner_with_one_tv, dates)
    view.dateSpinner.adapter = spinnerArrayAdapter

    val times = arrayOf(Time(" Утро", "8:00 АМ"), Time("После полудня", "1:00 РМ"),
      Time("Вечер", "6:00 РМ"),
      Time("Ночь", "9:00"),Time("Выберите время", ""))
    val timeAdapter = TimeSpinnerAdapter(context, times)
    view.timeSpinner.adapter = timeAdapter

    builder.setTitle("New Section")
    builder.setPositiveButton("Add"){ dialog, which ->

    }
    builder.setNegativeButton("Cancel") { dialog, which ->

    }
    val dialog = builder.show()
    dialog.setCancelable(false)
    dialog.show()
  }
}

class TimeSpinnerAdapter(val context: Context?, var times: Array<Time>): BaseAdapter() {

  val layoutInflater = LayoutInflater.from(context)

  override fun getView(postition: Int, convertView: View?, parent: ViewGroup?): View {
    var view = layoutInflater.inflate(R.layout.item_spinner_with_two_tv, parent, false)
    view.tv1.text = times[postition].name
    view.tv2.text = times[postition].time
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
}


data class Time(val name: String, val time: String)


