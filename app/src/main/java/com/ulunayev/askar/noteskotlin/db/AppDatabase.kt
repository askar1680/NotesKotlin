package com.ulunayev.askar.noteskotlin.db

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.ulunayev.askar.noteskotlin.models.ColorConverter
import com.ulunayev.askar.noteskotlin.models.Note
import com.ulunayev.askar.noteskotlin.models.RepetitionConverter

@Database(entities = arrayOf(Note::class), version = 1)
@TypeConverters(RepetitionConverter::class, ColorConverter::class)
abstract class AppDatabase : RoomDatabase() {
  abstract fun userDao(): NoteDao

  companion object {
    @Volatile private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase =
      INSTANCE ?: synchronized(this) {
        INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
      }

    private fun buildDatabase(context: Context) =
      Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app.db").build()
  }

}

