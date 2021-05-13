package com.example.myapplication.login.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.login.register.RegisterActivity;
import com.example.myapplication.login.waterbutton.MyButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageLoginActivity extends AppCompatActivity/*BaseActivity */ implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messagelogin_layout);
        //固定位置，在setContentView()之下,否则无论写的什么逻辑  都不会在Activity中起作用
        ButterKnife.bind(this);
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

    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_new_email)
    EditText et_new_email;

    @OnClick({
            R.id.btn_update_password,
            R.id.iv_loginactivity_back,
            R.id.tv_loginactivity_login,
            R.id.et_email,
            R.id.et_new_email,
    })

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_update_password:
                /*
                String email=et_email.getText().toString().trim();//获取文本框用户输入的信息
                if(!TextUtils.isEmpty(email)){
                    //发送邮件重置密码
                    MyUSer.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(MessageLoginActivity.this, "请前往邮箱查看！", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(MessageLoginActivity.this, "密码重置失败！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(MessageLoginActivity.this, "输入框不能为空！", Toast.LENGTH_SHORT).show();
                }

                 */
                Intent intent_1 = new Intent(this, RegisterActivity.class);
                startActivity(intent_1);
                finish();
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

