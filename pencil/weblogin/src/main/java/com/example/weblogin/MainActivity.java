package com.example.weblogin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    private EditText user_name;
    private EditText user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_name = findViewById(R.id.user_name);
        user_password = findViewById(R.id.user_password);


    }

    public void click(View view) {
        System.out.println("aaa");
        new Thread(){public void run(){
            try {
                String name = user_name.getText().toString().trim();
                String pwd = user_password.getText().toString().trim();
                String path = "" +
                        "?uname="+name+"&upass="+pwd+"";
                URL url = new URL(path);
                //拿到HTTPURLconnection对象 用于发送或者接受数据
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                //设置发送get请求
                httpURLConnection.setRequestMethod("GET");//要求大写，默认get
                //设置请求时间
                httpURLConnection.setConnectTimeout(5000);
                //获取服务器的返回状态码
                int code = httpURLConnection.getResponseCode();
                //如果code=200，说明请求成功
                if (code == 200){
                    //获取服务器的数据是以流形式返回的，把流转换成字符串是十分常见的，可以抽成一个utils
                    InputStream in = httpURLConnection.getInputStream();
                    //把服务器返回的数据展示到吐司，但不能在子线程使用吐司
                    String content = StreamTools.streamToString(in);
                    showToast(content);
                    System.out.println(content);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.toString());
            }
        }}.start();
    }

    public void click2(View view) {
    }

    //封装一个toast方法
    public void showToast(final String content){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //该方法一定执行在主线程
                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

