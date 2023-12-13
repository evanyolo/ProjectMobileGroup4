package com.example.group4_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbLite extends SQLiteOpenHelper {
    private static final String db_Name = "Logins.db";
    private static final String tb_Name = "emails";
    private static final String col_1 ="ID";
    private static final String col_2 ="emails";
    public DbLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + tb_Name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tb_Name);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,email);
        long result = db.insert(tb_Name, null, contentValues);
        return result != -1;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + tb_Name, null);
    }


}
