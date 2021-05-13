package com.example.apple;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

    private EditText user_reg_name;
    private EditText user_reg_pwd1;
    private EditText user_reg_pwd2;
    private TextView tv_warn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_interface);
        initControl();
    }

    private void initControl(){

        user_reg_name = findViewById(R.id.et_user_reg1_name);
        user_reg_pwd1 = findViewById(R.id.et_user_reg2_pwd);
        user_reg_pwd2 = findViewById(R.id.et_user_reg3_pwd);
        tv_warn = findViewById(R.id.tv_warn);

    }

    public void register(View view) {

        MyOpenHelper myOpenHelper = new MyOpenHelper(getApplicationContext());
        String user_name = user_reg_name.getText().toString().trim();
        String user_pwd1 = user_reg_pwd1.getText().toString().trim();
        String user_pwd2 = user_reg_pwd2.getText().toString().trim();

        if (user_name.equals("") || user_pwd1.equals("") || user_pwd2.equals("")){
            tv_warn.setText("用户名和密码不能为空！");
        }else if (!(user_pwd1.equals(user_pwd2))){
            tv_warn.setText("两次输入的密码不相同，请重新输入！");
        }else{

            boolean isExist = isExistName(myOpenHelper, user_name);
            if (isExist){
                tv_warn.setText("该账户已存在，请更改账户名！");
            }else {
                SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("user_name",user_name);
                values.put("user_pwd",user_pwd1);
                long insert = sqLiteDatabase.insert("Account_info",null,values);

                sqLiteDatabase.close();

                if (insert>0){
                    Toast.makeText(this, "注册成功：" + user_name + "！", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(this, "注册失败：291", Toast.LENGTH_SHORT).show();
                    tv_warn.setText("");
                }
            }
        }
    }

    private boolean isExistName(MyOpenHelper myOpenHelper, String reg_name){

        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        boolean isExist = false;
        try{
            sqLiteDatabase = myOpenHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("Account_info",null,"user_name=?",new String[]{reg_name},null,null,null);
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

        /**
         * 当数据库第一次创建的时候调用
         * 适合做表结构初始化 就是写sql语句
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            // id一般以_id
            db.execSQL("create table Account_info(_id integer primary key autoincrement,user_name varchar(20),user_pwd varchar(20))");
        }

        /**
         * 适合做升级数据库时
         * 这个适合做表结构更新
         * @param db
         * @param oldVersion
         * @param newVersion
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL("alter table info add phone varchar(20)");
        }
    }

    public void backtrack(View view) {
        finish();
    }

}
