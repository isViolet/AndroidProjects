package com.example.mutidownload;

import java.net.HttpURLConnection;
import java.net.URL;

public class MutiDownload {

    //定义下载的路径
    static String path = "http://tb-video.bdstatic.com/tieba-smallvideo-transcode-cae/7036484_ced0f4aba989ddf6dcddadf05562ad3b_0_cae.mp4";
    public static void main(String[] args){
        //获取服务器文件大小 要计算每个线程的开始位置和结束位置
        new Thread(){public void run(){
            try {
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
                    int length = httpURLConnection.getContentLength();
                    System.out.println(length);

                    //创建一个大小和服务器大小一样的空间

                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.toString()); //输出文件大小
            }
        }}.start();
    }
}

