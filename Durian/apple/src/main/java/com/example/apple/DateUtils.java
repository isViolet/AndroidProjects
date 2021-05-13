package com.example.apple;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

class DateUtils {

    @SuppressLint("SimpleDateFormat")
    static String dateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return df.format(date);
    }
}
