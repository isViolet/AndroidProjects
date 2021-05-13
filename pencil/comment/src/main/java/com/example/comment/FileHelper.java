package com.example.comment;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileHelper {

    private Context mContext;

    public FileHelper() {
    }

    public FileHelper(Context mContext) {
        super();
        this.mContext = mContext;
    }

    /*
     * 这里定义的是一个文件保存的方法，写入到文件中，所以是输出流
     * */
    public static void save(Context context, String pp) throws Exception {
        FileOutputStream output = context.openFileOutput("OPO.txt", Context.MODE_APPEND);
        String pp1 = pp + "\n";
        output.write(pp1.getBytes());  //将String字符串以字节流的形式写入到输出流中
        output.close();         //关闭输出流
    }


    /*
     * 这里定义的是文件读取的方法
     * */
    public static void read(Context context) throws IOException {

        //打开文件输入流
        FileInputStream input = context.openFileInput("OPO.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        String content = "";
        //读取文件内容:
        while ((content = bufferedReader.readLine()) != null) {
            essLearn_Content.a.add(content);
        }
        //关闭输入流
        input.close();
    }

}