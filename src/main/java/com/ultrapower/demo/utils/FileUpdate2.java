package com.ultrapower.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

public class FileUpdate2 {
	public static void main(String[] args) {
		try {
			Map<String,String> fileNames = fileNames();
			System.out.println(fileNames.size());
			
			File cfgFile = ResourceUtils.getFile(File.separator + "Users" + File.separator + "bob" + File.separator
					+ "Downloads" + File.separator + "QQ文件" + File.separator + "编码格式golden_最新");
			if(cfgFile.isDirectory()) {
				String[] filelist = cfgFile.list();
				for (String string : filelist) {
					File cfgFile1 = ResourceUtils.getFile(File.separator + "Users" + File.separator + "bob" + File.separator
							+ "Downloads" + File.separator + "QQ文件" + File.separator + "编码格式golden_最新" + File.separator + string);
					for (Entry<String, String> entry : fileNames.entrySet()) {
						if(string.contains(entry.getKey())) {
							String end = string.split(entry.getKey())[1];
							File newfile = ResourceUtils.getFile(File.separator + "Users" + File.separator + "bob" + File.separator
									+ "Downloads" + File.separator + "QQ文件" + File.separator + "编码格式golden_new"  + File.separator + entry.getValue() + end);
							copyFileUsingFileStreams(cfgFile1 , newfile);
						}
					}
				}
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Map<String,String> fileNames() {
		Map<String,String> fileNames = new HashMap<String,String>();
		try {
			// 定义一个数据格式化对象
			XSSFWorkbook wb = null;
			// 获取sheet表格，及读取单元格内容
			XSSFSheet sheet = null;
			// excel模板路径
//			File cfgFile = ResourceUtils.getFile("D:" + File.separator + "临时文件" + File.separator + "复制" + File.separator
//					+ "0609" + File.separator + "XC-#9-支持GB18030编码格式-ryh-V1.1.xlsx");
			File cfgFile = ResourceUtils.getFile(File.separator + "Users" + File.separator + "bob" + File.separator
					+ "Downloads" + File.separator + "QQ文件" + File.separator + "01.xlsx");
			InputStream in = new FileInputStream(cfgFile);
			// 读取excel模板
			wb = new XSSFWorkbook(in);
			sheet = wb.getSheetAt(0);
			// 先将获取的单元格设置为String类型，下面使用getStringCellValue获取单元格内容
			// 如果不设置为String类型，如果单元格是数字，则报如下异常
			// java.lang.IllegalStateException: Cannot get a STRING value from a
			// NUMERIC cell
			for (int i = 1; i < (1 << 9); i++) {
				XSSFRow row = sheet.getRow(i);
				if (row == null) {
					break;
				}
//				XSSFCell cell1 =  row.getCell(8);
//				String cellValue1 = cell1.getStringCellValue();
				XSSFCell cellkey = row.getCell(0);
				XSSFCell cellvalue = row.getCell(1);

				if (cellkey == null || cellvalue == null) {
					break;
				}
				// 读取单元格内容
				String key = cellkey.getStringCellValue();
				String value = cellvalue.getStringCellValue();

//				System.out.println("~~~~~~" + cellValue);
				fileNames.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("~~~~~~" + fileNames.size());
		return fileNames;
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
