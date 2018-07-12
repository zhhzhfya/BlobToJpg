package com.appsoft.job;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvWorker implements Runnable {
	private static Logger log = LoggerFactory.getLogger(CsvWorker.class);

	String filePath;

	public CsvWorker(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void run() {
		try {
			// 解析当前数据文件，然后入中间表，执行sql导入业务表，根据啥标识不同的数据，写入不同的数据源、不同的表
			WorkOrder wo = new WorkOrder();
			wo.work(filePath, "#", 0, 1);
			wo = null;
			int c = 0;
			while (!FileUtils.deleteQuietly(new File(filePath)) && isFileExists(filePath)) {
				System.gc();
				try {
					log.debug("哎呀，删除本地文件失败了，再删一次，文件:{}", filePath);
					c = c++ > 2 ? 2 : c++;
					Thread.sleep(c * 1000);
				} catch (InterruptedException e) {
					log.error("Worker::deleteFileError:{}", e.getLocalizedMessage());
				}
			}
		} catch (Exception e) {
			log.error("执行命令出现异常:{}", e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public boolean isFileExists(String file) {
		File f = new File(file);
		return f.exists();
	}

}