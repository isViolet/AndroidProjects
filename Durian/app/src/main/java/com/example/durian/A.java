package com.example.durian;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A {

    public static void main(String[] args){

        String a = "2019年08月09日 18时49分27秒 周五:938\n";
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(a);
        a = m.replaceAll("");
        System.out.println(a);
        String[] var = a.split(":");
        int b = Integer.parseInt(var[1]);
//        System.out.println(b);
        System.out.println("var[0]:" + var[0] + "var[1]" + var[1]);

    }

}
