package com.example.td2sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DataBaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public DBManager(Context context){
        this.context = context;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if(dbHelper != null){
            dbHelper.close();
        }
    }

    public void insert(String name, String desc) {
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.SUBJECT, name);
        cv.put(DataBaseHelper.DESC, desc);
        db.insert(DataBaseHelper.TABLE_NAME, null, cv);
    }

    public Cursor fetch(){
        String [] columns = {
                DataBaseHelper._ID,
                DataBaseHelper.SUBJECT,
                DataBaseHelper.DESC,
        };

        Cursor cursor = db.query(DataBaseHelper.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null,
                null);

        if (cursor != null) cursor.moveToFirst();

        return cursor;
    }

    public int update(long _id, String name, String desc){
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.SUBJECT, name);
        cv.put(DataBaseHelper.DESC, desc);

        int update = db.update(DataBaseHelper.TABLE_NAME, cv, "_ID = ?", new String[]{String.valueOf(_id)});
        return update;
    }

    public void delete(long _id){
        db.delete(DataBaseHelper.TABLE_NAME, "_ID = ?", new String [] {String.valueOf(_id)});
    }

}
