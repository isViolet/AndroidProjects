package com.example.physical_experiment3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private EditText et5;
    private TextView average_result;
    private TextView sy_zg_result;
    private EditText et6;
    private EditText et7;
    private EditText et8;
    private TextView jy_zg_result;
    private EditText et_qiu1;
    private EditText et_qiu2;
    private TextView qiu_zg_result;
    private EditText jz1;
    private EditText jz2;
    private EditText jz3;
    private TextView jz_zg_ll_result;
    private EditText ll1;
    private EditText ll2;
    private EditText ll3;
    private TextView ll_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        et1 = findViewById(R.id.value1);
        et2 = findViewById(R.id.value2);
        et3 = findViewById(R.id.value3);
        average_result = findViewById(R.id.result);

        et4 = findViewById(R.id.sy1);
        et5 = findViewById(R.id.sy2);
        sy_zg_result = findViewById(R.id.result1);

        et6 = findViewById(R.id.jy1);
        et7 = findViewById(R.id.jy2);
        et8 = findViewById(R.id.jy3);
        jy_zg_result = findViewById(R.id.result2);

        et_qiu1 = findViewById(R.id.qiu1);
        et_qiu2 = findViewById(R.id.qiu2);
        qiu_zg_result = findViewById(R.id.result3);

        jz1 = findViewById(R.id.ll_jz1);
        jz2 = findViewById(R.id.ll_jz2);
        jz3 = findViewById(R.id.ll_jz3);
        jz_zg_ll_result = findViewById(R.id.result_ll1);

        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);
        ll3 = findViewById(R.id.ll3);
        ll_result = findViewById(R.id.result_ll2);
    }

    public void average(View view) {
        float value1 = Float.parseFloat(et1.getText().toString().trim());
        float value2 = Float.parseFloat(et2.getText().toString().trim());
        float value3 = Float.parseFloat(et3.getText().toString().trim());
        float  result = (value1 + value2 + value3)/3;
        average_result.setText("" + result);
    }

    public void sy_zg(View view) {

        float value1 = Float.parseFloat(et4.getText().toString().trim());
        float value2 = Float.parseFloat(et5.getText().toString().trim());
        float  result = (value2 * value1 * value1)/8;
        sy_zg_result.setText("" + result);
    }

    public void jy_zg(View view) {

        float value1 = Float.parseFloat(et6.getText().toString().trim());
        float value2 = Float.parseFloat(et7.getText().toString().trim());
        float value3 = Float.parseFloat(et8.getText().toString().trim());
        float  result = (value3 * (value1 * value1 + value2 * value2))/8;
        jy_zg_result.setText("" + result);
    }

    public void qiu_zg(View view) {
        float value1 = Float.parseFloat(et_qiu1.getText().toString().trim());
        float value2 = Float.parseFloat(et_qiu2.getText().toString().trim());
        float  result = (value2 * value1 * value1)/10;
        qiu_zg_result.setText("" + result);
    }

    public void xg_zg(View view) {
        float value1 = Float.parseFloat(et_qiu1.getText().toString().trim());
        float value2 = Float.parseFloat(et_qiu2.getText().toString().trim());
        float  result = (value2 * value1 * value1)/12;
        qiu_zg_result.setText("" + result);
    }

    public void ll_jz_zg(View view) {
        double value1 = Double.parseDouble(jz1.getText().toString().trim());
        double value2 = Double.parseDouble(jz2.getText().toString().trim());
        double value3 = Double.parseDouble(jz3.getText().toString().trim());
        double  result = ((value2 * value2)/(value3 * value3 - value2 * value2))*value1;
        jz_zg_ll_result.setText("" + result);
    }

    public void ll1_jz_zg(View view) {
        double value1 = Double.parseDouble(ll1.getText().toString().trim());
        double value2 = Double.parseDouble(ll2.getText().toString().trim());
        double value3 = Double.parseDouble(ll3.getText().toString().trim());
        double  result = ((value1 * value2 * value2)/(4 * Math.PI * Math.PI)) - value3;
        ll_result.setText("" + result);
    }
}
