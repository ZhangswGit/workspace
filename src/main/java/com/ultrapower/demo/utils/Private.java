package com.ultrapower.demo.utils;

public class Private {
	public static void main(String[] args) {
		System.out.println(HexChange(1234));
	}

	public static String HexChange(int binary) {
		StringBuilder sb = new StringBuilder("0x");
		StringBuilder a = new StringBuilder();
		while(binary > 16){
			int i = binary % 16;
			switch (i) {
			case 10:
				a.append("A");
				break;
			case 11:
				a.append("B");
				break;
			case 12:
				a.append("C");
				break;
			case 13:
				a.append("D");
				break;
			case 14:
				sb.append("E");
				break;
			case 15:
				a.append("F");
				break;
			default:
				a.append(i);
			}
			binary = binary / 16;
		}
		switch (binary) {
		case 10:
			a.append("A");
			break;
		case 11:
			a.append("B");
			break;
		case 12:
			a.append("C");
			break;
		case 13:
			a.append("D");
			break;
		case 14:
			sb.append("E");
			break;
		case 15:
			a.append("F");
			break;
		default:
			a.append(binary);
		}
		return sb.append(a.reverse()).toString();
	}
}
