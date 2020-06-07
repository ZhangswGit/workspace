package com.ultrapower.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {
	public static void main(String[] args) throws IOException {
		// 两个参数
		String filepath = args[0];// 文件路径
		String count = args[1];// 复制个数
		String filename = filepath.substring(filepath.lastIndexOf(File.separator) + 1);
		String[] split = filename.split("_");
		int nownum = 0;// 当前编号
		String pre = "";
		String last = "";
		for (int i = 0; i < split.length; i++) {
			try {
				nownum = Integer.valueOf(split[i]);
				String[] split2 = filename.split("_"+split[i]+"_");
				pre = split2[0];
				last = split2[1];
				break;
			} catch (NumberFormatException e) {
				continue;
			}
		}

		File oldname = new File(filepath);
		for (int i = nownum + 1; i < Integer.valueOf(count) + nownum + 1; i++) {
			String num = i + "";
			if (String.valueOf(i).length() < 2) {
				num = "0" + i;
			}
			String newpath = filepath.substring(0, filepath.lastIndexOf(File.separator)) + File.separator + pre + "_" + num + "_" + last;
			File newname = new File(newpath);
			copyFileUsingFileStreams(oldname, newname);
		}
		System.out.println("------------------辛苦啦--------------------");
	}

	private static void copyFileUsingFileStreams(File source, File dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		} finally {
			input.close();
			output.close();
		}
	}
}
