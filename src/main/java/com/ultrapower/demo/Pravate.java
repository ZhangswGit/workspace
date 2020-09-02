package com.ultrapower.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
*jdk1.8 lamda表达式和stream
*/
public class Pravate {
    public static void main(String[] args) {
        String format = String.format("%s@%s", "111", "222");
        System.out.println(format);
        List<String> lists = new ArrayList<String>();
        lists.add("1");
        lists.add("2");
        lists.add("3");
        lists.forEach(System.out::println);
    }
}
