package com.appsoft.utils;

/**
 * 工具类
 * 
 * @author dzwz
 *
 */
public class Util {

	/**
	 * 清空对象
	 * 
	 * @param datas
	 */
	public static void cleanOjbs(Object... datas) {
		for (Object n : datas) {
			n = null;
		}
		System.gc();
	}

}
