package com.appsoft.utils;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadCsvErrors {
	private static Logger log = LoggerFactory.getLogger(ReadCsvErrors.class);

	public static void main(String[] args) {
		File[] files = new File("C:\\soft\\errorfile").listFiles();
		for (File f : files) {
			log.debug("file:{}", f.getName());
			try {
				// List<String> readLines = FileUtils.readLines(f);
				// for (String l : readLines) {
				// if (l.contains("报警称在岳麓区天马小区42栋365")) {
				// char[] charArray = l.toCharArray();
				// System.out.println(l);
				// for (char c : charArray) {
				// String t = String.valueOf(c);
				// if (t.matches("[^\\x00-\\xff]")) {
				// System.out.println(t);
				// }
				// }
				// System.out.println(filterEmoji(l));
				// log.debug("报警称在岳麓区天马小区42栋365");
				// }
				// }
				Iterator<CSVRecord> stream = null;
				while (stream.hasNext()) {
					CSVRecord r = (CSVRecord) stream.next();

				}
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}

		}
	}

	public static String filterEmoji(String src) {
		if (StringUtils.isNotBlank(src)) {
			return src.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
		}
		return src;
	}

}
