package com.ulunayev.askar.noteskotlin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    //Logcat tag
    private static final String LOG = "DataBaseHelper";

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "notesDB";

    //Database tables
    private static final String TABLE_NOTES = "notes";
    private static final String TABLE_SECTIONS = "sections";

    //Notes table column names
    private static final String KEY_ID = "id";
    private static final String KEY_POSITION_NOTES = "position";
    private static final String KEY_TITLE = "title";
    private static final String KEY_TEXT = "text";
    private static final String KEY_TYPE = "type";

    //Sections table column names
    private static final String KEY_NAME = "name";
    private static final String KEY_POSITION_SECTIONS = "position";

    //notes table create statement
    private static final String CREATE_TABLE_NOTES = "CREATE TABLE "+TABLE_NOTES+"("+
            KEY_ID+" INTEGER PRIMARY KEY," +
            KEY_POSITION_NOTES + " INTEGER,"+
            KEY_TITLE + " TEXT,"+
            KEY_TEXT + " TEXT,"+
            KEY_TYPE + " TEXT" + ")";

    //sections table create statement
    private static final String CREATE_TABLE_SECTIONS = "CREATE TABLE "+TABLE_SECTIONS+"("+
            KEY_NAME+" TEXT," +
            KEY_POSITION_SECTIONS + " INTEGER" + ")";




    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTES);
        db.execSQL(CREATE_TABLE_SECTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NOTES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SECTIONS);
        onCreate(db);
    }
}
