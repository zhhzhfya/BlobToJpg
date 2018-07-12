/*
 * Copyright (c) 2011 Ruaho All rights reserved.
 */
package com.appsoft.db;

import java.util.List;

/**
 * 行处理器接口类
 * 
 * @author jerry li
 */
public interface IRowHandler {
	/**
	 * 处理行数据
	 * 
	 * @param columns 列数据
	 * @param data 行数据
	 */
	void handle(List<Bean> columns, Bean data);

	/**
	 * 整体查询结束后通过回调方法提供列相关信息，无论是否有数据信息都执行此回调。
	 * 
	 * @param count 此次查询的数据数（不是分页查询的总记录数）
	 * @param columns 列信息
	 */
	void end(int count, List<Bean> columns);
}
