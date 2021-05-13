package com.example.weblogin;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import static android.content.ContentValues.TAG;

public class StreamTools {
    //把一个inputStream 转换成一个String
    public static String readStream(InputStream in) throws Exception{
        int len = -1;
        //定义一个内存输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];//1kb
        while((len=in.read(bytes))!=-1){
            byteArrayOutputStream.write(bytes,0,len);

        }
        in.close();
        String connent = new String(byteArrayOutputStream.toByteArray());
        return connent;
    }
    public static String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }
}

