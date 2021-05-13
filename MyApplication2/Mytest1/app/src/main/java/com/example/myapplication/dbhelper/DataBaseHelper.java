package com.example.myapplication.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.wifi.WifiManager;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context, String dataBaseName) {
        super(context, dataBaseName + ".db", null, 1);
    }

    /**
     * 当数据库第一次创建的时候调用
     * 适合做表结构初始化 就是写sql语句
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // id一般以_id
//        if (sqlStruct == null){
//            return;
//        }
//        db.execSQL(sqlStruct);
        //db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20),phone varchar(20))");
        String sql = "create table allworks(id integer primary key,title varchar(20),label varchar(20),content varchar(65530))";
        db.execSQL(sql);
        String sql1 = "create table worksTable(id integer primary key,title varchar(20),label varchar(20),content varchar(110))";
        db.execSQL(sql1);
        String sql2 = "create table favoriteTB(id integer primary key,title varchar(20),label varchar(20),content varchar(65530))";
        db.execSQL(sql2);
        Log.i("xsacd","sa");
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
        //db.execSQL("alter table info add phone varchar(20)");
        String sql = "create table allworks(id integer primary key,title varchar(20),label varchar(20),content varchar(65530))";
        db.execSQL(sql);
    }
}
