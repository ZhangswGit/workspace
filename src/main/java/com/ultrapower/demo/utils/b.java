package com.ultrapower.demo.utils;

import java.util.HashSet;
import java.util.Set;

public class b implements Runnable{

	@Override
	public void run(){
		StringBuffer sb = new StringBuffer();
		try {
			Thread.sleep(1000);
			int a = 1/0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Set set = new HashSet<>();
		set.add(3);
		set.add(4);
		set.add(1);
		for (Object object : set) {
			System.out.println(object);
		}
	}
	public static void main(String[] args) {
		Thread t = new Thread(new b());
		t.start();
	}
	
	
}
