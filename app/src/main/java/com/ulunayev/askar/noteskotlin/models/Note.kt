package com.ulunayev.askar.noteskotlin.models

import com.ulunayev.askar.noteskotlin.R
import java.util.*

enum class Repetition{
  NEVER, EVERY_DAY, EVERY_WEEK, EVERY_MONTH, EVERY_YEAR
}

data class Note(var position: Int, var title: String, var note: String,
                val type: String, var isSelected: Boolean = false,
                var color: Color = Color(R.color.white, R.color.black),
                var time: Date = Date(), var repetition: Repetition = Repetition.NEVER)

data class Section(val name: String, var position: Int)

data class Color(val color: Int, val textColor: Int)
