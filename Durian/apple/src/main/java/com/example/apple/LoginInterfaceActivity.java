package com.example.apple;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginInterfaceActivity extends Activity {

    private EditText user_name;
    private EditText user_pwd;
    private TextView tv_login_warn;
    private CheckBox checkBox;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_interface);
        initViews();
        initControl();

    }

    private void initControl() {

        sharedPreferences = getSharedPreferences("rememberpassword", Context.MODE_PRIVATE);
        boolean isRemember = sharedPreferences.getBoolean("rememberpassword",false);
        if(isRemember){
            String name = sharedPreferences.getString("name","");
            String password = sharedPreferences.getString("password","");
            user_name.setText(name);
            user_pwd.setText(password);
            checkBox.setChecked(true);
        }

    }

    private void initViews(){

        user_name = findViewById(R.id.et_user_name);
        user_pwd = findViewById(R.id.et_user_pwd);
        tv_login_warn = findViewById(R.id.tv_login_warn);
        checkBox = findViewById(R.id.checkBox);

    }

    public void login(View view) {

        // 判断用户名密码 略过
        MyOpenHelper myOpenHelper = new MyOpenHelper(getApplicationContext());
        String user_login_name = user_name.getText().toString().trim();
        String user_login_pwd = user_pwd.getText().toString().trim();

        if (user_login_name.equals("") || user_login_pwd.equals("")){
            tv_login_warn.setText("用户名和密码不能为空！");
        }else {
            boolean isExist = isExistName(myOpenHelper, user_login_name, user_login_pwd);
            if (isExist){
                SharedPreferences.Editor editor= sharedPreferences.edit();
                if (checkBox.isChecked()){
                    editor.putBoolean("rememberpassword",true);
                    editor.putString("name",user_login_name);
                    editor.putString("password",user_login_pwd);
                }else {
                    editor.clear();
                }
                editor.commit();
                Intent login_intent = new Intent(LoginInterfaceActivity.this,MainActivity.class);
                startActivity(login_intent);
                finish();
                Toast.makeText(this, "欢迎您，" + user_login_name + "！", Toast.LENGTH_SHORT).show();
            }else {
                tv_login_warn.setText("账号不存在或账号密码有误！");
            }

        }
    }

    public void register(View view) {

        Intent register_intent = new Intent(LoginInterfaceActivity.this,RegisterActivity.class);
        startActivity(register_intent);

    }

    private boolean isExistName(MyOpenHelper myOpenHelper, String user_name, String user_pwd){

        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        boolean isExist = false;
        try{
            sqLiteDatabase = myOpenHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("Account_info",null,"user_name=? and user_pwd=?",new String[]{user_name,user_pwd},null,null,null);
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            if (cursor != null && cursor.getCount()>0){
                isExist = true;
            }
            assert cursor != null;
            cursor.close();
        }
        return isExist;
        //查询所有
//        Cursor cursor = sqLiteDatabase.query("Account_info",null,null,null,null,null,null);
    }

    private class MyOpenHelper extends SQLiteOpenHelper {
        MyOpenHelper(Context context) {
            super(context, "Apple.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // id一般以_id
            db.execSQL("create table Account_info(_id integer primary key autoincrement,user_name varchar(20),user_pwd varchar(20))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL("alter table info add phone varchar(20)");
        }
    }

}
