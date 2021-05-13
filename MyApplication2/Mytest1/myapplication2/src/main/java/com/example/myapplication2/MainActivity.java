package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    TextView textView1;
    private int num = 9999999;
    int a = 0;
    CountDownTimer countDownTimer = new CountDownTimer(25000,1000) {
        @Override
        public void onTick(long l) {
            if (a < num ){
                textView.setText("倒计时：" + a);
                ++a;
            }
        }

        @Override
        public void onFinish() {
            countDownTimer.cancel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.count);
        textView1 = findViewById(R.id.ter);
        num = (int)(16 * Math.random()) + 6;
        textView1.setText("预计用时：" + num);
        countDownTimer.start();
    }
}
