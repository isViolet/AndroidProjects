package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.dbhelper.DataBaseHelper;
import com.example.myapplication.dbhelper.DbQuery;

public class WorksWriteMainActivity extends Activity {

    private DataBaseHelper dataBaseHelper;
    private EditText writeTitle;
    private EditText writeLabel;
    private EditText writeContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_page);
        initAll();
    }

    private void initAll(){

        //属性
        dataBaseHelper = new DataBaseHelper(this,"BookManager");
        writeTitle = findViewById(R.id.write_title);
        writeLabel = findViewById(R.id.write_label);
        writeContent = findViewById(R.id.write_content);

    }

    public void exit(View view) {
        finish();
    }

    public void save(View view) {
        String title = writeTitle.getText().toString().trim();
        String label = writeLabel.getText().toString().trim();
        String content = writeContent.getText().toString();
        DbQuery dbQuery = new DbQuery();
        if (title.equals("") || content.equals("")){
            Toast.makeText(this, "请填写完整！", Toast.LENGTH_SHORT).show();
            return;
        }else if (dbQuery.isExist(title, this,"worksTable")){
            Toast.makeText(this, "标题已经存在！", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",title);
        values.put("label",label);
        values.put("content",content);
        long insert = sqLiteDatabase.insert("worksTable",null,values);

        sqLiteDatabase.close();

        if (insert>0){
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
