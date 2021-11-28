package com.example.td2sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    //table name
    public static final String TABLE_NAME = "PAYS";

    //table columns
    public static final String _ID = "_id";
    public static final String SUBJECT = "jubject";
    public static final String DESC = "description";

    //database info
    public static final String DB_NAME = "pays.DB";

    //database version
    public static final int DB_VERSION = 1;

    //creating table query
    public static final String CREATE_TABLE =   "CREATE TABLE " + TABLE_NAME + "("
                                                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                + SUBJECT + " TEXT NOT NULL, "
                                                + DESC + " TEXT"
                                                + ");";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
