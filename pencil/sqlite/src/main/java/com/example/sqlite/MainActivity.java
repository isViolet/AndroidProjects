package com.example.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private MyOpenHelper myOpenHelper;
    private ListView listView;
    private List<Person> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myOpenHelper = new MyOpenHelper(this) ;
        listView = findViewById(R.id.lv);
        //定义一个集合用于存在listview
        list = new ArrayList<Person>();
    }

    public void click1(View v){
        SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name","wangwu");
            values.put("phone","110");
            long insert = sqLiteDatabase.insert("info",null,values);

            sqLiteDatabase.close();

            if (insert>0){
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();

        }
    }

    public void click2(View view){
        SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();
        //返回值int代表删除的行数
        int delete = sqLiteDatabase.delete("info","name=?",new String[]{"wangwu"});
        sqLiteDatabase.close();
        Toast.makeText(this,"删除了"+delete+"行", Toast.LENGTH_SHORT).show();
    }

    public void click3(View view){
        SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("phone","114");
        int upgrade = sqLiteDatabase.update("info",values,"name=?",new String[]{"wangwu"});
        Toast.makeText(this,"更新了"+upgrade+"行", Toast.LENGTH_SHORT).show();
    }

    public void click4(View view){
        SQLiteDatabase sqLiteDatabase = myOpenHelper.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.query("info",null,"name=?",new String[]{"wangwu"},null,null,null);
        //查询所有
        Cursor cursor = sqLiteDatabase.query("info",null,null,null,null,null,null);
        if (cursor!=null&&cursor.getCount()>0){
            while(cursor.moveToNext()){
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                //封装到javabean
                Person person = new Person();
                person.setName(name);
                person.setPhone(phone);
                //把javabean对象加入到集合
                list.add(person);

            }
            listView.setAdapter(new MyAdpater());
        }
    }

    //定义listview的适配器
    private class MyAdpater extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view ;
            if (convertView == null){
                //创建新的对象
                view = View.inflate(getApplicationContext(), R.layout.item,null);
            }else{
                view = convertView;
            }

            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_phone = view.findViewById(R.id.tv_phone);
            Person person = list.get(position);
            tv_name.setText(person.getName());
            tv_phone.setText(person.getPhone());
            return view;
        }
    }

}
