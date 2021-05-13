package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.myapplication.constants.NovelImageConstants;
import com.example.myapplication.dbhelper.DbQuery;
import com.example.myapplication.myUtils.MyTransformation;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyFragment1 extends Fragment  implements ViewPager.OnPageChangeListener{

    private List<Integer> imageViewList;
    private List<Integer> imageViewList1;
    private ViewPager viewPager;
    private ViewPager viewPager1;
    private Button restricton;
    private EditText search_box;
    private boolean isRunning = false;

    public MyFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.app_restriction, container, false);
        // 初始化布局 View视图
        initViews(view);

        // Model数据
        initData();

        // Controller 控制器
        initAdapter();
//
//        // 开启轮询
//        new Thread() {
//            public void run() {
//                isRunning = true;
//                while (isRunning) {
//
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    // 往下跳一位
//                    getActivity().runOnUiThread(new Runnable() {
//
//                        @Override
//                        public void run() {
////                            ++i;
////                            System.out.println("设置当前位置: " + viewPager.getCurrentItem());
////                            viewPager.setCurrentItem(500000 + i);
////                            viewPager1.setCurrentItem(500000 - i);
//                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
//                            cooo();
//                        }
//                    });
//                }
//            }
//            ;
//        }.start();
////        Log.e("HEHE", "1日狗");
        return view;
    }

    //    寻找布局控件id
    private void initViews(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.pager_recommend);

        viewPager1 = view.findViewById(R.id.pager_recommend1);

        search_box = view.findViewById(R.id.search_box);
        restricton = view.findViewById(R.id.restriction_btn);
        restricton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = search_box.getText().toString().trim();
                if (title.equals("")){
                    Toast.makeText(getContext(), "输入有误", Toast.LENGTH_SHORT).show();
                    return;
                }
                DbQuery dbQuery = new DbQuery();
                boolean isExist = dbQuery.isExist(title, getContext(), "allworks");
                if (isExist){
                    final Intent intent = new Intent(getActivity(),WorksMainActivity.class);
                    intent.putExtra("title",title);
                    Log.i("asfasf",title);
                    Objects.requireNonNull(getActivity()).startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "暂时没有该文章QAQ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //viewPager1.setOnPageChangeListener(this);
//		viewPager.setOffscreenPageLimit(1);// 左右各保留几个对象

        view.findViewById(R.id.recommend).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return viewPager.dispatchTouchEvent(motionEvent);
            }
        });
    }

    /**
     * 初始化要显示的数据
     */
    private void initData() {
        // 图片资源id数组
        NovelImageConstants novelImageConstants = new NovelImageConstants();
        int[] imageResIds = novelImageConstants.getImageAllResId();
        // 初始化要展示的5个ImageView
        imageViewList = new ArrayList<>();
        imageViewList1 = new ArrayList<>();
        for (int i = 0; i < imageResIds.length; i++) {
            // 初始化要显示的图片对象
            imageViewList.add(imageResIds[i]);
            imageViewList1.add(imageResIds[imageResIds.length-i-1]);
        }
    }

    private void initAdapter() {
        viewPager.setAdapter(new MyAdapter(getContext(),imageViewList));
        viewPager1.setAdapter(new MyAdapter1(getContext(),imageViewList1));
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(500000); // 设置到某个位置
        viewPager1.setCurrentItem(500000);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setPageTransformer(true, new MyTransformation());
        viewPager1.setPageTransformer(true,new MyTransformation());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i("TT","我滑动了");
        cooo();
    }

    private void cooo(){
        int cha = viewPager.getCurrentItem() - 500000;
        viewPager1.setCurrentItem(500000 - cha);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.i("TT","我滑动借宿了了");
    }

    class MyAdapter extends PagerAdapter {
        private Context context;
        private List<Integer> list;
        public MyAdapter(Context context,List<Integer> list){
            this.context=context;
            this.list=list;
        }
        public MyAdapter(){
            super();
        }
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        // 3. 指定复用的判断逻辑, 固定写法
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        // 1. 返回要显示的条目内容, 创建条目
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            System.out.println("instantiateItem初始化: " + position);
            int newPosition = position % list.size();
            ImageView imageView=new ImageView(context);
            Glide.with(context).load(list.get(newPosition)).into(imageView);
            container.addView(imageView);
            return imageView; // 必须重写, 否则报异常
        }

        // 2. 销毁条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // object 要销毁的对象
            System.out.println("destroyItem销毁: " + position);
            container.removeView((View) object);
        }
    }



    class MyAdapter1 extends PagerAdapter {
        private Context context;
        private List<Integer> list;
        public MyAdapter1(Context context,List<Integer> list){
            this.context=context;
            this.list=list;
        }
        public MyAdapter1(){
            super();
        }
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        // 3. 指定复用的判断逻辑, 固定写法
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        // 1. 返回要显示的条目内容, 创建条目
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            System.out.println("instantiateItem初始化: " + position);
            int newPosition = position % list.size();
            ImageView imageView=new ImageView(context);
            Glide.with(context).load(list.get(newPosition)).into(imageView);
            container.addView(imageView);
            return imageView; // 必须重写, 否则报异常
        }

        // 2. 销毁条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // object 要销毁的对象
            System.out.println("destroyItem销毁: " + position);
            container.removeView((View) object);
        }
    }

}