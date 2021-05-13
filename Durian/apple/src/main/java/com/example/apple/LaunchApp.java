package com.example.apple;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class LaunchApp  {

    //首先我们必须要知道要跳转的app的包名，每一个APP的包名都是独立的，纵使是马甲包和主包的包名也是不一样的。
    //我们将要跳转的包名填在以下位置。
    //public static final String APP_PACKAGE_NAME = "tv.danmaku.bili"; // 跳转b站

    private String sentence = null;
    private Context context = null;

    public LaunchApp(){

    }

    LaunchApp(String sentence, Context context){
        this.sentence = sentence;
        this.context = context;
    }

    int senToAppPackage(){

        boolean isGet = false;
        int kind = 3;
        if(sentence.equals("我要上b站")){
            // 跳转b站
            String app_package1 = "tv.danmaku.bili";
            sentence = app_package1;
            isGet = true;
            kind = 2;
        }else if (sentence.equals("再见")){
            kind = 1;
        } else if (sentence.equals("我要锻炼了")||sentence.equals("1")){
            kind = 4;
        }
        else if (sentence.startsWith("我要上")){
            Toast.makeText(context, "抱歉，该功能未上线", Toast.LENGTH_SHORT).show();
            kind = 2;
        }

        if (isGet){
            launchapp(context);
        }
        return kind;
    }

    //跳转页面的方法
    private void launchapp(Context context) {
        //判断当前手机是否有要跳入的app
        if (isAppInstalled(context,sentence)){
            //如果有根据包名跳转
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(sentence));
        }else{
            //如果没有，走进入系统商店找到这款APP，提示你去下载这款APP的程序
            goToMarket(context, sentence);
        }
    }
    //这里是进入应用商店，下载指定APP的方法。
    private void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    //这里是判断APP中是否有相应APP的方法
    private boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName,0);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

