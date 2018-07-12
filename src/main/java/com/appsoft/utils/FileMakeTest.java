package com.appsoft.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileMakeTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String s = "C:\\Users\\dzwz\\Desktop\\15221142332.txt";
		String str = FileUtils.readFileToString(new File(s));
		for (int i = 0; i < 100; i++) {
			FileUtils.writeStringToFile(new File("D:\\workspaces\\data_trans\\ftp_in_demos\\" + System.currentTimeMillis() + i + ".txt"), str);
		}
	}

}
