package com.ulunayev.askar.noteskotlin.models

import com.ulunayev.askar.noteskotlin.R

data class Note(var position: Int, var title: String, var note: String, val type: String, var isSelected: Boolean = false, var color: Color = Color(R.color.white, R.color.black))

data class Section(val name: String, var position: Int)

data class Color(val color: Int, val textColor: Int)
