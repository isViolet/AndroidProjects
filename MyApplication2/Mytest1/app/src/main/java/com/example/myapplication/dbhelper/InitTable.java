package com.example.myapplication.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.constants.AllWorksConstants;

public class InitTable {

    public void initData(Context context){
        Log.i("sssd","3");
        SQLiteOpenHelper dataBaseHelper = new DataBaseHelper(context, "BookManager");
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Log.i("sssd","1");
        Cursor cursor = sqLiteDatabase.query("allworks",null,null,null,null,null,null);
        if (cursor!=null&&cursor.getCount()==0){
            Log.i("sssd","1");

            Toast.makeText(context, "数据表没有信息，开始插入数据", Toast.LENGTH_SHORT).show();
            AllWorksConstants allWorksConstants = new AllWorksConstants();
            int index = 0;
            while (allWorksConstants.getAllTitle()[index] != null){
                ContentValues values = new ContentValues();
                values.put("title",allWorksConstants.getAllTitle()[index]);
                values.put("label",allWorksConstants.getAllLabel()[index]);
                values.put("content",allWorksConstants.getAllContent()[index]);
                sqLiteDatabase.insert("allworks",null,values);
                ++index;
                if (index == 6){
                    return;
                }
            }
            Log.i("sssd","1");
            sqLiteDatabase.close();
            Log.i("sssd","2");
        }else {
            Toast.makeText(context, "有数据", Toast.LENGTH_SHORT).show();
        }
    }

}
