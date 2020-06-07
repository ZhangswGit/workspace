package com.ultrapower.demo.utils;

import java.util.concurrent.atomic.AtomicLong;

public class Third {
	public static void main(String[] args) {
		StringBuilder one = new StringBuilder("1111111111");
		StringBuilder two = new StringBuilder("2222222222");
		//反过来按位相乘
		char[] ones = one.reverse().toString().toCharArray();
		char[] twos = two.reverse().toString().toCharArray();
		//记录最终值 //需要以原子性操作 ，否则值会有出入
		AtomicLong num1 = new AtomicLong(0l);
		long n1 = num1.get();
		for (int i = 0; i < ones.length; i++) {
			AtomicLong num2 = new AtomicLong(0l);
			//当前位
			long count1 = Long.valueOf(String.valueOf(new char[]{ones[i]}));
			for(int j = 0; j < twos.length; j++){
				//参数one第i位和two每一位相乘后的和
				long count2 = Long.valueOf(String.valueOf(new char[]{twos[j]}));
				long n2 = num2.get();
				n2 = num2.addAndGet((long) (count1 * count2 * Math.pow(10, j)));
				num2 = new AtomicLong(n2);
			}
			n1 = num1.addAndGet((long) (num2.get() * Math.pow(10, i)));
		}
		System.out.println(n1);
		System.out.println(1111111111 * 2222222222l);
	}
}
