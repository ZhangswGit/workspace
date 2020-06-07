package com.ultrapower.algorithm.practice;

import org.junit.Test;

public class demo {
	public static void main(String[] args) {
//		System.out.println(1 & 1);
//		System.out.println(1 & 6);
//		System.out.println(3 ^ 6);
		String a = "ab";
		String b = "ab";
		System.out.println(a == b);
	}
	/**
	 * 【程序1】  题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第四个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？ 
	 *  程序分析：   兔子的规律为数列1,1,2,3,5,8,13,21…. 
	 *  从第三个数开始 是前两数之和 
	 *  递归
	 */
	@Test
	public void demo1(){
		System.out.println(f(4));
	}
	public int f(int x){
		if(x == 1 || x == 2){
			return 1;
		}else{
			return f(x-1) + f(x-2);
		}
	}
	/**
	 * 【程序2】 题目：判断101-200之间有多少个素数，并输出所有素数。 
	 *  程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除， 
	 * 	则表明此数不是素数，反之是素数。 
	 */
	@Test
	public void demo2(){
		int count = 0;
		for (int i = 101; i < 200; i++) {
		    boolean flag = true;
			for (int j = 2; j < i; j++) {
				if(i%j == 0){
					//不是素数
					flag = false;
					break;
				}
			}
			if(flag) count++;
		}
		System.out.println(count);
	}
	
	
}
