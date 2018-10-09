package com.ulunayev.askar.noteskotlin.dialogs

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import com.ulunayev.askar.noteskotlin.R
import com.ulunayev.askar.noteskotlin.adapters.DateSpinnerAdapter
import com.ulunayev.askar.noteskotlin.adapters.TimeSpinnerAdapter
import kotlinx.android.synthetic.main.alert_reminder.view.*
import java.text.SimpleDateFormat
import java.util.*


class ReminderDialog(val context: Context?){
  val layoutInflater = LayoutInflater.from(context)
  fun showDialog(onSuccess: (String) -> (Unit)) {
    val builder = AlertDialog.Builder(context)
    val view = layoutInflater.inflate(R.layout.alert_reminder, null)
    builder.setView(view)

    setupDatesSpinner(view)
    setupTimeSpinner(view)

    builder.setTitle("New Section")
    builder.setPositiveButton("Add"){ dialog, which ->

    }
    builder.setNegativeButton("Cancel") { dialog, which ->

    }
    val dialog = builder.show()
    dialog.setCancelable(false)
    dialog.show()
  }

  fun setupDatesSpinner(view: View){
    val dates = arrayOf("Сегодня", "Завтра", "Выберите дату")
    val dateAdapter = DateSpinnerAdapter(context, dates);
    view.dateSpinner.adapter = dateAdapter

    view.dateSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
      override fun onNothingSelected(parent: AdapterView<*>?) {

      }
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position == dates.size - 1){
          val c = Calendar.getInstance()
          val year = c.get(Calendar.YEAR)
          val month = c.get(Calendar.MONTH)
          val day = c.get(Calendar.DAY_OF_MONTH)

          val datePickerDialog = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val date = "$dayOfMonth/$monthOfYear/$year"
            dates[position] = date
            dateAdapter.notifyDataSetChanged()
          }, year, month, day)
          datePickerDialog.show()
        }
      }

    }

  }
  fun setupTimeSpinner(view: View) {
    val times = arrayOf(Time(" Утро", "8:00 AM"), Time("После полудня", "1:00 PM"),
      Time("Вечер", "6:00 PM"),
      Time("Ночь", "9:00 PM"), Time("Выберите время", ""))
    val timeAdapter = TimeSpinnerAdapter(context, times)
    view.timeSpinner.adapter = timeAdapter

    view.timeSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) {

      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position == times.size - 1) {
          val cal = Calendar.getInstance()
          val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            val time = SimpleDateFormat("HH:mm a").format(cal.time)
            times[position].time = time
            timeAdapter.notifyDataSetChanged()
          }
          TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
      }

    }

  }
}


data class Time(val name: String, var time: String)


