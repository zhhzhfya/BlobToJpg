package com.appsoft.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.appsoft.data_trans.AppThread;
import com.appsoft.data_trans.DsMgr;
import com.appsoft.db.Bean;

import oracle.sql.BLOB;

public class TestInserTemp {

	public static void main(String[] args) throws IOException, SQLException, ParseException {
		// TODO Auto-generated method stub
		// String filepath = "C:\\soft\\v_vehicle#1516160493019.csv";
		if (args.length > 0) {
			AppThread.app_home = args[0];
		} else {
			AppThread.app_home = System.getProperty("user.dir");
		}
		DsMgr.initDs();
		DsMgr.setDs("jdbc/rhcore");
		// String filepath =
		// "C:\\soft\\testcol\\errorfile\\T_XZJCSRJYZMS#1516965736414_1353132.csv";
		// WorkOrder wo = new WorkOrder();
		// wo.work(filepath, "#", 0, 1);
		// String sql = "select rybh,xm from T_LSGL_RK_JBXX where rksj is not
		// null and rownum <4";
		// List<Bean> query = DbUtil.getInstance().query(sql);
		// String feilePath = "C:\\soft\\testcol\\errorfile\\ppooo.csv";
		// BufferedWriter bufferedWriter = new BufferedWriter(new
		// OutputStreamWriter(new FileOutputStream(feilePath)));
		// String temp;
		// for (Bean bean : query)
		// {
		// bufferedWriter.newLine();
		// temp = JSONObject.toJSONString(bean);
		// bufferedWriter.write(temp);
		// }
		// bufferedWriter.flush();
		// bufferedWriter.close();
		// String sql = "select * from T_LSGL_RK_ZPXX where 1 =1 and rownum <2";
		// List<Bean> query = DbUtil.getInstance().query(sql);
		// for (Bean bean : query) {
		// Iterator<Entry<Object, Object>> iterator =
		// bean.entrySet().iterator();
		// while(iterator.hasNext()){
		// Entry<Object, Object> next = iterator.next();
		// Object object = next.getValue();
		// if(object instanceof BLOB){
		// saveBlob(object);
		// }
		// }
		// }
		// String sql = "select * from bd_alarmresult_1 where rownum <100";
		// List<Bean> query = DbUtil.getInstance().query(sql);
		// String path = "C:\\soft\\testcol\\bd_alarmresult_1#00130401230.csv";
		// if(query.size()>0)
		// {
		// long str = System.currentTimeMillis();
		// writeJsonFile(query,path);
		// System.out.println("创建成功!共耗时-->"+(System.currentTimeMillis()-str));
		// }
		String filePath = "C:\\soft\\test2.csv";
		String outPath = "C:\\soft\\testcol\\errorfile\\uf8test2.csv";
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath), "UTF-8"));
		String rl = null;
		long n = 0l;
		while ((rl = in.readLine()) != null) {
			if (n == 0) {
				out.write(rl);
			} else {
				out.newLine();
				out.write(rl);
			}
			n++;
		}
		in.close();
		out.flush();
		out.close();
		System.out.println("写出完成");
	}

	private static void saveBlob(Object object) throws SQLException, IOException {
		// TODO Auto-generated method stub
		BLOB blo = (BLOB) object;
		InputStream binaryStream = blo.getBinaryStream();
		BufferedInputStream in = new BufferedInputStream(binaryStream);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		while ((in.read(b)) != -1) {
			out.write(b);
		}
		in.close();
		byte[] byteArray = out.toByteArray();
		out.close();
		FileImageOutputStream imageOutPut = new FileImageOutputStream(new File("C:\\soft\\testcol\\errorfile\\02.jpg"));
		imageOutPut.write(byteArray, 0, byteArray.length);
		imageOutPut.flush();
		imageOutPut.close();
		System.out.println("写入成功");
	}

	private static void OuWriteCSV(Object[] heads, List<Object[]> li, String path) throws IOException {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		String strh = StringUtils.join(heads, ",");
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "GBK"));
		fileWriter.write(strh);
		for (int i = 0; i < li.size(); i++) {
			fileWriter.newLine();
			Object[] objects = li.get(i);
			int m = objects.length;
			for (Object object : objects) {
				m--;
				if (m == 0) {
					sb.append("\"").append(object).append("\"");
				} else {
					sb.append("\"").append(object).append("\"").append(",");
				}
			}
			fileWriter.write(sb.toString());
			sb.setLength(0);
		}
		fileWriter.flush();
		fileWriter.close();
	}

	public static void writeJsonFile(List<Bean> list, String filePath) throws IOException, FileNotFoundException {
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "GBK"), 128);
		int si = list.size();
		Bean bean = null;
		String js = null;
		for (int i = 0; i < si; i++) {
			if (i != 0) {
				fileWriter.newLine();
			}
			bean = list.get(i);
			js = JSONObject.toJSONString(bean);
			// fileWriter.write(js);
			fileWriter.append(js);
		}
		list = null;
		bean = null;
		js = null;
		fileWriter.flush();
		fileWriter.close();
	}
}
