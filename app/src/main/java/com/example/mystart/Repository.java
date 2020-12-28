package com.example.mystart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Repository {

    private final EventDataBaseHelper dbHelper;
    private SQLiteDatabase database;

    public Repository(Context context){
        dbHelper = new EventDataBaseHelper(context.getApplicationContext());
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[]{EventDataBaseHelper.COLUMN_ID,
                                        EventDataBaseHelper.COLUMN_NUMBER,
                                        EventDataBaseHelper.COLUMN_DESCRIPTION,
                                        EventDataBaseHelper.COLUMN_DATE_TIME
        };
        return database.query(EventDataBaseHelper.TABLE,columns,null,null,null,null,null);
    }

    public ArrayList<Event> getEvents(){
        ArrayList<Event> events = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(EventDataBaseHelper.COLUMN_ID));
            String number = cursor.getString(cursor.getColumnIndex(EventDataBaseHelper.COLUMN_NUMBER));
            String description = cursor.getString(cursor.getColumnIndex(EventDataBaseHelper.COLUMN_DESCRIPTION));
            String dateTime = cursor.getString(cursor.getColumnIndex(EventDataBaseHelper.COLUMN_DATE_TIME));
            events.add(new Event(id,number,description,dateTime));
        }
        cursor.close();
        return events;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, EventDataBaseHelper.TABLE);
    }

    public Event getEvent(int id){
        Event event = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",EventDataBaseHelper.TABLE, EventDataBaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            String number = cursor.getString(cursor.getColumnIndex(EventDataBaseHelper.COLUMN_NUMBER));
            String description = cursor.getString(cursor.getColumnIndex(EventDataBaseHelper.COLUMN_DESCRIPTION));
            String dateTime = cursor.getString(cursor.getColumnIndex(EventDataBaseHelper.COLUMN_DATE_TIME));
            event = new Event(id, number, description, dateTime);
        }
        cursor.close();
        return  event;
    }

    public long insert(Event event){

        ContentValues cv = new ContentValues();
        cv.put(EventDataBaseHelper.COLUMN_NUMBER, event.getNumber());
        cv.put(EventDataBaseHelper.COLUMN_DESCRIPTION, event.getDescription());
        cv.put(EventDataBaseHelper.COLUMN_DATE_TIME, event.getDateTime());

        return  database.insert(EventDataBaseHelper.TABLE, null, cv);
    }

    public long delete(int userId){

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return database.delete(EventDataBaseHelper.TABLE, whereClause, whereArgs);
    }

    public long update(Event event){

        String whereClause = EventDataBaseHelper.COLUMN_ID + "=" + String.valueOf(event.getId());
        ContentValues cv = new ContentValues();
        cv.put(EventDataBaseHelper.COLUMN_NUMBER, event.getNumber());
        cv.put(EventDataBaseHelper.COLUMN_DESCRIPTION, event.getDescription());
        cv.put(EventDataBaseHelper.COLUMN_DATE_TIME, event.getDateTime());
        return database.update(EventDataBaseHelper.TABLE, cv, whereClause, null);
    }

}
