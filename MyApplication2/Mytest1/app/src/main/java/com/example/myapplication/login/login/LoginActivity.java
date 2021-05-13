package com.example.myapplication.login.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.MainActivity;
import com.example.myapplication.login.register.RegisterActivity;
import com.example.myapplication.login.waterbutton.MyButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private String realCode;
    private DBOpenHelper mDBOpenHelper;
    int i=0;
    int j=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        //固定位置，在setContentView()之下,否则无论写的什么逻辑  都不会在Activity中起作用
        ButterKnife.bind(this);
        //if语句去标题
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        mDBOpenHelper = new DBOpenHelper(this);
        //将验证码用图片的形式显示出来
        mIvloginactivityShowcode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
    }
    @BindView(R.id.touxiang)
    ImageView touxiang;
    @BindView(R.id.btnMan)
    RadioButton btnMan;
    @BindView(R.id.btnWoman)
    RadioButton btnWoman;
    @BindView(R.id.bt_loginactivity_login)
    MyButton myButton;
    @BindView(R.id.rl_loginactivity_top)
    RelativeLayout mRlRegisteractivityTop;
    @BindView(R.id.iv_loginactivity_back_1)
    ImageView mIvloginactivityBack_1;
    @BindView(R.id.tv_loginactivity_login)
    TextView tv_loginactivity_login;
    @BindView(R.id.ll_loginactivity_body)
    LinearLayout mLlloginactivityBody;
    @BindView(R.id.et_loginactivity_username)
    EditText mEtloginactivityUsername;
    @BindView(R.id.et_loginactivity_password1)
    EditText mEtloginactivityPassword1;
    @BindView(R.id.et_loginactivity_password2)
    EditText mEtloginactivityPassword2;
    @BindView(R.id.et_loginactivity_phoneCodes)
    EditText mEtloginactivityPhonecodes;
    @BindView(R.id.iv_loginactivity_showCode)
    ImageView mIvloginactivityShowcode;
    @BindView(R.id.rl_loginactivity_bottom)
    RelativeLayout mRlloginactivityBottom;

    /**
     * 注册页面能点击的就三个地方
     * top处返回箭头、刷新验证码图片、注册按钮
     */
    @OnClick({
            R.id.iv_loginactivity_back_1,
            R.id.tv_loginactivity_login,
            R.id.iv_loginactivity_showCode,
            R.id.bt_loginactivity_login,
            R.id.touxiang,
            R.id.btnMan,
            R.id.btnWoman,
    })

    public void onClick(View view) {
        final int jpg1[] = {R.drawable.defaulthead,R.drawable.boy1,R.drawable.boy2,R.drawable.boy3,R.drawable.boy4,R.drawable.boy5,R.drawable.boy6,R.drawable.boy7,R.drawable.boy8};
        final int jpg2[] = {R.drawable.defaulthead,R.drawable.girl1,R.drawable.girl2,R.drawable.girl3,R.drawable.girl4,R.drawable.girl5,R.drawable.girl6,R.drawable.girl7};
        boolean checkedman = btnMan.isChecked();
        boolean checkedwoman = btnWoman.isChecked();
        switch (view.getId()) {
            case R.id.btnMan:
                if (checkedman) {
                    Toast.makeText(LoginActivity.this, "选择男生", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnWoman:
                if (checkedwoman) {
                    Toast.makeText(LoginActivity.this, "选择女生", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.touxiang:
                //判断是否点击RadioButton
                if (checkedman) {
                    //Toast.makeText(LoginActivity.this, "选择男生", Toast.LENGTH_LONG).show();
                    ++i;
                    touxiang.setImageResource(jpg1[i%jpg1.length]);
                }
                if (checkedwoman) {
                    //Toast.makeText(LoginActivity.this, "选择女生", Toast.LENGTH_LONG).show();
                    ++j;
                    touxiang.setImageResource(jpg2[j%jpg2.length]);
                }
                break;
            case R.id.iv_loginactivity_back_1: //箭头返回登录页面
                Intent intent_1 = new Intent(this, RegisterActivity.class);
                startActivity(intent_1);
                finish();
                break;
            case R.id.tv_loginactivity_login: //back返回登录页面
                Intent intent_2 = new Intent(this, RegisterActivity.class);
                startActivity(intent_2);
                finish();
                break;
            case R.id.iv_loginactivity_showCode:    //改变随机验证码的生成
                mIvloginactivityShowcode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                break;
            case R.id.bt_loginactivity_login:    //注册按钮
                //获取用户输入的用户名、密码、验证码
                String username = mEtloginactivityUsername.getText().toString().trim();
                String password = mEtloginactivityPassword2.getText().toString().trim();
                String phoneCode = mEtloginactivityPhonecodes.getText().toString().toLowerCase();
                //注册验证
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phoneCode) ) {
                    if (phoneCode.equals(realCode)) {
                        //将用户名和密码加入到数据库中
                        mDBOpenHelper.add(username, password);
                        Intent intent2 = new Intent(this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                        Toast.makeText(this,  "验证通过，注册成功", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this, "验证码错误,注册失败", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "未完善信息，注册失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
