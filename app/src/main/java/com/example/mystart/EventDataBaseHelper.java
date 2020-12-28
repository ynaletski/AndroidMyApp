package com.example.mystart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "event_database.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "events";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE_TIME = "datetime";

    public EventDataBaseHelper(Context context) {

        super(context, DB_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY" +
                " AUTOINCREMENT UNIQUE ," + COLUMN_NUMBER + " TEXT NOT NULL, " + COLUMN_DESCRIPTION
                 + " TEXT NOT NULL, " + COLUMN_DATE_TIME + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

}
