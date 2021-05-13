package com.example.myapplication.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.example.myapplication.workBean.WorksItem;
import java.util.ArrayList;
import java.util.List;

public class DbQuery {

    public static List<WorksItem> search(SQLiteDatabase sqLiteDatabase, String table){

        //查找创作的作品
        List<WorksItem> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(table,null,null,null,null,null,null);
        if (cursor!=null&&cursor.getCount()>0){
            while(cursor.moveToNext()){
                String title = cursor.getString(1);
                String label = cursor.getString(2);
                String content = cursor.getString(3);
                //封装到javabean
                WorksItem worksItem = new WorksItem();
                worksItem.setWorksTitle(title);
                worksItem.setWorksLabel(label);
                worksItem.setWorksContent(content);
                //把javabean对象加入到集合
                list.add(worksItem);
            }
        }
        return list;
    }

    public boolean isExist(String title, Context context, String table){

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context, "BookManager");
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(table,null,null,null,null,null,null);
        if (cursor!=null&&cursor.getCount()>0){
            while(cursor.moveToNext()){
                if (cursor.getString(1).equals(title)){
                    sqLiteDatabase.close();
                    return true;
                }
            }
        }
        sqLiteDatabase.close();
        return false;
    }

    public boolean isNotEmpty(Context context, String table){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context, "BookManager");
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(table,null,null,null,null,null,null);
        return cursor != null && cursor.getCount() > 0;
    }

    public void insertFavorite(Context context, WorksItem worksItem){

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context,"BookManager");
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("favoriteTB",null,"title = ?",new String[]{worksItem.getWorksTitle()},null,null,null);
        if (cursor!=null&&cursor.getCount()==0){
            Toast.makeText(context, "数据表没有信息，开始插入数据", Toast.LENGTH_SHORT).show();
            ContentValues values = new ContentValues();
            values.put("title",worksItem.getWorksTitle());
            values.put("label",worksItem.getWorksLabel());
            values.put("content",worksItem.getWorksContent());
            sqLiteDatabase.insert("favoriteTB",null,values);
            sqLiteDatabase.close();
        }else {
            Toast.makeText(context, "有数据", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteFavorite(Context context, WorksItem worksItem){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context,"BookManager");
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        sqLiteDatabase.delete("favoriteTB","title=?",new String[]{worksItem.getWorksTitle()});
    }

}
