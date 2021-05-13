package com.example.comment;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class essLearn_Content extends AppCompatActivity {

    private Button note_submit;
    private TextView note_list;
    private EditText note_title,note_content;
    private DrawerLayout note_drawer;
    private Eduction_Cell eduction_cell;
    private LinearLayout note_showarea;
    static List<String> a =new ArrayList<>();


    private ImageView esscontent_ima;
    private TextView esscontent_title,esscontent_comefrom,esscontent_date,esscontent_main;
    private int esscontentchoose=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_esslearn);
        //显示返回上一级导航图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        InitView();
        InitEssDrawerNote();
        try {
            FileHelper.read(this);//初始化链表a中的数据
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 侧滑笔记栏设置
     */
    private void InitEssDrawerNote(){
        note_showarea=findViewById(R.id.note_showarea_ess);
        note_submit=findViewById(R.id.note_submit_ess);
        note_list=findViewById(R.id.note_list_ess);
        note_content=findViewById(R.id.note_content_ess);
        note_title=findViewById(R.id.note_title_ess);
        /**
        //软键盘弹出检测监听
        KeyboardStateObserver.getKeyboardStateObserver(this).setKeyboardVisibilityListener(new KeyboardStateObserver.OnKeyboardVisibilityListener() {
            @Override public void onKeyboardShow() {
                note_showarea.setVisibility(View.INVISIBLE);
            }
            @Override public void onKeyboardHide() {
                note_showarea.setVisibility(View.VISIBLE);
            }
        });
         */

        note_drawer=findViewById(R.id.note_drawer_ess);
        note_drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);   //关闭手势滑动，只允许点击“笔记”按钮弹出
        eduction_cell=findViewById(R.id.esscontent_bottomline);
        //重写原组件的“笔记”按钮监听
        eduction_cell.eductionCell_BtnSet(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note_drawer.openDrawer(Gravity.LEFT);  //触发打开抽屉
            }
        },"takenotes");
        //重写原组件的“收藏”按钮监听
        eduction_cell.eductionCell_BtnSet(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("debug","收藏按钮已设置");
            }
        },"collectit");
        //确认提交笔记
        eduction_cell.eductionCell_BtnSet(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog();//弹出评论
            }
        },"askquestions");
        note_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=note_title.getText().toString();
                String content=note_content.getText().toString();
                title+="\n";
                if(!(title.equals(""))&&!(content.equals(""))){
                    note_list.append("标题:"+title);
                    note_list.append("笔记:"+content+"\n\n");
                    note_title.setText(null);
                    note_content.setText(null);
                }
                else{
                    Toast.makeText(essLearn_Content.this,"标题或内容未输入", Toast.LENGTH_SHORT).show();
                }
                note_title.callOnClick();
            }
        });
    }

    /**
     * 初始化场景
     */
    private void InitView(){
        //寻找控件
        esscontent_title = findViewById(R.id.esscontent_title);
        esscontent_comefrom = findViewById(R.id.esscontent_comefrom);
        esscontent_date = findViewById(R.id.esscontent_date);
        esscontent_ima = findViewById(R.id.esscontent_ima);
        esscontent_main = findViewById(R.id.esscontent_main);
        //根据preview主题，改变图文内容
/**
 Intent intent = getIntent();
 Log.i("debug","contentchoose:" + intent.getStringExtra("contentchoose"));
 String esstype=intent.getStringExtra("esstype");
 esscontentchoose = Integer.parseInt(intent.getStringExtra("contentchoose"));
 */
        //实例化获取数据源，并装载数据内容
        EssEattingResource eer = new EssEattingResource();
        esscontent_title.setText(eer.getEssEatting_title(esscontentchoose));
        esscontent_comefrom.setText(eer.getEssEatting_comefrom(esscontentchoose));
        esscontent_date.setText(eer.getEssEatting_date(esscontentchoose));
        esscontent_main.setText(eer.getEssEatting_main(esscontentchoose));
        esscontent_ima.setImageResource(eer.getEssEatting_ima(esscontentchoose));

    }

    @Override //重写返回上一级
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 弹出评论
     */
    private void showBottomDialog() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_layout, null);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Mydapter adapter = new Mydapter();
        recyclerView.setAdapter(adapter);


        adapter.setData(a);

        bottomSheetDialog.setContentView(view);

        //设置背景透明
        try {
            // hack bg color of the BottomSheetDialog
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.setBackgroundResource(android.R.color.transparent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
    }

    public  class Mydapter extends RecyclerView.Adapter {

        private List<String> mData;

        public void setData(List<String> list) {

            mData = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_item,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.name.setText(mData.get(position) + "");
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }


        public  class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView name;

            public MyViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.bottom_sheet_dialog_item_name);
            }
        }
    }






}
