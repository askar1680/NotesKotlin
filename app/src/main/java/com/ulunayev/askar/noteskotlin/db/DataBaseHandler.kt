package com.ulunayev.askar.noteskotlin.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.ulunayev.askar.noteskotlin.models.Note

val DATABASE_NAME = "notesDb"
val TABLE_NAME = "notes"
val COL_ID = "id"
val COL_POSITION = "position"
val COL_TITLE = "title"
val COL_NOTE = "note"
val COL_TYPE = "type"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE "+ TABLE_NAME +" (" +
                COL_ID+" INTEGER PRIMARY KEY," +
                COL_POSITION+" INTEGER," +
                COL_TITLE+" VARCHAR(256)," +
                COL_NOTE+" TEXT," +
                COL_TYPE+" TEXT)";
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    fun insertNote(note: Note){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_POSITION,note.position)
        cv.put(COL_TITLE,note.title)
        cv.put(COL_NOTE,note.note)
        cv.put(COL_TYPE,note.type)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong()){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        }else Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()

    }

}
