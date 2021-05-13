package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.dbhelper.DataBaseHelper;
import com.example.myapplication.workBean.WorksItem;

import java.util.ArrayList;
import java.util.List;

public class WorksMainActivity extends Activity{

    private List<WorksItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_page);
        initAll();
    }

    private void initAll(){

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        list = new ArrayList<>();
        assert title != null;
        Log.i("大声道",title +"");

        //UI
        TextView contentTitle = findViewById(R.id.content_workId);
        ListView contentList = findViewById(R.id.content_lv);


        contentTitle.setText(title);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this, "BookManager");
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        //查询所有
//        Cursor cursor = sqLiteDatabase.query("worksTable",null,"title = ?", new String[]{title},null,null,null);
        Cursor cursor = sqLiteDatabase.query("worksTable",null,null, null,null,null,null);
        Cursor cursor1 = sqLiteDatabase.query("allworks",null,null, null,null,null,null);
        if (cursor!=null&&cursor.getCount()>0){
            while(cursor.moveToNext()){
                if (!cursor.getString(1).equals(title)){
                    continue;
                }
                String content = cursor.getString(3);
                WorksItem worksItem = new WorksItem();
                worksItem.setWorksContent(content);
                list.add(worksItem);
                break;
            }
        }else if(cursor1!=null&&cursor1.getCount()>0){
            while(cursor1.moveToNext()){
                if (!cursor1.getString(1).equals(title)){
                    continue;
                }
                String content = cursor1.getString(3);
                WorksItem worksItem = new WorksItem();
                worksItem.setWorksContent(content);
                list.add(worksItem);
                break;
            }
        }
        contentList.setAdapter(new MyAdapter());
    }

    public void gotoWorks(View view) {
        finish();
    }

//    public void ce(View view) {
//        ImageView imageView = findViewById(R.id.test);
//        imageView.setAlpha(1);
//    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View view ;
            if (convertView == null){
                //创建新的对象
                view = View.inflate(getApplicationContext(), R.layout.content_page_item,null);
            }else{
                view = convertView;
            }
            TextView tv = view.findViewById(R.id.content_item_tv);
            WorksItem worksItem = list.get(i);
            tv.setText(worksItem.getWorksContent());
            return view;
        }
    }


}
