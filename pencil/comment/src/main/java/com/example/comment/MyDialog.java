package com.example.comment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyDialog extends Dialog {
    private TextView dialogcancel_btn,dialogsubmit_btn;
    private EditText et_input;

    public MyDialog(Context context) {
        this(context, 0);
    }
    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cell_my_dialog);
        et_input=findViewById(R.id.et_input);
        showSoftInputFromWindow(et_input);
        PopDialog();
    }
    private void PopDialog(){
        //dialog弹出后,点击屏幕其他位置或物理返回键，dialog消失
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        //获得窗口
        Window window = getWindow();
        //window.setGravity(Gravity.BOTTOM);//dialog底部弹出
        //设置dialog窗口大小
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        window.setWindowAnimations(R.style.BottomDialogAnimation);
        //设置取消和提问事件监听
        dialogcancel_btn=findViewById(R.id.dialogcancel_btn);
        dialogsubmit_btn=findViewById(R.id.dialogsubmit_btn);
        dialogcancel_btn.setOnClickListener(btn_listener);
        dialogsubmit_btn.setOnClickListener(btn_listener);
    }
    //取消和提问事件编写
    private TextView.OnClickListener btn_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.dialogcancel_btn:
                    hide();//隐藏对话框
                    Log.i("debug","隐藏对话框");
                    break;
                case R.id.dialogsubmit_btn:
                    dismiss();
                    String asking_content=et_input.getText().toString();
                    essLearn_Content.a.add(asking_content);//添加数据
                    try {
                        FileHelper.save(getContext(),asking_content);//保存到文本
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getContext(),"已提问\n"+asking_content, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private void showSoftInputFromWindow(EditText editText){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        //inputManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        inputManager.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }


}

