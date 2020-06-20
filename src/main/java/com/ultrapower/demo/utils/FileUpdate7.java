package com.ultrapower.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

public class FileUpdate7 {
	public static void main(String[] args) {
		try {
			String path = "D:" + File.separator + "临时文件" + File.separator + "复制" + File.separator + "3" + File.separator
					+ "aggregate_min";
			File file = new File(path);
			String[] list = null;
			if (file.isDirectory()) {
				list = file.list();
			}
			for (int i = 0; i < list.length; i++) {
				if (list[i].contains("ryh")) {
					return;
				}
				File oldfile = new File(path + File.separator + list[i]);
				String[] split = list[i].split("_");
				StringBuffer sb = new StringBuffer();
				for (int j = 0; j < split.length; j++) {
					sb.append(split[j]);
					if (j != split.length - 1) {
						sb.append("_");
					}
					if (j == 1) {
						sb.append("ryh_");
					}
				}
				File newfile = new File(path + File.separator + sb.toString());
				oldfile.renameTo(newfile);
			}
			System.out.println("辛苦了");
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
			File cfgFile = ResourceUtils.getFile(File.separator + "Users" + File.separator + "bob" + File.separator
					+ "Downloads" + File.separator + "QQ文件" + File.separator + "XC-#9-支持GB18030编码格式-ryh-V1.1.xlsx");
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
				XSSFCell cell1 = row.getCell(8);
				String cellValue1 = cell1.getStringCellValue();
				if (!cellValue1.contains(key)) {
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
		System.out.println("~~~~~~" + fileNames.size());
		return fileNames;
	}

}
