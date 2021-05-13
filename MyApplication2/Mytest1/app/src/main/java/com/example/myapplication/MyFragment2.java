package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.myapplication.dbhelper.DataBaseHelper;
import com.example.myapplication.dbhelper.DbQuery;
import com.example.myapplication.workBean.WorksItem;
import java.util.ArrayList;
import java.util.List;

public class MyFragment2 extends Fragment {

    private ViewPager viewPager;
    private List<WorksItem> list;

    public MyFragment2() {
    }
    public MyFragment2(ViewPager viewPager){
        this.viewPager = viewPager;
    }

    private void initAll(View view){

        DbQuery dbQuery = new DbQuery();
        list = new ArrayList<>();

        //UI
        ListView listView = view.findViewById(R.id.favorite);
        listView.setAdapter(new favoriteAdapter());
        LinearLayout favoriteLayout = view.findViewById(R.id.save);
        if (dbQuery.isNotEmpty(getContext(), "favoriteTB")){
            favoriteLayout.setVisibility(View.INVISIBLE);
        }else {
            favoriteLayout.setVisibility(View.VISIBLE);
        }

        Button addBtn = view.findViewById(R.id.add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
                Toast.makeText(getContext(), "点击收藏", Toast.LENGTH_SHORT).show();
            }
        });

        //data
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(),"BookManager");
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        list = DbQuery.search(sqLiteDatabase, "favoriteTB");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_favorite, container, false);
        initAll(view);
        Log.e("HEHE", "000");
        return view;
    }

    private class favoriteAdapter extends BaseAdapter{

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
        public View getView(int i, View view, ViewGroup viewGroup) {
            View newView;
            if (view == null){
                //创建新的对象
                newView = View.inflate(getContext(), R.layout.app_write_item, null);
            }else{
                newView = view;
            }

            //[1.1]初始化item布局下的各个控件
            TextView workTitle = newView.findViewById(R.id.works_item_title);
            TextView workLabel = newView.findViewById(R.id.works_item_label);

            //[2]获取集合的item
            WorksItem worksItem = list.get(i);

            //[3]设置空间属性
            workTitle.setText(worksItem.getWorksTitle());
            workLabel.setText(worksItem.getWorksLabel());

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