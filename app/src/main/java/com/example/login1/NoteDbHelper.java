package com.example.login1;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class NoteDbHelper extends SQLiteOpenHelper {
 
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "NoteManager";
 
    // tasks table name
    private static final String TABLE_TASKS = "Notes";
 
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NOTENAME = "noteName";
    private static final String KEY_NOTE = "note";
 
    public NoteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
    	//db.execSQL("DROP TABLE tasks");
        String sql = "CREATE TABLE " + TABLE_TASKS + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NOTENAME+ " TEXT, "
                + KEY_NOTE + " TEXT)";
        db.execSQL(sql);
      //  db.close();
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
         // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        // Create tables again
        onCreate(db);
        db.close();
    }
    
    public void addTask(NoteTask task) {
        SQLiteDatabase db = this.getWritableDatabase();
         
        ContentValues values = new ContentValues();
        values.put(KEY_NOTENAME, task.getTaskName()); // task name
         // status of task- can be 0 for not done and 1 for done
        values.put(KEY_NOTE, task.getNote()); 
         
        // Inserting Row
        db.insert(TABLE_TASKS, null, values);
        db.close(); // Closing database connection
    }

    
    public List<NoteTask> getAllTasks() {
        List<NoteTask> taskList = new ArrayList<NoteTask>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	NoteTask task = new NoteTask();
                task.setId(cursor.getInt(0));
                task.setTaskName(cursor.getString(1));
                task.setNote(cursor.getString(2));
                // Adding contact to list
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        db.close();
        // return task list
        return taskList;
    }
     
    
    public void updateTask(NoteTask task) {
        // updating row
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues values = new ContentValues();
       values.put(KEY_NOTENAME, task.getTaskName());
       values.put(KEY_NOTE, task.getNote());
       db.update(TABLE_TASKS, values, KEY_ID + " = ?",
       new String[]{String.valueOf(task.getId())});
       
       // db.delete(TABLE_TASKS, KEY_ID + " = ?", new String[]{String.valueOf(task.getId())});
        db.close();
       
    }
    
    public void deleteTask(NoteTask task)
    {
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.delete(TABLE_TASKS, KEY_ID + " = ?", new String[]{String.valueOf(task.getId())});
        db.close();
    }
    
    public String onSelect(NoteTask task) {
        String name=task.getNote();
        return name;
        //db.close();
    /*	String sql="SELECT * FROM " + TABLE_TASKS; 
    	SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
        	do
        	{
            	//NoteTask task1 = new NoteTask();
               // task1.setId(cursor.getInt(0));
               // task1.setTaskName(cursor.getString(1));
                //task1.setNote(cursor.getString(2));
                cursor.moveToNext();
        	}while(!(task.getNote().toString().equals(cursor.getString(cursor.getColumnIndex("noteName")).toString())));
        cursor.moveToPrevious();
		return cursor.getString(cursor.getColumnIndex("noteName")).toString();
        }
        else
        	return "NULL";*/
       
   }
}



