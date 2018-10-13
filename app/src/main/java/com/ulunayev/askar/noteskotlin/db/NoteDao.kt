package com.ulunayev.askar.noteskotlin.db

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.ulunayev.askar.noteskotlin.models.Note




@Dao
interface NoteDao {


  @get:Query("SELECT * FROM Note")
  val all: List<Note>



//  @Query("SELECT * FROM Note WHERE uid IN (:userIds)")
//  fun loadAllByIds(userIds: IntArray): List<User>
//
//
//
//  @Query("SELECT * FROM user WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
//  fun findByName(first: String, last: String): Note

  @Insert
  fun insertAll(vararg notes: Note)

  @Delete
  fun delete(note: Note)
}
