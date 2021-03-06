package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.myapplication.constants.NovelImageConstants;
import com.example.myapplication.dbhelper.DataBaseHelper;
import com.example.myapplication.dbhelper.DbQuery;
import com.example.myapplication.workBean.WorksItem;
import java.util.ArrayList;
import java.util.List;

public class MyFragment3 extends Fragment {

    private List<WorksItem> list;
    private NovelImageConstants novelImageConstants;
    private DbQuery dbQuery;

    public MyFragment3() {
    }

    private void changeFlag(WorksItem worksItem, TextView isFavorite){
        boolean flag = dbQuery.isExist(worksItem.getWorksTitle(), getContext(), "favoriteTB");
        if (flag){
            isFavorite.setTextColor(isFavorite.getResources().getColor(R.color.colorRed));
        }else {
            isFavorite.setTextColor(isFavorite.getResources().getColor(R.color.colorGrey));
        }
    }

    private void initAll(View view){

        //UI
        ListView shareList = view.findViewById(R.id.list_view);

        //init data
        dbQuery = new DbQuery();
        novelImageConstants = new NovelImageConstants();
        list = new ArrayList<>();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), "BookManager");
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        list = DbQuery.search(sqLiteDatabase, "allworks");
        Log.i("FF",""+list);

        //init Adapter
        shareList.setAdapter(new MyAdpater());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.app_home, container, false);
        initAll(view);
        return view;

    }

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
            View newView;
            if (convertView == null){
                //??????????????????
                newView = View.inflate(getContext(), R.layout.app_home_item, null);
            }else{
                newView = convertView;
            }

            //[1.1]?????????item????????????????????????
            TextView bigTitle = newView.findViewById(R.id.big_title);
            TextView smallLabel = newView.findViewById(R.id.small_title);
            ImageView itemImage = newView.findViewById(R.id.item_image);
            final TextView isFavorite = newView.findViewById(R.id.flag);

            //[1.2]??????????????????item???
            final WorksItem worksItem = list.get(position);
            changeFlag(worksItem,isFavorite);

            //[1.3]???????????????????????????
            isFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean exist = dbQuery.isExist(worksItem.getWorksTitle(), getContext(), "favoriteTB");
                    if (exist){
                        //??????????????????
                        dbQuery.deleteFavorite(getContext(), worksItem);
                        isFavorite.setTextColor(isFavorite.getResources().getColor(R.color.colorGrey));
                    }else {
                        //??????????????????
                        dbQuery.insertFavorite(getContext(), worksItem);
                        isFavorite.setTextColor(isFavorite.getResources().getColor(R.color.colorRed));
                    }
                }
            });

            //[2]?????????????????????
            bigTitle.setText(worksItem.getWorksTitle());
            smallLabel.setText(worksItem.getWorksLabel());
            itemImage.setImageResource(novelImageConstants.getImageAllResId()[position%6]);

            //[3]????????????list???????????????
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