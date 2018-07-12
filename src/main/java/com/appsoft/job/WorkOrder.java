package com.appsoft.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.appsoft.cache.ColCache;
import com.appsoft.data_trans.DsMgr;
import com.appsoft.db.Bean;
import com.appsoft.db.DbUtil;
import com.appsoft.utils.CSVUtils;

import sun.misc.BASE64Decoder;

/**
 * 
 * @author Jack
 *
 */
public class WorkOrder {

	private static Logger log = LoggerFactory.getLogger(WorkOrder.class);

	/**
	 * 
	 * @param filePath
	 * @param splitStr
	 * @param tableNamesplitIndex
	 * @param reqidsplitIndex
	 * @throws IOException
	 */
	public void work(String filePath, String splitStr, int tableNamesplitIndex, int reqidsplitIndex) throws IOException {

		DsMgr.setDs("jdbc/rhcore");
		StringBuffer sql = new StringBuffer();
		List<Object[]> datas = new ArrayList<>();
		File file = new File(filePath);
		if (!file.exists()) {
			log.debug(filePath);
			return;
		}
		BufferedReader in = null;
		List<Bean> readJsonStream = null;
		Bean bean = null;
		Map<String, String> comm = null;
		SimpleDateFormat sdfymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfymd = new SimpleDateFormat("yyyy-MM-dd");
		Object[] colArr = null;
		Iterator<Entry<String, String>> it = null;
		String key = null;
		BASE64Decoder base64Decoder = new BASE64Decoder();
		int offset;
		int pageSize;
		String ds;
		String whereSql;
		Entry<String, String> next = null;
		List<Object> data = null;
		try {
			String filename = file.getName();
			String[] split = filename.split(splitStr);
			String tableName = split[tableNamesplitIndex];// 0
			offset = Integer.valueOf(split[reqidsplitIndex]);// 1
			pageSize = Integer.valueOf(split[2]);// 2
			ds = split[3];// 3
			whereSql = split[4];// 4
			DsMgr.setDs("jdbc/rhcore");
			comm = getCDMap(tableName);
			if (null == comm) {
				log.error("文件名{},tableName{}", filename, tableName);
				return;
			}
			colArr = comm.keySet().toArray();
			in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file.getAbsolutePath())), "GBK"));
			readJsonStream = CSVUtils.readJsonStream(in, tableName, offset, pageSize, ds, whereSql);
			if (null != in) {
				in.close();
			}
			if (null == readJsonStream || readJsonStream.size() == 0) {
				return;
			}
			int size = readJsonStream.size();
			for (int i = 0; i < size; i++) {
				bean = readJsonStream.get(i);
				data = new ArrayList<>();
				if (sql.length() == 0) {
					sql.append(" (").append(StringUtils.join(colArr, ",")).append(") values (");
					sql.append(StringUtils.rightPad("?", (colArr.length - 1) * 2 + 1, ",?")).append(")");
					sql.insert(0, "INSERT INTO " + tableName);
				}
				it = comm.entrySet().iterator();
				while (it.hasNext()) {
					next = it.next();
					Object obj = null;
					try {
						if (next.getValue().equalsIgnoreCase("DATE")) {
							obj = getDate(bean.getStr(next.getKey()), sdfymdhms, sdfymd);
						} else if (next.getValue().equalsIgnoreCase("TIMESTAMP(6)")) {
							obj = getTimesta(bean.getStr(next.getKey()), sdfymdhms);
						} else if (next.getValue().equalsIgnoreCase("BLOB")) {
							obj = bean.get(next.getKey());
							if (null != obj && !obj.equals("")) {
								obj = base64Decoder.decodeBuffer((String) obj);
							}
						} else if (next.getValue().equalsIgnoreCase("CLOB")) {
							obj = bean.get(next.getKey());
						} else {
							key = next.getKey();
							if (bean.containsKey(key)) {
								obj = bean.getStr(key).replaceAll("\\\\r\\\\n", "\r\n").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r");
							}
						}
						data.add(obj);
					} catch (Exception e) {
						log.error("行数据错误:{}", tableName, e.getMessage());
						data = null;
					}
				}
				if (null != data) {
					datas.add(data.toArray());
				}
				data = null;
			}
			try {
				if (null != datas && datas.size() > 0) {
					log.debug("准备保存");
					DbUtil.getInstance().executeBatchSimple(sql.toString(), datas);
					log.debug("入库成功共{}条", datas.size());
				}
			} catch (Exception e) {
				log.error("表->{}<--,入库失败:{}", tableName, e.getLocalizedMessage());
				errorDataAction(tableName, offset, pageSize, ds, whereSql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("数据解析入库错误", e.getMessage());
		} finally {
			if (null != in) {
				in.close();
			}
			data = null;
			base64Decoder = null;
			next = null;
			ds = null;
			key = null;
			bean = null;
			readJsonStream = null;
			it = null;
			comm = null;
			sql = null;
			datas = null;
			file = null;
			sdfymdhms = null;
			sdfymd = null;
			colArr = null;
			System.gc();
		}
	}

	private void errorDataAction(String tableName, int offset, int pageSize, String ds, String whereSql) throws IOException {
		HashMap<String, Object> mm = new HashMap<>();
		StringBuffer sb = new StringBuffer();
		mm.put("act", "sql");
		mm.put("ds", ds);
		mm.put("table", tableName);
		mm.put("sql", new String(Base64.decodeBase64(whereSql)));
		mm.put("offset", offset);
		mm.put("page_size", pageSize);
		String jsto = JSONObject.toJSONString(mm);// app_home
		sb.setLength(0);
		sb.append("/opt/errorfile/").append(tableName).append("_").append(System.currentTimeMillis()).append(".txt");
		FileUtils.write(new File(sb.toString()), jsto);
	}

	private Object getTimesta(String str, SimpleDateFormat sdfymdhms) throws ParseException {
		if (null != str && str.length() > 0) {
			if (str.length() == 23) {
				// "1985-11-24 00:00:00:000";
				str = str.substring(0, 19);// 1985-11-24 00:00:00
				Date da = sdfymdhms.parse(str);
				Timestamp ti = new Timestamp(da.getTime());
				return ti;
			}
		}
		return null;
	}

	private Map<String, String> getCDMap(String tableName) {
		if (!StringUtils.isBlank(tableName)) {
			HashMap<String, LinkedHashMap<String, String>> cacheMap = ColCache.getInstance().getCacheMap();
			boolean isPs = putVaToCachMap(tableName, cacheMap);
			if (isPs) {
				Map<String, String> mm = ColCache.getInstance().getCacheMap(tableName);
				return mm;
			} else {
				return null;
			}
		}
		return null;
	}

	private boolean putVaToCachMap(String tableName, HashMap<String, LinkedHashMap<String, String>> cacheMap) {
		if (null != cacheMap && cacheMap.containsKey(tableName)) {
			return true;
		} else if ((null != cacheMap && cacheMap.containsKey(tableName) == false) || cacheMap.size() == 0) {
			StringBuffer clsb = new StringBuffer();
			clsb.append("SELECT COLUMN_NAME,DATA_TYPE FROM USER_TAB_COLUMNS T WHERE T.TABLE_NAME= '").append(tableName.toUpperCase()).append("'");
			List<Bean> Liben = DbUtil.getInstance().query(clsb.toString());
			if (Liben.size() > 0) {
				LinkedHashMap mm = new LinkedHashMap<>();
				for (Bean bean : Liben) {
					mm.put(bean.getStr("COLUMN_NAME"), bean.getStr("DATA_TYPE"));
				}
				ColCache.getInstance().setCaCheMap(mm, tableName);
				clsb = null;
				Liben = null;
				return true;
			} else {
				clsb = null;
				return false;
			}
		}
		return false;
	}

	private Object getDate(String timeStr, SimpleDateFormat sdfymdhms, SimpleDateFormat sdfymd) {
		// TODO Auto-generated method stub
		if (null != timeStr && !timeStr.equals("")) {
			if (timeStr.length() == 10) {
				Date date = null;
				try {
					date = sdfymd.parse(timeStr);
					Timestamp ti = new Timestamp(date.getTime());
					return ti;
				} catch (Exception e) {
					log.error("getDate时间格式化错误:{}", e.getMessage());
					return null;
				} finally {
					date = null;
				}
			} else if (timeStr.length() == 19) {
				Date date = null;
				try {
					date = sdfymdhms.parse(timeStr);
					Timestamp ti = new Timestamp(date.getTime());
					return ti;
				} catch (Exception e) {
					log.error("getDate时间格式化错误:{}", e.getMessage());
					return null;
				} finally {
					date = null;
				}
			} else if (timeStr.length() == 23) {
				Date date = null;
				try {
					date = sdfymdhms.parse(timeStr.substring(0, 19));
					Timestamp ti = new Timestamp(date.getTime());
					return ti;
				} catch (Exception e) {
					log.error("getDate时间格式化错误:{}", e.getMessage());
					return null;
				} finally {
					date = null;
				}
			}
		}
		return null;
	}
}
