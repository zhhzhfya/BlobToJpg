package com.appsoft.utils;

import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

public class SysUtil {
	private static OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

	public static String getMemInfo() {
		StringBuffer info = new StringBuffer();
		info.append("总内存：" + mem.getTotalPhysicalMemorySize() / 1024 / 1024 + "MB");
		info.append("\t可用内存：" + mem.getFreePhysicalMemorySize() / 1024 / 1024 + "MB");
		
		return info.toString();
	}
}
