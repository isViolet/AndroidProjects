package com.example.myfirstchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<Message> messageList =new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();
        inputText=(EditText) findViewById(R.id.input_text);
        send=(Button) findViewById(R.id.send);
        msgRecyclerView=(RecyclerView)findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        //LayoutManager用来指定RecyclerView的布局方式，layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL)设置横向滚动，StaggeredLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL) 可以设置3列的瀑布排列
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(messageList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                String content=inputText.getText().toString();
                if(!"".equals(content))
                {
                    Message message =new Message(content, Message.TYPE_SENT);
                    messageList.add(message);
                    //adapter.notifyItemInserted(messageList.size()-1);
                    //msgRecyclerView.scrollToPosition(messageList.size()-1);
                    inputText.setText("");
                    if(content.equals("1")){
                        Message message1 =new Message(Constant.ck1, Message.TYPE_RECEIVED);
                        messageList.add(message1);
                    }
                    else if(content.equals("2")){
                        Message message1 =new Message(Constant.ts, Message.TYPE_RECEIVED);
                        messageList.add(message1);
                    }
                    else{
                        Message message1 =new Message(Constant.recall1, Message.TYPE_RECEIVED);
                        messageList.add(message1);
                    }
                    adapter.notifyItemInserted(messageList.size()-1);
                    msgRecyclerView.scrollToPosition(messageList.size()-1);
                }
            }
        });
    }
    public void initMsg()//初始化信息
    {
        Message message1 =new Message(Constant.recall1, Message.TYPE_RECEIVED);
        messageList.add(message1);
    }
}

