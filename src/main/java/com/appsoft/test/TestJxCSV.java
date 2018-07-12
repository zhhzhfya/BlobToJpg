package com.appsoft.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.appsoft.data_trans.AppThread;
import com.appsoft.data_trans.DsMgr;
import com.appsoft.db.DbUtil;
import com.appsoft.utils.CSVUtils;
import com.appsoft.utils.DateUtils;

public class TestJxCSV {

	public static void main(String[] args) throws IOException, SQLException {
		// // TODO Auto-generated method stub
		// String str = "v_vehicle#1516160493019.csv";
		// String[] split = str.split("#");
		// for (int i = 0; i < split.length; i++) {
		// System.out.println(split[i]);
		// }
		// long l = 430000168693l;
		// String datetime = DateUtils.getByTimestamp(tm);
		// System.out.println(datetime);
		String str = "2003-01-09 15:26:57.0";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date parse = sdf.parse(str);
			System.out.println(parse);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String filePath =
		// "C:\\Users\\Jack\\Desktop\\sy_comm_menu_1515666268065.csv";
		// Reader in = new FileReader(filePath);
		// Iterable<CSVRecord> records =
		// CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

		// String jsonString = JSONObject.toJSONString(records);
		// List<? extends Centity> parseArray = JSONArray.parseArray(jsonString,
		// new Centity().getClass());
		// System.out.println(parseArray.get(2).getMenu_name());
		// 启动
		// if (args.length > 0) {
		// AppMain.app_home = args[0];
		// } else {
		// AppMain.app_home = System.getProperty("user.dir");
		// }
		// // 初始化数据源
		// DsMgr.initDs();
		//// Connection conn = DsMgr.getConnection("jdbc/ga");
		// Connection conn = DsMgr.getConnection("jdbc/ga");
		// String[] tables = {"WJ_KEY_TRAFFC","WJ_KEY_WORK"};
		// String tableNa ="";
		// File file = new File("C:\\Users\\Jack\\Desktop\\table.txt");
		// FileWriter fw = new FileWriter(file,true);
		// StringBuffer sb = new StringBuffer();
		// for (int i = 0; i < tables.length; i++)
		// {
		// tableNa = tables[i];
		// ResultSet executeQuery = conn.createStatement().executeQuery("select
		// * from "+tableNa+"");
		// ResultSetMetaData metaData = executeQuery.getMetaData();
		// String colType = "";
		// sb.append("\r\n\r\nCREATE TABLE IF NOT EXISTS "+tableNa+"(\r\n");
		// for (int j = 1; j <=metaData.getColumnCount(); j++)
		// {
		// colType = metaData.getColumnTypeName(j);
		// if(j==metaData.getColumnCount())
		// {
		// sb.append("info.\""+metaData.getColumnName(j)+"\" "+colType+");");
		// }else{
		// sb.append("info.\""+metaData.getColumnName(j)+"\" "+colType+",\r\n");
		// }
		// }
		// }
		// fw.write(sb.toString());
		// fw.flush();
		// fw.close();
		// in.close();
	}

}
