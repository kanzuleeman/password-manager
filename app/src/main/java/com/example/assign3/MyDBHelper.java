package com.example.assign3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper{
    private final String DATABASE_NAME="MYDBHelper";
    private final String DATABASE_TABLE="RegisterTable";
    private final int DATABASE_VERSION='1';
    public static final String ROW_ID="_id";
    public static final String ROW_USERNAME="_username";
    public static final String ROW_PASSWORD="_password";

    public Context ourcontext;
    private SQLiteDatabase ourDatabase;
    private DBHelper ourHelper;

    public MyDBHelper(Context context) {
        ourcontext=context;
    }
    public void open(){
       ourHelper=new DBHelper(ourcontext);
       ourDatabase=ourHelper.getWritableDatabase();

    }
    public void close()
    {
        ourHelper.close();
    }
    private class DBHelper extends SQLiteOpenHelper{

        public DBHelper(@Nullable Context context ) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query="CREATE TABLE "+DATABASE_TABLE+"("
                    +ROW_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +ROW_USERNAME+" TEXT NOT NULL,"
                    +ROW_PASSWORD+" TEXT NOT NULL);"
                    ;
            db.execSQL(query);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);


        }
    }

    public long register(String username,String password){
        ContentValues cv=new ContentValues();
        cv.put(ROW_USERNAME,username);
        cv.put(ROW_PASSWORD,password);
        return ourDatabase.insert(DATABASE_TABLE,null,cv);
    }
    public boolean checkUsername(String username) {
        Cursor cursor = ourDatabase.query(DATABASE_TABLE, new String[]{ROW_ID}, ROW_USERNAME + "=?", new String[]{username}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }

    public boolean checkPassword(String username, String password) {
        Cursor cursor = ourDatabase.query(DATABASE_TABLE, new String[]{ROW_PASSWORD}, ROW_USERNAME + "=?", new String[]{username}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String dbPassword = cursor.getString(0);
            cursor.close();
            return dbPassword.equals(password);
        }
        return false;
    }
}