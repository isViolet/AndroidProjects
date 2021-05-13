package com.example.apple;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cxy on 2016/12/30.
 */

public class StepCounterActivity extends Activity implements SensorEventListener {

    private TextView tv_time;
    private TextView tv_steps;
    private Button bt_control;
    private int lines = 0;
    private final String TAG = "tiny";
    private float startSteps = 0;
    private float mSteps = 0;
    private static final int msgKey1 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_counter);
        initView();
        initButton();
        initSensor();
        new TimeThread().start();
    }

    private void initView(){

        tv_time = (TextView) findViewById(R.id.tv_nowTime);
        tv_steps = findViewById(R.id.tv_stepsCount);
        bt_control = findViewById(R.id.bt_stepsControl);

    }

    private void initButton(){

        String last_Line = null;
        try{
            //打开文件输入流
            FileInputStream input = getApplicationContext().openFileInput("MotionRecord.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
            String read_Content = null;
            while ((read_Content = bufferedReader.readLine()) != null) {
                ++lines;
                last_Line = read_Content;
            }
            lines %= 2;
            //关闭输入流
            input.close();
        }catch (Exception e){
            e.getStackTrace();
        }
        if (lines == 0){
            bt_control.setText("开始计步");
            tv_steps.setText("你还没开始计步呢");
        }else {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            assert last_Line != null;
            Matcher m = p.matcher(last_Line);
            last_Line = m.replaceAll("");
            String[] var = last_Line.split(":");
            startSteps = Integer.parseInt(var[1]);
            bt_control.setText("停止计步");
        }
    }

    private void initSensor(){


        // 获取SensorManager管理器实例
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // 获取计步器sensor
        assert mSensorManager != null;
        Sensor stepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(stepCounter != null){
            // 如果sensor找到，则注册监听器
            mSensorManager.registerListener(this, stepCounter,1000000);
        }
        else{
            Log.e(TAG,"no step counter sensor found");
        }

    }

    public void viewSteps(View view) {

        // 启动一个新的界面，listview展示数据
        String isStart = bt_control.getText().toString().trim();
        if (isStart.equals("停止计步")){
            Toast.makeText(this, "正在计步，不能查看数据", Toast.LENGTH_SHORT).show();
        }else if(isStart.equals("开始计步")){
            startActivity(new Intent(StepCounterActivity.this,ShowStepsRecords.class));
        }

    }

    public void controlSteps(View view) {

        FileOutputStream output = null;
        try{

            String time = tv_time.getText().toString().trim();
            String record = time + ":" + (int)mSteps + "\n";
            output = getApplicationContext().openFileOutput("MotionRecord.txt", Context.MODE_APPEND);
            output.write(record.getBytes());  //将String字符串以字节流的形式写入到输出流中
            ++lines;

        }catch (Exception e){
            e.getStackTrace();
        }finally {
            try{
                if (output != null){
                    output.close();  //关闭输出流
                }
            }catch (Exception e){
                e.getStackTrace();
            }
        }
        lines %= 2;
        if (lines == 1){
            startSteps = mSteps;
            bt_control.setText("停止计步");
            tv_steps.setText("少女祈祷中...\n（如果偷懒的话，祈祷会失败哦）");
        }else {
            bt_control.setText("开始计步");
            tv_steps.setText("你还没开始计步呢");
        }

    }

    // 实现SensorEventListener回调接口，在sensor改变时，会回调该接口
    // 并将结果通过event回传给app处理
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        mSteps = sensorEvent.values[0];
        Log.i(TAG,"Detected step changes:"+sensorEvent.values[0]);
        if (lines == 1){
            if (((int)mSteps - (int) startSteps) >= 0){
                tv_steps.setText("步数："+String.valueOf((int)mSteps - (int) startSteps)+"步");
            }else {
                tv_steps.setText("少女祈祷中...\n（如果偷懒的话，祈祷会失败哦）");
            }

        }else{
            tv_steps.setText("你还没开始计步呢");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public class TimeThread extends  Thread{
        @Override
        public void run() {
            super.run();
            do{
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case msgKey1:
                    long time = System.currentTimeMillis();
                    Date date = new Date(time);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 EEE");
                    tv_time.setText(format.format(date));
                    break;
                default:
                    break;
            }
        }
    };
}

