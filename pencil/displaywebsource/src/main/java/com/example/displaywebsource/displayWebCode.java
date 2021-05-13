package com.example.displaywebsource;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class displayWebCode extends Activity {

    private EditText et_path;
    private TextView tv_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_path = (EditText) findViewById(R.id.et_path);
        tv_code = findViewById(R.id.tv_code);
    }

    public void click(View view) {
        new Thread(){public void run(){
            String path = et_path.getText().toString().trim();
            //创建url对象，指定我们要访问的网址
            URL url = null;
            try {
                url = new URL(path);
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

                    //把in转换成String
                    String connent = StreamTools.streamToString(in);

                    //把流里面的数据展示到textview上
                    tv_code.setText(connent);
                    System.out.println(connent);
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        }}.start();
    }
}
