package com.appsoft.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 要求： 第一行是csv的header，header没有双引号 第二行是数据体，每个用双引号包裹
 * 
 * @author dzwz
 *
 */
public class CsvParser implements Iterable<List<Object>> {
	private static Logger log = LoggerFactory.getLogger(CsvParser.class);
	private BufferedReader reader;

	private boolean hasNext = true;

	public CsvParser(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
		this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "GBK"));
	}

	@Override
	public Iterator<List<Object>> iterator() {
		return new CsvIterator();
	}

	class CsvIterator implements Iterator<List<Object>> {

		@Override
		public boolean hasNext() {
			return hasNext;
		}

		@Override
		public List<Object> next() {
			List<Object> cols = new ArrayList<Object>();
			try {
				char c;
				String txt;
				boolean strBegin = false;
				StringBuffer sb = new StringBuffer();
				while ((txt = reader.readLine()) != null) {
					txt += "\n";
					for (int i = 0; i < txt.length(); i++) {
						c = txt.charAt(i);
						if (strBegin) {
							if (c == '"') {
								if (i + 1 < txt.length()) {
									if (txt.charAt(i + 1) == '"') {
										sb.append(c);
										i++;
									} else {
										strBegin = false;
									}
								} else {
									strBegin = false;
								}
							} else {
								sb.append(c);
							}
						} else {
							if (c == ',') {
								cols.add(sb.toString());
								sb.setLength(0);
							} else if (c == '\n') {// csv每行以换行结束
								cols.add(sb.toString());
								sb.setLength(0);
								// 读取一行结束了，txt应该是读到结尾了。
								return cols;
							} else if (c == '"') {
								strBegin = true;
							} else if (c != '\r') {
								sb.append(c);
							}
						}
					}
				}
				if (sb.length() > 0) {// 最后一行也许没有换行结尾
					cols.add(sb.toString());
				}
				hasNext = false;
			} catch (Exception e) {
				log.error("CsvIterator::error:{}", e.getLocalizedMessage());
			}
			return cols;
		}

		@Override
		public void remove() {

		}

	}

	public static void main(String[] args) {
		File[] files = new File("C:\\soft\\errorfile").listFiles();
		for (File f : files) {
			log.debug("file:{}", f.getName());
			try {
				CsvParser p = new CsvParser(f.getAbsolutePath());
				Iterator<List<Object>> iterator = p.iterator();
				List<Object> h = null;
				while (iterator.hasNext()) {
					if (h == null) {
						h = (List<Object>) iterator.next();
						// System.out.println(h);
					}
					List<Object> list = (List<Object>) iterator.next();
					if (list.size() != h.size() && list.size() != 0) {
						System.out.println(h);
						System.out.println(list);
					}
				}
			} catch (Exception e) {
				log.error(e.getLocalizedMessage());
			}
		}
	}

}
