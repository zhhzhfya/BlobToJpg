package com.appsoft.test;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class TestFtp {

	public static void main(String[] args) throws SocketException, IOException {
		// TODO Auto-generated method stub
		FTPClient ftp = new FTPClient();
		ftp.connect("12.15.13.251", 21);
		boolean login = ftp.login("shou", "123456");
		System.out.println(login);
		FTPFile[] listFiles = ftp.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			String str = listFiles[i].getName();
			System.out.println(str);
		}
	}

}
