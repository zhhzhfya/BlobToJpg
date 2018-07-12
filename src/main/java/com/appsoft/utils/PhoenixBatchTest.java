package com.appsoft.utils;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appsoft.data_trans.AppThread;
import com.appsoft.data_trans.DsMgr;
import com.appsoft.db.DbUtil;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

public class PhoenixBatchTest {

	private static Logger log = LoggerFactory.getLogger(AppThread.class);

	public static boolean run = true;
	public static String app_home = "";
	public static Queue<String> saveFiles = new LinkedList<String>();

	public static void main(String[] args) throws IOException {
		// 启动
		log.info("data_save app begin...");
		if (args.length > 0) {
			app_home = args[0];
		} else {
			app_home = System.getProperty("user.dir");
		}
		AppThread.app_home = app_home;
		log.info("ftp download file save to dir:{}/ftp_in", app_home);
		// 设置logger的level的为ERROR，appsoft包下的的为debug
		setLevel("com.appsoft", "ERROR", "DEBUG");
		// 初始化数据源
		DsMgr.initDs();
		DsMgr.setDs("jdbc/hw");
		Random r = new Random();

		String[] split = new String[] { "CS", "UM", "EU", "NA", "CN" };
		for (int a = 0; a < 1000; a++) {
			List<List<Object>> params = new ArrayList<>();
			for (int x = 0; x < 100000; x++) {
				List<Object> list = new ArrayList<>();
				list.add(split[r.nextInt(5)]);
				list.add("Apple.com");
				list.add("Dashboard" + r.nextInt(100000));
				list.add(new Date(System.currentTimeMillis() / 1000));
				list.add(r.nextInt(99));
				list.add(r.nextInt(9999));
				list.add(r.nextInt(9999));
				list.add(r.nextInt(9999));
				list.add(r.nextInt(9999));
				list.add(r.nextInt(9999));
				list.add(r.nextInt(9999));
				list.add(r.nextInt(9999));
				list.add(r.nextInt(9999));
				list.add(r.nextInt(9999));
				list.add(r.nextInt(9999));
				list.add(r.nextInt(9999));
				params.add(list);
			}
			long l = System.currentTimeMillis();
			log.debug("begin");
			String sql = "UPSERT INTO PERFORMANCE_PHOENIX (HOST,DOMAIN,FEATURE,DATE,CORE,DB, ACTIVE_VISITOR1, ACTIVE_VISITOR2, ACTIVE_VISITOR3, "
					+ "ACTIVE_VISITOR4, ACTIVE_VISITOR5, ACTIVE_VISITOR6, ACTIVE_VISITOR7, ACTIVE_VISITOR8, ACTIVE_VISITOR9, "
					+ "ACTIVE_VISITOR0) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			DbUtil.getInstance().executeBatch(sql, params);
			log.debug("params.size:{},耗时:{}", params.size(), System.currentTimeMillis() - l);
		}
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
}
