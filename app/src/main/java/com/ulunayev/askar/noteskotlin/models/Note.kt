package com.ulunayev.askar.noteskotlin.models

data class Note(val id: Long, var position: Int, var title: String, var note: String, val type: String, var isSelected: Boolean = false)

data class Section(val name: String, var position: Int)