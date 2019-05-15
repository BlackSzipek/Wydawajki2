package com.example.molly.wydawajki2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RecreationTable
{
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public RecreationTable(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void openDB()
    {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        Log.d("jest", "dtable");

    }

    public void closeDB()
    {
        sqLiteDatabase.close();
    }

    public void insertRecord(Integer duserid, Double dusersum,String ddate)
    {
        ContentValues contentValues =  new ContentValues();
        contentValues.put(DBHelper.COL_DUSERID,duserid);
        contentValues.put(DBHelper.COL_DUSERSUM,dusersum);
        contentValues.put(DBHelper.COL_DDATE,ddate);
        sqLiteDatabase.insert(DBHelper.TABLE_RNAME,null,contentValues);
        Log.d("jest", "wpisano do recreation");
    }

    public Cursor getAllRecords()
    {
        Log.d("jest", "wypisujenmy");
        return sqLiteDatabase.rawQuery("select * from "+DBHelper.TABLE_RNAME,null);

    }

    public void deleteAllHouseExpenses()
    {
        sqLiteDatabase.delete(DBHelper.TABLE_RNAME,null,null);
    }

    public Cursor getSumAndDate(Integer userid)
    {
        Log.d("jest", "suma+data");
        return sqLiteDatabase.rawQuery("select "+DBHelper.COL_DUSERSUM+","+DBHelper.COL_DDATE+" from "+DBHelper.TABLE_RNAME
                +" where "+DBHelper.COL_DUSERID+"="+userid,null);

    }

    public Cursor getSumOfExpenses(Integer userid)
    {
        Log.d("jest", "suma");
        return sqLiteDatabase.rawQuery("select SUM("+DBHelper.COL_DUSERSUM+") "+" from "+DBHelper.TABLE_RNAME
                +" where "+DBHelper.COL_DUSERID+"="+userid,null);

    }
}
