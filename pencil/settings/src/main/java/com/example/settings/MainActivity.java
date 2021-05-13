package com.example.settings;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    MsgAdpater ad;

    List<Aa> dataList = new ArrayList<>();
    private String[] target_text = {"账号设置", "消息通知", "隐私", "用户协议",
            "意见反馈", "关于台美与帮助", "版本号", "切换账号", "登出账号"};
    private int[] target_image = {R.drawable.t1, R.drawable.t2, R.drawable.t3, R.drawable.t4, R.drawable.t5, R.drawable.t6, R.drawable.t1, R.drawable.t2, R.drawable.t3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecommendList_getData();
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ad = new MsgAdpater(dataList, MainActivity.this);
        recyclerView.setAdapter(ad);
    }

    private void RecommendList_getData() {

        for (int i = 0; i < 9; i++) {
            //这里这么直白，不用说了吧
            Aa a= new Aa();
            a.b = target_text[i];
            a.a = target_image[i];
            a.id = i+1;
            dataList.add(a);
        }
    }
}
