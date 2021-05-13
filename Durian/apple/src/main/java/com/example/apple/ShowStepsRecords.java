package com.example.apple;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowStepsRecords extends Activity {

    private Record record;
    private List<Record> list;
    private ListView record_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_view);
        initView();
        initList();

    }

    private void initView() {

        record_list = findViewById(R.id.lv_record);
    }

    private void initList(){

        try{
            int lines = 0;
            int startSteps = 0;
            int endSteps = 0;
            list = new ArrayList<Record>();
            //打开文件输入流
            FileInputStream input = getApplicationContext().openFileInput("MotionRecord.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
            String read_Content = null;
            while ((read_Content = bufferedReader.readLine()) != null) {

                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(read_Content);
                read_Content = m.replaceAll("");
                if (lines == 0){
                    String[] var = read_Content.split(":");
                    record = new Record();
                    record.setStartDate(var[0]);
                    startSteps = Integer.parseInt(var[1]);
                }else {
                    String[] var = read_Content.split(":");
                    record.setEndDate(var[0]);
                    endSteps = Integer.parseInt(var[1]);
                    record.setSteps(endSteps - startSteps);
                    list.add(record);
                }
                ++lines;
                lines %= 2;
            }
            record_list.setAdapter(new MyAdpater());
            //关闭输入流
            input.close();
        }catch (Exception e){
            e.getStackTrace();
        }

    }

    public void goback(View view) {
        finish();
    }

    //定义listview的适配器
    private class MyAdpater extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view ;
            if (convertView == null){
                //创建新的对象
                view = View.inflate(getApplicationContext(),R.layout.record_view_item,null);
            }else{
                view = convertView;
            }

            TextView startTime = view.findViewById(R.id.tv_startTime);
            TextView endTime = view.findViewById(R.id.tv_endTime);
            TextView steps = view.findViewById(R.id.tv_steps);

            Record record = list.get(getCount() - position - 1);
            startTime.setText("步行开始时间：\n" + record.getStartDate());
            endTime.setText("步行结束时间：\n" + record.getEndDate());
            steps.setText("该时间段您步行数为：" + record.getSteps() + "步");
            return view;
        }
    }

    private class Record {

        private String startDate = null;
        private String endDate = null;
        private int steps = 0;

        private String getStartDate() {
            return startDate;
        }

        private void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        private String getEndDate() {
            return endDate;
        }

        private void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        private int getSteps() {
            return steps;
        }

        private void setSteps(int steps) {
            this.steps = steps;
        }
    }
}
