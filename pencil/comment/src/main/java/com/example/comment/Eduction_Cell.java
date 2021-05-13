package com.example.comment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;


public class Eduction_Cell extends LinearLayout {
    private Button note_submit;
    private TextView note_list;
    private EditText note_title,note_content;
    private DrawerLayout note_drawer;
    private int collect_flag=0;

    private ImageView askquestions,takenotes,collectit;
    private EditText asking_edit;
    private Context context;

    public Eduction_Cell(Context context) {
        this(context, null);
        this.context=context;
    }
    public Eduction_Cell(final Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        //导入布局
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.cell_eduction,this,true);
        InitView();
        //InitDrawerNote();
    }
    private void InitView(){
        asking_edit=findViewById(R.id.asking_edit);
        asking_edit.setCursorVisible(false);
        asking_edit.setFocusable(false);
        asking_edit.setFocusableInTouchMode(false);
        asking_edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog myDialog=new MyDialog(context, R.layout.cell_my_dialog);
                myDialog.show();
                //asking_edit.clearFocus();
            }
        });
        askquestions=findViewById(R.id.askquestions);
        takenotes=findViewById(R.id.takenotes);
        collectit=findViewById(R.id.collectit);
    }
    //    private void InitDrawerNote(){
//        note_submit=findViewById(R.id.note_submit);
//        note_list=findViewById(R.id.note_list);
//        note_content=findViewById(R.id.note_content);
//        note_title=findViewById(R.id.note_title);
//        note_drawer=findViewById(R.id.note_drawer);
//        note_drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);   //关闭手势滑动
//        //确认提交笔记
//        note_submit.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title=note_title.getText().toString();
//                String content=note_content.getText().toString();
//                title+="\n";
//                if(!(title.equals(""))&&!(content.equals(""))){
//                    note_list.append("标题:"+title);
//                    note_list.append("笔记:"+content+"\n\n");
//                    note_title.setText(null);
//                    note_content.setText(null);
//                }
//                else{
//                    Toast.makeText(context,"标题或内容未输入",Toast.LENGTH_SHORT).show();
//                }
//                note_title.callOnClick();
//            }
//        });
//    }
    public void eductionCell_BtnSet(OnClickListener l, String id){
        switch (id){
            case "askquestions":
                askquestions.setOnClickListener(l);
                //Toast.makeText(context,"点击提问评论",Toast.LENGTH_SHORT).show();
                break;
            case "takenotes":
                takenotes.setOnClickListener(l);
                //Toast.makeText(context,"点击做笔记",Toast.LENGTH_SHORT).show();
                break;
            case "collectit":
                collectit.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(collect_flag==0){
                            collectit.setImageResource(R.drawable.collect02);
                            collect_flag=1;
                        }else if(collect_flag==1){
                            collectit.setImageResource(R.drawable.collect01);
                            collect_flag=0;
                        }
                    }
                });
                //Toast.makeText(context,"点击收藏此页",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
