package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.adapter.MsgAdpater;
import com.example.myapplication.login.register.RegisterActivity;
import com.example.myapplication.workBean.Aa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyFragment5 extends Fragment {

//    private List<Aa> dataList = new ArrayList<>();
//    private String[] target_text = {"账号设置", "消息通知", "隐私", "用户协议",
//            "意见反馈", "关于台美与帮助", "版本号", "切换账号", "登出账号"};
//    private int[] target_image = {R.drawable.t1, R.drawable.t2, R.drawable.t3, R.drawable.t4,
//            R.drawable.t5, R.drawable.t6, R.drawable.t1, R.drawable.t2, R.drawable.t3};

    public MyFragment5() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_user, container, false);
//        RecommendList_getData();
        init(view);

        Log.e("HEHE", "5日狗");
        return view;
    }

    private void init(View view) {
        Button button = view.findViewById(R.id.unregister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RegisterActivity.class));
                Objects.requireNonNull(getActivity()).finish();
            }
        });
//        RecyclerView recyclerView = view.findViewById(R.id.msg_recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        MsgAdpater ad = new MsgAdpater(dataList, getContext());
//        recyclerView.setAdapter(ad);
    }

//    private void RecommendList_getData() {
//
//        if (!dataList.isEmpty()){
//            return; //防止切换fragment页面时导致recyclerView重复产生多个子项
//        }
//
//        for (int i = 0; i < 9; i++) {
//            //这里这么直白，不用说了吧
//            Aa a= new Aa();
//            a.b = target_text[i];
//            a.a = target_image[i];
//            a.id = i+1;
//            dataList.add(a);
//        }
//    }
}