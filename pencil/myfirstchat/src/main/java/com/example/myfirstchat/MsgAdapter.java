package com.example.myfirstchat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Message> mMessageList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
        public ViewHolder(View view)//传入view参数，获取布局中的控件
        {
            super(view);
            leftLayout=(LinearLayout) view.findViewById(R.id.left_layout);
            rightLayout=(LinearLayout) view.findViewById(R.id.right_layout);
            leftMsg=(TextView)view.findViewById(R.id.left_msg);
            rightMsg=(TextView)view.findViewById(R.id.right_msg);
        }
    }
    public MsgAdapter(List<Message> list)
    {
        mMessageList =list;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        return new ViewHolder(view);
    }//创建ViewHolder实例，加载msg_item布局，
    public void onBindViewHolder(ViewHolder holder,int position)
    {
        Message message = mMessageList.get(position);//获取当前的Msg实例
        if(message.getType()== Message.TYPE_RECEIVED)//对消息的类型进行判断，显示对应的布局，隐藏另一个布局。
        {
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(message.getContent());
        }else if(message.getType()== Message.TYPE_SENT)
        {
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(message.getContent());
        }
    }//对RecyclerView的子项的数据进行赋值，会在每个子项被滚动到屏幕时执行。
    public int getItemCount()
    {
        return mMessageList.size();//告诉RecyclerView一共有多少子项。直接返回数据源长度。
    }
}

