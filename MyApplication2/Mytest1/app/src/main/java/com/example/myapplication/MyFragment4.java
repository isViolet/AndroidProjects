package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.myapplication.dbhelper.DataBaseHelper;
import com.example.myapplication.dbhelper.DbQuery;
import com.example.myapplication.workBean.WorksItem;

import java.util.ArrayList;
import java.util.List;

public class MyFragment4 extends Fragment {

    private List<WorksItem> itemList;

    public MyFragment4() {
    }

    private void initAll(View view){

        //UI
        ListView worksListView = view.findViewById(R.id.works_body);
        Button writeBtn = view.findViewById(R.id.works_create);

        //编辑按钮跳转事件
        writeBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),WorksWriteMainActivity.class));//先获取当前的Activity在进行跳转
            }
        });

        //data
        itemList = new ArrayList<>();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), "BookManager");
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        itemList = DbQuery.search(sqLiteDatabase, "WorksTable");
        worksListView.setAdapter(new MyFragment4.WorksAdapter());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_write, container, false);
        initAll(view);
        return view;
    }

    class WorksAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return itemList.size();
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
        public View getView(int i, View view, ViewGroup viewGroup) {

            View newView ;
            if (view == null){
                //创建新的对象
                newView = View.inflate(getContext(), R.layout.app_write_item,null);
            }else{
                newView = view;
            }

            //[1.1]初始化item布局下的各个控件
            TextView workTitle = newView.findViewById(R.id.works_item_title);

            //[2]获取集合的item
            WorksItem worksItem = itemList.get(i);

            //[3]设置空间属性
            workTitle.setText(worksItem.getWorksTitle());

            //[4]设置跳转事件
            final Intent intent = new Intent(getActivity(),WorksMainActivity.class);
            intent.putExtra("title",worksItem.getWorksTitle());
            newView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
            return newView;
        }
    }

}