package com.example.myapplication.login.enter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.login.login.LoginActivity;
import com.example.myapplication.login.register.RegisterActivity;
import com.example.myapplication.login.waterbutton.MyButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EnterActivity extends AppCompatActivity {
    //private MyButton myButton;
    //private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_layout);
        //固定位置，在setContentView()之下,否则无论写的什么逻辑  都不会在Activity中起作用
        ButterKnife.bind(this);
        //initView();
        //if语句去标题
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        /*
        final Button myButton = (Button)findViewById(R.id.enter_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.enter_button:
                        Intent intent_1 = new Intent(EnterActivity.this, RegisterActivity.class);
                        startActivity(intent_1);
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });
        final TextView textView_1 = (TextView)findViewById(R.id.newuser_1);
        textView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.newuser_1:
                        Intent intent_2 = new Intent(EnterActivity.this, LoginActivity.class);
                        startActivity(intent_2);
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });
        final TextView textView_2 = (TextView)findViewById(R.id.forget_1);
        textView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.forget_1:
                        Intent intent_3 = new Intent(EnterActivity.this, ResetPasswordsActivity.class);
                        startActivity(intent_3);
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });

         */
    }
    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
    */
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

 */
    @BindView(R.id.enter_button)
    MyButton enter_button;
    @BindView(R.id.newuser_1)
    TextView newuser_1;
    //    @BindView(R.id.forget_1)
//    TextView forget_1;
    @OnClick({
            R.id.enter_button,
            R.id.newuser_1,
//            R.id.forget_1,
    })
    public void onClick(View view){
        switch (view.getId()){
            case R.id.enter_button:
                Intent intent_1 = new Intent(EnterActivity.this, RegisterActivity.class);
                startActivity(intent_1);
                finish();
                break;

            case R.id.newuser_1:
                Intent intent_2 = new Intent(EnterActivity.this, LoginActivity.class);
                startActivity(intent_2);
                finish();
                break;

//            case R.id.forget_1:
//                Intent intent_3 = new Intent(EnterActivity.this, ResetPasswordsActivity.class);
//                startActivity(intent_3);
//                finish();
//                break;
        }
    }
}
