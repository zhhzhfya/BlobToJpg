package com.appsoft.data_trans;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.appsoft.utils.PropertyUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DsMgr {

	private static Logger log = LoggerFactory.getLogger(DsMgr.class);

	private static Map<String, ComboPooledDataSource> dsMap = new HashMap<String, ComboPooledDataSource>();

	private static ThreadLocal<String> dsThread = new ThreadLocal<String>() {
		public String initialValue() {
			return "";
		}
	};

	public static void setDs(String ds) {
		dsThread.set(ds);
	}

	public static void initDs() {
		try {
			Properties prop = PropertyUtil.read("db.properties");
			String[] names = prop.getProperty("names").split(",");
			for (String name : names) {
				Properties props = new Properties();
				ComboPooledDataSource ds = new ComboPooledDataSource();
				ds.setDriverClass(prop.getProperty(name + ".driverClass"));
				ds.setJdbcUrl(prop.getProperty(name + ".jdbcUrl"));
				ds.setUser(prop.getProperty(name + ".user"));
				ds.setPassword(prop.getProperty(name + ".password"));
				ds.setMinPoolSize(Integer.parseInt(prop.getProperty(name + ".minPoolSize")));
				ds.setAcquireIncrement(Integer.parseInt(prop.getProperty(name + ".acquireIncrement")));
				ds.setMaxPoolSize(Integer.parseInt(prop.getProperty(name + ".maxPoolSize")));
				ds.setMaxIdleTime(Integer.parseInt(prop.getProperty(name + ".maxIdleTime")));
				ds.setMaxConnectionAge(Integer.parseInt(prop.getProperty(name + ".maxConnectionAge")));//
				
				dsMap.put(name, ds);
			}
			log.debug("init ds ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addDs(JSONObject json) {
		String[] keys = new String[] { "name", "driverClass", "jdbcUrl", "user", "password", "minPoolSize", "acquireIncrement", "maxPoolSize" };
		for (String k : keys) {
			if (json.containsKey(k)) {
				log.error("添加数据源失败没有包含:{}", k);
				return;
			}
		}

		try {
			ComboPooledDataSource ds = new ComboPooledDataSource();
			ds.setDriverClass(json.getString("driverClass"));
			ds.setJdbcUrl(json.getString("jdbcUrl"));
			ds.setUser(json.getString("user"));
			ds.setPassword(json.getString("password"));
			ds.setMinPoolSize(Integer.parseInt(json.getString("minPoolSize")));
			ds.setAcquireIncrement(Integer.parseInt(json.getString("acquireIncrement")));
			ds.setMaxPoolSize(Integer.parseInt(json.getString("maxPoolSize")));
			dsMap.put(json.getString("name"), ds);
			log.debug("添加数据源成功:{}", json.getString("name"));
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	public static Connection getConnection() {
		return getConnection(dsThread.get());
	}

	public static Connection getConnection(String ds) {
		if (dsMap.containsKey(ds)) {
			try {
				return dsMap.get(ds).getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ds == null || "".equals(ds)) {
			for (ComboPooledDataSource cds : dsMap.values()) {
				try {
					return cds.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
