package com.example.zh.park;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zh on 16/7/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String CREATE_USER="create table user("
            +"id integer primary key autoincrement,"
            +"phone integer,"
            +"password text)";

    public static final String CREATE_PARK="create table park("
            +"id integer primary key autoincrement,"
            + "owner_id integer,"
            +"tenant_id integer,"
            +"price real,"
            +"remark text,"
            +"court_name text)";

    public static final String CREATE_ORDER="create table orders("
            +"id integer primary key autoincrement,"
            +"park_id integer,"
            +"date text,"
            +"tenant_id integer,"
            +"price real)";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CREATE_PARK);
        sqLiteDatabase.execSQL(CREATE_ORDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
