package com.appsoft.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.appsoft.data_trans.AppThread;
import com.appsoft.db.Bean;

public class CSVUtils {
	private static Logger log = LoggerFactory.getLogger(CSVUtils.class);
	// CSV文件分隔符
	private final static String NEW_LINE_SEPARATOR = "\n";
	// 初始化csvformat
	CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

	/**
	 * 写入csv文件
	 * 
	 * @param headers 列头
	 * @param data 数据内容
	 * @param filePath 创建的csv文件路径
	 * @throws IOException
	 **/
	public static void writeCsv(Object[] headers, List<Object[]> data, String filePath) throws IOException {
		// 初始化csvformat
		CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
		// 创建FileWriter对象
		// FileWriter fileWriter = new FileWriter(filePath);
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath));
		// 创建CSVPrinter对象
		CSVPrinter printer = new CSVPrinter(fileWriter, formator);
		// 写入列头数据
		printer.printRecord(headers);
		if (null != data) {
			// 循环写入数据
			for (Object[] lineData : data) {
				printer.printRecord(lineData);
			}
		}
		printer.flush();
		printer.close();
		fileWriter.close();
		log.debug("CSV文件创建成功,文件路径:" + filePath);
	}

	/**
	 * 读取csv文件
	 * 
	 * @param filePath 文件路径
	 * @param headers csv列头
	 * @return CSVRecord 列表
	 * @throws IOException
	 **/
	public static List<CSVRecord> readCSV(String filePath, String[] headers) throws IOException {

		// 创建CSVFormat
		CSVFormat formator = CSVFormat.DEFAULT.withHeader(headers);

		FileReader fileReader = new FileReader(filePath);

		// 创建CSVParser对象
		CSVParser parser = new CSVParser(fileReader, formator);

		List<CSVRecord> records = parser.getRecords();

		parser.close();
		fileReader.close();

		return records;
	}

	public static List<CSVRecord> read(String filePath) throws IOException {
		FileReader in = new FileReader(filePath);
		CSVParser parse = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
		List<CSVRecord> records = parse.getRecords();
		in.close();
		return records;
	}

	public static List<CSVRecord> readStream(BufferedReader br) throws Exception {
		// CSVParser parse =
		// CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(br);
		CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
		CSVParser parse = new CSVParser(br, format);
		List<CSVRecord> records = parse.getRecords();
		br.close();
		return records;
	}

	public static List<Bean> readJsonStream(BufferedReader br, String tableName, int offset, int pageSize, String ds, String whereSql) throws IOException {
		String rLine = null;
		List<Bean> li = new ArrayList<>();
		Bean be = new Bean();
		while ((rLine = br.readLine()) != null) {
			try {
				li.add(JSONObject.parseObject(rLine, be.getClass()));
			} catch (Exception e) {
				log.error("JSON格式化失败::-表{}->{}<--数据", tableName, rLine);
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
				sb.append(AppThread.app_home).append("/ftp_out/").append(tableName).append("_").append(System.currentTimeMillis()).append("_")
						.append(new Random().nextInt(1000)).append(".txt");
				String sbstr = sb.toString();
				FileUtils.write(new File(sbstr), jsto);
				br.close();
				sb.setLength(0);
				li = null;
				// uperrFile(sbstr);
				return li;
			}
		}
		return li;
	}

}
