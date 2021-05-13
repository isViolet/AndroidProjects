package com.example.myapplication.login.banner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.dbhelper.DataBaseHelper;
import com.example.myapplication.dbhelper.InitTable;
import com.example.myapplication.login.enter.EnterActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {
    private List<BannerBean> mBannerBeanList;
    private Banner mBannerView;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.banner_layout);
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this,"BookManager");
            InitTable initTable = new InitTable();
            initTable.initData(this);
            //if语句去标题
            if (getSupportActionBar() != null){
                getSupportActionBar().hide();
            }
        initViews();
        initDatas();
        final Button countdown= findViewById(R.id.Countdown);
        //倒计时总时间为5S，时间防止从6s开始显示
        MyCountTimer myCountTimer = new MyCountTimer(2000, 1000, countdown ,"skip");
        myCountTimer.start();
        countdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Countdown:
                        countdown.setSelected(true);
                        break;
                    default:
                        break;
                }
                Toast.makeText(BannerActivity.this,"You have entered the APP interface.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BannerActivity.this, EnterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews() {
        mBannerView = findViewById(R.id.banner);
    }

    private void initDatas() {
        mBannerBeanList = new ArrayList<BannerBean>();
        BannerBean bannerBean1 = new BannerBean();
        bannerBean1.setTitle("1");
        bannerBean1.setImgUrl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2577002433,4177622543&fm=26&gp=0.jpg");
        //bannerBean1.setUrlPath("https://www.baidu.com/?tn=62095104_2_oem_dg.html");

        BannerBean bannerBean2 = new BannerBean();
        bannerBean2.setTitle("2");
        bannerBean2.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569152217564&di=2a7d1c70aacd3f22e297005478a43d9c&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F2b9dc298fc82114119de8154f82251cbd613725f3db656-Yi1iXS_fw658");
        //bannerBean2.setUrlPath("https://www.baidu.com/?tn=62095104_2_oem_dg.html");

        BannerBean bannerBean3 = new BannerBean();
        bannerBean3.setTitle("3");
        bannerBean3.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570023371081&di=00e75402eca9a9b62088299e4de3d9c2&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201612%2F02%2F20161202223430_JGjmL.jpeg");
        //bannerBean3.setUrlPath("https://www.baidu.com/?tn=62095104_2_oem_dg.html");

        mBannerBeanList.add(bannerBean1);
        mBannerBeanList.add(bannerBean2);
        mBannerBeanList.add(bannerBean3);

        initBanner();//设置Banner配置，必须在设置Banner数据之前执行
        initBannerData();//设置Banner的数据
        initBannerEvent();//设置Banner监听事件
    }
    //设置Banner配置，必须在设置Banner数据之前执行
    private void initBanner() {
        //轮播图的常规设置
        mBannerView.setIndicatorGravity(BannerConfig.RIGHT);//设置指示器局右显示
        //====加载Banner数据====
        mBannerView.setImageLoader(new BannerImageLoader());//设置图片加载器
        //设置显示圆形指示器和标题（水平显示）
        mBannerView.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
    }

    //设置Banner的数据
    private void initBannerData() {
        List<String> images = new ArrayList<String>();
        List<String> titles = new ArrayList<String>();
        for(BannerBean bannerBean : mBannerBeanList){
            images.add(bannerBean.getImgUrl());
            titles.add(bannerBean.getTitle());
        }
        mBannerView.setImages(images);
        mBannerView.setBannerTitles(titles);
        //banner设置方法全部调用完毕时最后调用
        mBannerView.start();
    }

    //设置Banner监听事件
    private void initBannerEvent() {
        //设置banner的滑动监听
        mBannerView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                String title = mBannerBeanList.get(position).getTitle();
                //Toast.makeText(BannerActivity.this,title,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //设置点击事件，下标是从0开始
        mBannerView.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                String urlPath = mBannerBeanList.get(position).getUrlPath();
                //Toast.makeText(BannerActivity.this,urlPath,Toast.LENGTH_SHORT).show();
            }
        });
    }
}