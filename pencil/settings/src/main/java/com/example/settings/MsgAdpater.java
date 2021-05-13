package com.example.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MsgAdpater extends RecyclerView.Adapter<MsgAdpater.ViewHolder>{
    private List<Aa> item_List;
    private Context context;
    int count = 0;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView text_1;
        LinearLayout linearLayout;
        public ViewHolder(View view)//传入view参数，获取布局中的控件
        {
            super(view);
            imageView = view.findViewById(R.id.iv);
            text_1 = (TextView)view.findViewById(R.id.tv_1);
            linearLayout = view.findViewById(R.id.ll);
        }
    }
    public MsgAdpater(List<Aa> list, Context context)
    {
        item_List =list;
        this.context = context;
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        if (count < 3){
            if (count == 0){
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item_top,parent,false);
                count ++;
                return new ViewHolder(view);
            }
            else if (count < 2){
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item_middle,parent,false);
                count ++;
                return new ViewHolder(view);
            }
            else {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item_bottom,parent,false);
                count ++;
                return new ViewHolder(view);
            }
        }
        else if (count < 7){
            if (count == 3){
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item_top,parent,false);
                count ++;
                return new ViewHolder(view);
            }
            else if (count < 6){
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item_middle,parent,false);
                count ++;
                return new ViewHolder(view);
            }
            else {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item_bottom,parent,false);
                count ++;
                return new ViewHolder(view);
            }
        }
        else{
            if (count == 7){
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item_top,parent,false);
                count ++;
                return new ViewHolder(view);
            }
            else{
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item_bottom,parent,false);
                count ++;
                return new ViewHolder(view);
            }
        }

    }//创建ViewHolder实例，加载msg_item布局，


    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        Aa map = item_List.get(position);
        holder.imageView.setImageResource(map.getInt());
        holder.text_1.setText(map.getStr());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "别点了" + position+1, Toast.LENGTH_SHORT).show();
            }
        });

    }//对RecyclerView的子项的数据进行赋值，会在每个子项被滚动到屏幕时执行。


    public int getItemCount()
    {
        return item_List.size();//告诉RecyclerView一共有多少子项。直接返回数据源长度。
    }
}
