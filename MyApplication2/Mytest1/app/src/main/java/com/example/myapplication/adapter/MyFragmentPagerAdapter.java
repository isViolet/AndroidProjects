package com.example.myapplication.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.MainActivity;
import com.example.myapplication.MyFragment1;
import com.example.myapplication.MyFragment2;
import com.example.myapplication.MyFragment3;
import com.example.myapplication.MyFragment4;
import com.example.myapplication.MyFragment5;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 5;
    private ViewPager viewPager;
    private MyFragment5 myFragment5 = null;
    private MyFragment1 myFragment1 = null;
    private MyFragment2 myFragment2 = null;
    private MyFragment3 myFragment3 = null;
    private MyFragment4 myFragment4 = null;


    public MyFragmentPagerAdapter(FragmentManager fm, ViewPager viewPager) {
        super(fm);
        myFragment1 = new MyFragment1();
        myFragment2 = new MyFragment2(viewPager);
        myFragment3 = new MyFragment3();
        myFragment4 = new MyFragment4();
        myFragment5 = new MyFragment5();
        this.viewPager = viewPager;
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = myFragment1;
                break;
            case MainActivity.PAGE_TWO:
                fragment = myFragment2;
                break;
            case MainActivity.PAGE_THREE:
                fragment = myFragment3;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = myFragment4;
                break;
            case MainActivity.PAGE_FIVE:
                fragment = myFragment5;
                break;
        }
        return fragment;
    }

}
