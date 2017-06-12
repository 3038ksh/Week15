package com.example.kimsoohyeong.week15;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by KimSooHyeong on 2017. 6. 8..
 */

public class MyManageDB {
    private static MySQLiteDatabase database = null;
    private static SQLiteDatabase myDB2 = null;
    private static MyManageDB mInstance = null;

    public final static MyManageDB getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyManageDB(context);
        }
        return mInstance;
    }

    MyManageDB(Context context) {
        database = new MySQLiteDatabase(context, "myDB2", null, 1);
        myDB2 = database.getWritableDatabase();
    }

    public Cursor execSELECTStudent(String sql) {
        Cursor cursor = myDB2.rawQuery(sql, null);
        return cursor;
    }

    public void execINSERTStudent(String sql) {
        myDB2.execSQL(sql);
    }
}
