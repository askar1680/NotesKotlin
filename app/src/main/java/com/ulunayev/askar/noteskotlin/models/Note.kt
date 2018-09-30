package com.ulunayev.askar.noteskotlin.models

data class Note(val id: Long, var position: Int, var title: String, var note: String, val type: String)

data class DBName(val name: String, var position: Int)