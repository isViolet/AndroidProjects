package com.example.myapplication.login.forget;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.login.login.DBOpenHelper;
import com.example.myapplication.login.login.User;
import com.example.myapplication.login.register.RegisterActivity;
import com.example.myapplication.login.waterbutton.MyButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends  AppCompatActivity implements View.OnClickListener {

    private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frogetpassword_layout);
        //固定位置，在setContentView()之下,否则无论写的什么逻辑  都不会在Activity中起作用
        ButterKnife.bind(this);
        mDBOpenHelper = new DBOpenHelper(this);
        //if语句去标题
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
    @BindView(R.id.btn_update_password)
    MyButton confirm;
    @BindView(R.id.iv_loginactivity_back)
    ImageView back_1;
    @BindView(R.id.tv_loginactivity_login)
    TextView back_2;

    @BindView(R.id.et_username)
    EditText et_username_1;
    @BindView(R.id.et_username_2)
    EditText et_ueername_2;

    @OnClick({
            R.id.btn_update_password,
            R.id.iv_loginactivity_back,
            R.id.tv_loginactivity_login,
            R.id.et_username,
            R.id.et_username_2,
    })

    public void onClick(View view){
        String username_1 = et_username_1.getText().toString().trim();
        String username_2 = et_ueername_2.getText().toString().trim();
        switch (view.getId()){
            case R.id.btn_update_password:
                //如果old或new输入框为空，返回空，如果old输入框不为空，判断old和new输入框是否一致，
                // 若一致,进一步判断是否与原来保存在SQLite数据库的用户名所匹配，若匹配返回弹窗内容，若不匹配则输出该用户名未注册
                // 若不一致，则输出输入的注册名不一致

                //判断old或new输入框为空
                if(!TextUtils.isEmpty(username_1)||!TextUtils.isEmpty(username_2)){
                    //判断old和new输入框是否一致
                    User user = null;
                    if(username_1.equals(username_2)){
                        ArrayList<User> data = mDBOpenHelper.getAllData();
                        boolean match = false;
                        for(int i=0;i<data.size();i++) {
                            user = data.get(i);
                            if ( username_1.equals(user.getName())){
                                match = true;
                                break;
                            }else{
                                match = false;
                            }
                        }
                        //判断是否与原来保存在SQLite数据库的用户名所匹配
                        if(match) {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(ForgetPasswordActivity.this);
                            dialog.setTitle("重置密码--" + username_1 + "用户您好：");
                            dialog.setMessage("您的原密码是："+ user.getPassword());
                            dialog.setCancelable(false);
                            dialog.setPositiveButton("确认并返回", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent_1 = new Intent(ForgetPasswordActivity.this, RegisterActivity.class);
                                    startActivity(intent_1);
                                    finish();
                                }
                            });
                            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            dialog.show();
                        }else{
                            Toast.makeText(ForgetPasswordActivity.this, "该用户名未进行过注册！", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(ForgetPasswordActivity.this, "输入的用户名不一致！", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ForgetPasswordActivity.this, "输入框不能为空！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_loginactivity_back:
                Intent intent_2 = new Intent(this, RegisterActivity.class);
                startActivity(intent_2);
                finish();
                break;
            case R.id.tv_loginactivity_login:
                Intent intent_3 = new Intent(this, RegisterActivity.class);
                startActivity(intent_3);
                finish();
                break;
        }
    }
}

