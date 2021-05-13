package com.example.myapplication.login.forget;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.login.login.DBOpenHelper;
import com.example.myapplication.login.login.User;
import com.example.myapplication.login.register.RegisterActivity;
import com.example.myapplication.login.waterbutton.MyButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordsActivity extends AppCompatActivity/*BaseActivity */ implements View.OnClickListener{

    private DBOpenHelper mDBOpenHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState/*Bundle savedInstanceState*/) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpassword_layout);
        //固定位置，在setContentView()之下,否则无论写的什么逻辑  都不会在Activity中起作用
        ButterKnife.bind(this);
        //if语句去标题
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        mDBOpenHelper = new DBOpenHelper(this);
    }
    @BindView(R.id.btn_update_password)
    MyButton confirm;
    @BindView(R.id.iv_loginactivity_back)
    ImageView back_1;
    @BindView(R.id.tv_loginactivity_login)
    TextView back_2;

    @BindView(R.id.et_old)
    EditText et_old;
    @BindView(R.id.et_new)
    EditText et_new;
    @BindView(R.id.et_new_2)
    EditText et_new_2;

    @OnClick({
            R.id.btn_update_password,
            R.id.iv_loginactivity_back,
            R.id.tv_loginactivity_login,
            R.id.et_old,
            R.id.et_new,
            R.id.et_new_2,
    })

    public void onClick(View view){
        String old=et_old.getText().toString().trim();
        String new1=et_new.getText().toString().trim();
        String new2=et_new_2.getText().toString().trim();

        switch (view.getId()){
            case R.id.btn_update_password:
                //判断输入不为空
                if(!TextUtils.isEmpty(old)&&!TextUtils.isEmpty(new1)&&!TextUtils.isEmpty(new2)){
                    //判断两次密码一致
                    if(new1.equals(new2)){
                        //判断密码为原密码
                        ArrayList<User> data = mDBOpenHelper.getAllData();
                        boolean match = false;
                        for(int i=0;i<data.size();i++) {
                            User user = data.get(i);
                            if ( old.equals(user.getPassword())){
                                match = true;
                                break;
                            }else{
                                match = false;
                            }
                        }
                        if (match){
                            mDBOpenHelper.updata(new2);
                            Toast.makeText(ResetPasswordsActivity.this, "密码重置成功！返回登录界面", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ResetPasswordsActivity.this, RegisterActivity.class));
                            finish();
                        }else{
                            Toast.makeText(ResetPasswordsActivity.this, "原密码输入错误！", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(ResetPasswordsActivity.this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ResetPasswordsActivity.this, "输入框不能为空！", Toast.LENGTH_SHORT).show();
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
