package com.appsoft.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appsoft.data_trans.AppThread;

public class PropertyUtil {
	private static Logger log = LoggerFactory.getLogger(PropertyUtil.class);

	public static Properties read(String fileName) {
		Properties prop = new Properties();
		String file = getPath(fileName);
		try {
			InputStream in = new FileInputStream(file);
			prop.load(in);
			log.debug("加载{},内容:{}", fileName, prop);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		return prop;
	}

	public static String getPath(String fileName) {
		String classPath = AppThread.app_home + "/conf";
		return classPath + File.separator + fileName;
	}

}
