package com.example.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.PrecomputedText;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="userdetails.db";
    public static final String COL1 ="name";
    public static final String COL2 ="id";
    public static final String COL3 ="fname";
    public static final String COL4 ="adress";
    public static final String COL5 ="gender";
    public static final String COL6 ="education";
    public static final String COL7="phone";


Context context;


    public DatabaseHelper(Context context) {

        super(context, "userdata.db", null,1);
        this.context=context;
    }




    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create TABLE " + "userdetails" + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+"name TEXT,fname TEXT,adress TEXT,gender TEXT,education TEXT,phone INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }
    public  boolean insertdata (String name ,String id,String fname,String adress,String gender,String education,String phone) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, id);
        contentValues.put(COL3, fname);
        contentValues.put(COL4, adress);
        contentValues.put(COL5, gender);
        contentValues.put(COL6, education);
        contentValues.put(COL7, phone);

        long result =database.insert("userdetails",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }
    public  boolean updatedata (String name ,String id,String fname,String adress,String gender,String education,String phone) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, id);
        contentValues.put(COL3, fname);
        contentValues.put(COL4, adress);
        contentValues.put(COL5, gender);
        contentValues.put(COL6, education);
        contentValues.put(COL7, phone);
        Cursor cursor = database.rawQuery("select *from userdetails where name = ?", new String[] {name});
        if (cursor.getCount()>0) {
            long result = database.update("userdetails", contentValues, "name=?", new String[] {name});
            if (result == -1){

                return false;
            }
            else{
                return true;
            }

        }
        else {
            return false;
        }
    }







    public  Cursor getData() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor=database.rawQuery("select *from userdetails",null);
return cursor;
    }
    public int count()
    {
        String query="select *from userdetails";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor=database.rawQuery(query,null);
        return cursor.getCount();


    }

}
