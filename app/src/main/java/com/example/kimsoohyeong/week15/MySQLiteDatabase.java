package com.example.kimsoohyeong.week15;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KimSooHyeong on 2017. 6. 8..
 */

public class MySQLiteDatabase extends SQLiteOpenHelper {
    public MySQLiteDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists students (" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "nickname text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists students";
        onCreate(db);
    }
}
