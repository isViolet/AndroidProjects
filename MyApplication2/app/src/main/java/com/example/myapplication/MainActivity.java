package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    //UI Objects
    private ViewPager vpager;
    private MyFragmentPagerAdapter mAdapter;
    private RelativeLayout r1;
    private RelativeLayout r2;
    private RelativeLayout r3;
    private RelativeLayout r4;
    private RelativeLayout r5;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    public static final int PAGE_FIVE = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
    }

    private void bindViews() {

        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);
        r5 = findViewById(R.id.r5);
        vpager = (ViewPager) findViewById(R.id.pagerControl);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void re1(View view) {
        vpager.setCurrentItem(PAGE_ONE);
    }

    public void re2(View view) {
        vpager.setCurrentItem(PAGE_TWO);
    }

    public void re3(View view) {
        vpager.setCurrentItem(PAGE_THREE);
    }

    public void re4(View view) {
        vpager.setCurrentItem(PAGE_FOUR);
    }

    public void re5(View view) {
        vpager.setCurrentItem(PAGE_FIVE);
    }
}
