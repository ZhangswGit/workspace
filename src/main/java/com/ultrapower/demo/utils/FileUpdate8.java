package com.ultrapower.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.util.ResourceUtils;

public class FileUpdate8 {
	public static void main(String[] args) {
		try {
			File cfgFile = ResourceUtils.getFile(File.separator + "Users" + File.separator + "bob" + File.separator
					+ "Downloads" + File.separator + "QQ文件" + File.separator + "FTP_IMPORT_RYH_01_01.csv");			
			for (int i = 1; i <= 99; i++) {
				File newfile = ResourceUtils.getFile(File.separator + "Users" + File.separator + "bob" + File.separator
						+ "Downloads" + File.separator + "QQ文件" + File.separator + "1" + File.separator +"FTP_IMPORT_GT_RYH_"+ (i<10 ? "0"+i : ""+i)  +"_01ng1-golden.csv");
				copyFileUsingFileStreams(cfgFile, newfile);
			}
			System.out.println("辛苦了");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
