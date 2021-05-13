package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context) {
        super(context, "itcast.db", null, 1);
    }


    /**
     * 当数据库第一次创建的时候调用
     * 适合做表结构初始化 就是写sql语句
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // id一般以_id
        db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20),phone varchar(20))");
    }

    /**
     * 适合做升级数据库时
     * 这个适合做表结构更新
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table info add phone varchar(20)");

    }
}
