package com.example.apple;

import java.util.Date;

/**
 * author:Created by ZhangPengFei.
 * data: 2017/12/28
 * 聊天消息的实体类
 */
public class ChatMessage {

    private String name;// 姓名
    private String message;// 消息
    private Type type;// 类型：0.发送者 1.接受者
    private Date data;// 时间

    ChatMessage() {

    }

    ChatMessage(String message, Type type, Date data) {
        super();
        this.message = message;
        this.type = type;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    Type getType() {
        return type;
    }

    void setType(Type type) {
        this.type = type;
    }

    Date getData() {
        return data;
    }

    void setData(Date data) {
        this.data = data;
    }

    public enum Type {
        INCOUNT, OUTCOUNT
    }
}

