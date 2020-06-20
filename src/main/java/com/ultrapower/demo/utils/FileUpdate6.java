package com.ultrapower.demo.utils;

import java.io.File;

public class FileUpdate6 {
	public static void main(String[] args) {
		try {
			String path = "D:"+File.separator+"临时文件"+File.separator+"复制"+File.separator+"3"+File.separator+"aggregate_min";
			File file = new File(path);
			String[] list = null;
			if(file.isDirectory()){
				list = file.list();
			}
			for (int i = 0; i < list.length; i++) {
				if(list[i].contains("ryh")){
					return;
				}
				File oldfile = new File(path + File.separator + list[i]);
				String[] split = list[i].split("_");
				StringBuffer sb = new StringBuffer();
				for (int j = 0; j < split.length; j++) {
					sb.append(split[j]);
					if(j != split.length-1){
						sb.append("_");
					}
					if(j == 1){
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
}
