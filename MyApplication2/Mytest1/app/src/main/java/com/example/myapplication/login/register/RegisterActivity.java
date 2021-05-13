package com.example.myapplication.login.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.login.enter.EnterActivity;
import com.example.myapplication.login.forget.ForgetPasswordActivity;
import com.example.myapplication.login.forget.ResetPasswordsActivity;
import com.example.myapplication.login.login.DBOpenHelper;
import com.example.myapplication.login.login.User;
import com.example.myapplication.MainActivity;
import com.example.myapplication.login.message.MessageLoginActivity;
import com.example.myapplication.login.waterbutton.MyButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    //声明自己写的 DBOpenHelper 对象DBOpenHelper(extends SQLiteOpenHelper) 主要用来创建数据表然后再进行数据表的增、删、改、查操作
    private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //保存实例状态
        super.onCreate(savedInstanceState);
        // 设置视图内容的配置文件,上面这行代码真正实现了把视图层 View 也就是 layout 的内容放到 Activity 中进行显示 ,
        // 将开源库ButterKnife应用到此上下文，这个开源库的规定用法
        setContentView(R.layout.register_layout);
        //固定位置，在setContentView()之下,否则无论写的什么逻辑  都不会在Activity中起作用
        ButterKnife.bind(this);
        //实例化 DBOpenHelper，待会进行登录验证的时候要用来进行数据查询
        mDBOpenHelper = new DBOpenHelper(this);
        //if语句去标题
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        /*
        String[] ReadInfo = Util.ReadInfo();
        //判断ReadInfo是否为空，如不为空，显示账号密码
        if (ReadInfo != null) {
            mEtregisteractivityUsername.setText(ReadInfo[0]);//读取分割符split“##”前面的username
            mEtregisteractivityPassword.setText(ReadInfo[1]);//读取分割符split“##”后面的password
        }
         */
    }
    /*
     * 这里使用注解的方式注入View,可以不再写findViewById了
     * 解决了findViewById代码过长的问题，
     * 还解决了setOnClickListener(new View.OnClickListener() {}代码过长的问题
     * 比如说，以前声明并实例化一个 Button 是这样的代码：
     * Button button_1 = (Button)findViewById(R.id.button1);
     * 用注解的方式是这样的代码（两行代码）：
     * @BindView(R.id.button1)
     * Button button_1;
     * 直接按着句子读就能理解了
     * 绑定按钮（资源id号是button1）
     * 实例化Button 为 button_1
     * 开源库的文章：https://github.com/JakeWharton/butterknife
     */


    @BindView(R.id.bt_registeractivity_register)
    MyButton mBtregisteractivityLogin;
    @BindView(R.id.register_back_1)
    ImageView register_back_1;
    @BindView(R.id.register_back_2)
    TextView register_back_2;
    @BindView(R.id.remember_password)
    CheckBox remember_password;
    @BindView(R.id.rl_registeractivity_top)
    RelativeLayout mRlregisteractivityTop;
    @BindView(R.id.et_registeractivity_username)
    EditText mEtregisteractivityUsername;
    @BindView(R.id.et_registeractivity_password)
    EditText mEtregisteractivityPassword;
    @BindView(R.id.ll_registeractivity_two)
    LinearLayout mLlregisteractivityTwo;
    @BindView(R.id.tv_registeractivity_forget)
    TextView mTvregisteractivityForget;
    @BindView(R.id.tv_registeractivity_message)
    TextView mTvregisteractivityMessage;
//    @BindView(R.id.tv_registeractivity_else)
//    TextView mBtregisteractivityElse;

    @OnClick({
            R.id.register_back_1,
            R.id.register_back_2,
            R.id.tv_registeractivity_forget,
            R.id.tv_registeractivity_message,
            R.id.bt_registeractivity_register,
            R.id.et_registeractivity_username,
            R.id.remember_password,
            //R.id.tv_registeractivity_else
    })

    public void onClick(View view) {
        String name = mEtregisteractivityUsername.getText().toString().trim();
        String password = mEtregisteractivityPassword.getText().toString().trim();
        //读取保存的信息，ReadInfo()类方法
        switch (view.getId()) {
            //TODO 返回箭头功能
            case R.id.register_back_1:      //返回
                startActivity( new Intent(this, EnterActivity.class));
                finish();//销毁此Activity
                break;
            //TODO 返回back功能
            case R.id.register_back_2:      //返回
                startActivity(new Intent(this, EnterActivity.class));
                finish();//销毁此Activity
                break;
            //TODO 返回记住密码功能
            case R.id.remember_password:
                boolean checked = remember_password.isChecked();
                //判断是否需要保存信息
                if (checked) {
                    //判断是否有成功保存信息
                    boolean saveinfo = Util.SaveInfo(name,password);
                    if (saveinfo) {
                        Toast.makeText(RegisterActivity.this, "用户信息保存成功", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(RegisterActivity.this, "用户信息保存失败", Toast.LENGTH_LONG).show();
                    }
                    Log.d("MainActivity", "保存用户名："+name+"密码："+password);//打印
                }
                break;
            case R.id.tv_registeractivity_forget:   //忘记密码
            //TODO 忘记密码操作界面跳转
                startActivity(new Intent(this, ResetPasswordsActivity.class));
                finish();//销毁此Activity
                break;
            case R.id.tv_registeractivity_message:    //邮箱验证登录
            // TODO 邮箱验证登录界面跳转
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                finish();//销毁此Activity
                break;
            case R.id.bt_registeractivity_register:
                //String name = mEtregisteractivityUsername.getText().toString().trim();
                //String password = mEtregisteractivityPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mDBOpenHelper.getAllData();
                    boolean match = false;
                    for(int i=0;i<data.size();i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())){
                            match = true;
                            break;
                        }else{
                            match = false;
                        }
                    }
                    if (match) {
                        String signin = mEtregisteractivityUsername.getText().toString()+"用户你好！登录成功！";
                        Toast.makeText(RegisterActivity.this,signin,Toast.LENGTH_SHORT).show();
                        Intent intent_2 = new Intent(this, MainActivity.class);
                        startActivity(intent_2);
                        finish();//销毁此Activity
                    }else {
                        Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
                break;
            //case R.id.tv_registeractivity_else:
            //TODO 第三方登录
            //    break;
        }
    }
}
