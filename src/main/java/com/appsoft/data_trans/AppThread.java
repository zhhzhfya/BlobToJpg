package com.appsoft.data_trans;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appsoft.db.Bean;
import com.appsoft.db.DbUtil;
import com.appsoft.enums.MessageEnum;
import com.appsoft.event.MsgEvent;
import com.appsoft.job.JpgWorker;
import com.appsoft.job.QueryWorker;
import com.appsoft.utils.PropertyUtil;
import com.appsoft.utils.SysUtil;
import com.google.common.eventbus.EventBus;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

/**
 * 公安基础数据Blob转jpg文件
 * 
 */
public class AppThread implements Runnable {
	private static Logger log = LoggerFactory.getLogger(AppThread.class);
	private static EventBus eventBus = null;
	public static boolean run = true;
	public static String app_home = "";
	private static int offset = 0;
	private static int count = 500;
	private static int all_count = 500;

	public AppThread(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	public static void setLevel(String key, String level, String level2) {
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		ch.qos.logback.classic.Logger logger = loggerContext.getLogger("root");
		logger.setLevel(Level.toLevel(level));
		if (StringUtils.isNotBlank(level2)) {
			ch.qos.logback.classic.Logger logger2 = loggerContext.getLogger(key);
			if (logger2 != null) {
				logger2.setLevel(Level.toLevel(level2));
			}
		}
		List<ch.qos.logback.classic.Logger> loggerList = loggerContext.getLoggerList();
		for (Logger logger2 : loggerList) {
			logger.debug(logger2.getName());
		}
	}

	public static boolean isFileExists(String file) {
		File f = new File(file);
		return f.exists();
	}

	public void setCount(int count) {
		this.count = count;
	}
	public void setAllCount(int count) {
		this.all_count = count;
	}

	public void stop() {
		this.run = false;
		eventBus.post(new MsgEvent(MessageEnum.CONSOLE.getId() + "正在停止..."));
	}

	@Override
	public void run() {
		run = true;
		log.info("data_save app begin...");
		if (app_home.equals("")) {
			app_home = System.getProperty("user.dir");
		}
		if (app_home.endsWith("bin"))
			app_home = app_home.substring(0, app_home.length() - 4);
		// 设置logger的level的为ERROR，appsoft包下的的为debug
		setLevel("com.appsoft", "ERROR", "DEBUG");
		// 初始化数据源
		DsMgr.initDs();

		Properties pro = PropertyUtil.read("job.properties");
		String sql = pro.getProperty("sql");
		String dir = pro.getProperty("dir");
		File cursorFile = new File(app_home + "\\data\\cursor.d");
		try {
			if (cursorFile.exists()) {
				String cursor = FileUtils.readFileToString(cursorFile);
				if (StringUtils.isNotBlank(cursor)) {
					offset = Integer.parseInt(cursor);
					eventBus.post(new MsgEvent(MessageEnum.ALL_COUNT.getId() + offset));
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		DsMgr.setDs("jdbc/rhcore");
		eventBus.post(new MsgEvent(MessageEnum.ALL_COUNT.getId() + offset));
		while (run) {
			log.info("\n查询：offset:{}, count:{}", offset, count);
			try {
				while (QueryJobMgr.getQueueSize() >= QueryJobMgr.queueSize) {// 结果集剩余小于100就全放入队列，然后赶紧去查数据库
					Thread.sleep(100);
				}
				QueryJobMgr.addJob(new QueryWorker(app_home, dir, sql, offset, count, eventBus));
				FileUtils.write(new File(app_home + "\\data\\cursor.d"), String.valueOf(offset), false);
				offset += count;
				if (offset > all_count) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		while (JobMgr.getQueueSize() > 0 || QueryJobMgr.getQueueSize() > 0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.info("\napp ok");
		// 程序结束
		eventBus.post(new MsgEvent(MessageEnum.OK.getId() + "OK"));
		try {
			FileUtils.write(new File(app_home + "\\data\\cursor.d"), "0", false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
