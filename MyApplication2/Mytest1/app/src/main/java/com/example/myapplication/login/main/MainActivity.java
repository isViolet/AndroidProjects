package com.example.myapplication.login.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.login.enter.EnterActivity;
import com.example.myapplication.login.waterbutton.MyButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        //固定位置，在setContentView()之下,否则无论写的什么逻辑  都不会在Activity中起作用
        ButterKnife.bind(this);
        //if语句去标题
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        /*
        final Button button = (Button)findViewById(R.id.bt_main_logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_main_logout:
                        button.setSelected(true);
                        break;
                    default:
                        break;
                }
                Intent intent = new Intent(MainActivity.this, EnterActivity.class);
                startActivity(intent);
            }
        });
        */
    }

    @BindView(R.id.quit)
    MyButton button;
    @OnClick({
            R.id.quit,
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.quit:
                Intent intent = new Intent(this, EnterActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

}
