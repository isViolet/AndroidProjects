package com.example.myapplication.login.register;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Util {

    //类方法1：保存用户账号密码
    public static boolean SaveInfo(String name, String password) {

        // TODO Auto-generated method stub
        //保存方式
        String info = name + "##" +password;
        //保存信息位置并以txt文件方式
        File file = new File("data/data/com.example.login/info.txt");
        try {

            //将用户信息写入info.txt文件
            FileOutputStream fis = new FileOutputStream(file);
            fis.write(info.getBytes());
            return true;
        } catch (Exception e) {
            // TODO: 处理异常错误
            e.printStackTrace();
            return false;
        }
    }
    //类方法2：读取账号密码
    public static String[] ReadInfo() {
        // TODO Auto-generated method stub
        //获取保存文件
        File file = new File("data/data/com.example.login/info.txt");
        //读取数据
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));//切割函数
            String temp = reader.readLine();
            String[] result = temp.split("##");
            return result;

        } catch (Exception e) {
            // TODO: 处理异常错误
            e.printStackTrace();
            return null;
        }
    }
}
