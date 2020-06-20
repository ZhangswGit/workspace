package com.ultrapower.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

public class FileUpdate3 {
	public static void main(String[] args) {
		try {
			//关键字
			String key = "DROP USER";
			String fileName = "aggregate_min_ryh_08_01ng1-golden";
			List<String> fileNames = fileNames(key);
			String path = File.separator + "Users" + File.separator + "bob" + File.separator + "Downloads" + File.separator
					+ "QQ文件" + File.separator + fileName + ".csv";
			String pathroot = File.separator + "Users" + File.separator + "bob" + File.separator + "Downloads" + File.separator
					+ "QQ文件" + File.separator +  "user" + File.separator ;
			for (String filename : fileNames) {
				copyFileUsingFileStreams(new File(path), new File(pathroot + filename));
			}
			System.out.println("完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> fileNames(String key) {
		List<String> fileNames = new ArrayList<String>();
		try {
			// 定义一个数据格式化对象
			XSSFWorkbook wb = null;
			// 获取sheet表格，及读取单元格内容
			XSSFSheet sheet = null;
			// excel模板路径
//			File cfgFile = ResourceUtils.getFile("D:" + File.separator + "临时文件" + File.separator + "复制" + File.separator
//					+ "0609" + File.separator + "XC-#9-支持GB18030编码格式-ryh-V1.1.xlsx");
			File cfgFile = ResourceUtils.getFile(
					File.separator + "Users" + File.separator + "bob" + File.separator + "Downloads" + File.separator
					+ "QQ文件" + File.separator + "XC-#9-支持GB18030编码格式-ryh-V1.1.xlsx");
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
				XSSFCell cell1 =  row.getCell(8);
				String cellValue1 = cell1.getStringCellValue();
				if(!cellValue1.contains(key)) {
					continue;
				}
				XSSFCell cell = row.getCell(5);
				// 读取单元格内容
				String cellValue = cell.getStringCellValue();
				fileNames.add(cellValue + "ng2-golden" + ".csv");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("~~~~~~"+fileNames.size());
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
