package com.example.tiaozhuan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    //首先我们必须要知道要跳转的app的包名，每一个APP的包名都是独立的，纵使是马甲包和主包的包名也是不一样的。
    //我们将要跳转的包名填在以下位置。
    public static final String APP_PACKAGE_NAME = "tv.danmaku.bili";
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //在布局中写一个button按钮，在这里初始化。
        bt = (Button) findViewById(R.id.tz);
        //为按钮设置监听
        bt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //监听被触发是启动跳转的方法。
        launchapp(this);
    }
    //跳转页面的方法
    private void launchapp(Context context) {
        //判断当前手机是否有要跳入的app
        if (isAppInstalled(context,APP_PACKAGE_NAME)){
            //如果有根据包名跳转
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(APP_PACKAGE_NAME));
        }else{
            //如果没有，走进入系统商店找到这款APP，提示你去下载这款APP的程序
            goToMarket(context, APP_PACKAGE_NAME);
        }
    }
    //这里是进入应用商店，下载指定APP的方法。
    private void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (Exception e) {
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

