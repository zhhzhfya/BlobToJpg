package com.appsoft.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appsoft.data_trans.DsMgr;
import com.appsoft.utils.DateUtils;

public class DbUtil {
	private static final Logger log = LoggerFactory.getLogger(DbUtil.class);
	public static final int MAX_SIZE = 5000;
	private static DbUtil db = null;

	public static DbUtil getInstance() {
		if (null == db) {
			db = new DbUtil();
		}
		return db;
	}

	/**
	 * 查询单体数据
	 * 
	 * @param sql sql语句
	 * @return 查询结果
	 */
	public Bean queryOne(String sql) {
		return queryOne(DsMgr.getConnection(), sql);
	}

	/**
	 * 查询单体数据
	 * 
	 * @param conn 数据库连接
	 * @param sql sql语句
	 * @return 查询结果
	 */
	public Bean queryOne(Connection conn, String sql) {
		return queryOne(conn, sql, null);
	}

	/**
	 * 查询单条数据
	 * 
	 * @param sql sql语句
	 * @param values prepared sql参数
	 * @return 查询结果
	 */
	public Bean queryOne(String sql, List<Object> values) {
		return queryOne(DsMgr.getConnection(), sql, values);
	}

	/**
	 * 查询单体数据
	 * 
	 * @param conn 数据库连接
	 * @param sql sql语句
	 * @param values prepared sql参数
	 * @return 查询结果
	 */
	public Bean queryOne(Connection conn, String sql, List<Object> values) {
		List<Bean> rtnList = query(conn, sql, 0, 1, values);
		if (!rtnList.isEmpty()) {
			return rtnList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 查询sql，不分页方式
	 * 
	 * @param sql sql语句
	 * @return 查询列表数据
	 */
	public List<Bean> query(String sql) {
		return query(sql, null, null);
	}

	/**
	 * 查询sql，不分页方式
	 * 
	 * @param sql sql语句
	 * @param qc 回调方法
	 * @return 查询列表数据
	 */
	public List<Bean> query(String sql, QueryCallback qc) {
		return query(sql, null, qc);
	}

	/**
	 * 查询sql
	 * 
	 * @param conn 数据库连接
	 * @param sql sql语句
	 * @return 查询列表数据
	 */
	public List<Bean> query(Connection conn, String sql) {
		return query(conn, sql, null);
	}

	/**
	 * 查询sql
	 * 
	 * @param sql sql语句
	 * @param values 参数值（prepared sql）
	 * @return 查询列表数据
	 */
	public List<Bean> query(String sql, List<Object> values) {
		return query(DsMgr.getConnection(), sql, values);
	}

	/**
	 * 查询sql
	 * 
	 * @param sql sql语句
	 * @param values 参数值（prepared sql）
	 * @param qc 回调方法
	 * @return 查询列表数据
	 */
	public List<Bean> query(String sql, List<Object> values, QueryCallback qc) {
		return query(DsMgr.getConnection(), sql, values, qc);
	}

	/**
	 * 查询sql，不分页方式
	 * 
	 * @param conn 数据库连接
	 * @param sql sql语句
	 * @param values 参数值（prepared sql）
	 * @return 查询列表数据
	 */
	public List<Bean> query(Connection conn, String sql, List<Object> values) {
		return query(conn, sql, 0, -1, values, null);
	}

	/**
	 * 查询sql，不分页方式
	 * 
	 * @param conn 数据库连接
	 * @param sql sql语句
	 * @param values 参数值（prepared sql）
	 * @param qc 回调方法
	 * @return 查询列表数据
	 */
	public List<Bean> query(Connection conn, String sql, List<Object> values, QueryCallback qc) {
        RowHandlerList rh = new RowHandlerList();
        queryCall(conn, sql, values, rh, qc);
        return rh;
	}

	/**
	 * 查询sql执行，采用通用游标方式支持分页
	 * 
	 * @param sql sql语句
	 * @param offset 记录所在位置
	 * @param count 获取记录数，大于0表示分页处理
	 * @return 查询列表数据
	 */
	public List<Bean> query(String sql, int offset, int count) {
		return query(sql, offset, count, null);
	}

	/**
	 * 查询sql执行，采用rownum方式支持分页
	 * 
	 * @param sql sql语句
	 * @param offset 记录所在位置
	 * @param count 获取记录数，大于0表示分页处理
	 * @param values prepared sql 参数信息
	 * @return 查询列表数据
	 */
	public List<Bean> query(String sql, int offset, int count, List<Object> values) {
		return query(DsMgr.getConnection(), sql, offset, count, values, null);
	}

	/**
	 * 查询sql执行
	 * 
	 * @param sql sql语句
	 * @param values prepared sql 参数信息
	 * @param rh 行处理器
	 */
	public void queryCall(String sql, List<Object> values, IRowHandler rh) {
		queryCall(DsMgr.getConnection(), sql, 0, -1, values, rh, null);
	}

	/**
	 * 查询sql执行
	 * 
	 * @param sql sql语句
	 * @param offset 记录所在位置
	 * @param count 获取记录数，大于0表示分页处理
	 * @param values prepared sql 参数信息
	 * @param rh 行处理器
	 */
	public void queryCall(String sql, int offset, int count, List<Object> values, IRowHandler rh) {
		queryCall(DsMgr.getConnection(), sql, offset, count, values, rh, null);
	}

	/**
	 * 查询sql执行
	 * 
	 * @param conn 连接
	 * @param sql sql语句
	 * @param offset 记录所在位置
	 * @param count 获取记录数，大于0表示分页处理
	 * @param values prepared sql 参数信息
	 * @param rh 行处理器
	 */
	public void queryCall(Connection conn, String sql, int offset, int count, List<Object> values, IRowHandler rh) {
		queryCall(conn, sql, values, rh, null);
	}
	
    /**
     * 查询sql执行，采用通用游标方式支持分页
     * @param conn 数据库连接，如果参数conn为null时自己获取缺省数据源的conn
     * @param sql sql语句
     * @param offset 记录所在位置，从1开始
     * @param count 获取记录数，大于0表示分页处理
     * @param values prepare sql的参数信息
     * @param rh    行处理器，支持大数据量自定义处理
     * @param qc    回调方法
     */
    public void queryCall(Connection conn, String sql, int offset, int count, List<Object> values,
            IRowHandler rh, QueryCallback qc) {
        if (count > 0) { // 分页处理
            StringBuilder paging = new StringBuilder();
            if (values != null) { // psql
                paging.append("select * from ( select row_.*, rownum rownum_ from ( ");
                paging.append(sql).append(" ) row_ where rownum < ?) where rownum_>=?");
                values.add(offset + count);
                values.add(offset);
            } else {
                paging.append("select * from ( select row_.*, rownum rownum_ from ( ");
                paging.append(sql).append(" ) row_ where rownum < ").append(offset + count);
                paging.append(") where rownum_>=").append(offset);
            }
            sql = paging.toString();
        }
        queryCall(conn, sql, values, rh, qc);
    }
    /**
     * 查询sql执行，采用rownum方式支持分页
     * @param conn 数据库连接
     * @param sql sql语句
     * @param offset 记录所在位置
     * @param count 获取记录数，大于0表示分页处理
     * @param values prepared sql 参数信息，如果null则执行普通sql
     * @param qc 回调方法
     * @return 查询列表数据
     */
    public List<Bean> query(Connection conn, String sql, int offset, int count, List<Object> values, QueryCallback qc) {
        if (count > 0) { // 分页处理
            StringBuilder paging = new StringBuilder();
            if (values != null) { // psql
                paging.append("select * from ( select row_.*, rownum rownum_ from ( ");
                paging.append(sql).append(" ) row_ where rownum < ?) where rownum_>=?");
                values.add(offset + count);
                values.add(offset);
            } else {
                paging.append("select * from ( select row_.*, rownum rownum_ from ( ");
                paging.append(sql).append(" ) row_ where rownum < ").append(offset + count);
                paging.append(") where rownum_>=").append(offset);
            }
            sql = paging.toString();
        }
        log.info(sql);
        return query(conn, sql, values, qc);
    }

	/**
	 * 查询sql执行
	 * 
	 * @param sql sql语句
	 * @param offset 记录所在位置
	 * @param count 获取记录数，大于0表示分页处理
	 * @param values prepared sql 参数信息
	 * @param qc 回调方法
	 * @return 查询列表数据
	 */
	public List<Bean> query(String sql, int offset, int count, List<Object> values, QueryCallback qc) {
		return query(DsMgr.getConnection(), sql, offset, count, values, qc);
	}

	/**
	 * 查询sql执行，采用通用游标方式支持分页
	 * 
	 * @param conn 数据库连接
	 * @param sql sql语句
	 * @param offset 记录所在位置
	 * @param count 获取记录数，大于0表示分页处理
	 * @return 查询列表数据
	 */
	public List<Bean> query(Connection conn, String sql, int offset, int count) {
		return query(conn, sql, offset, count, null, null);
	}

	/**
	 * 查询sql执行，采用通用游标方式支持分页
	 * 
	 * @param conn 数据库连接，如果参数conn为null时自己获取缺省数据源的conn
	 * @param sql sql语句
	 * @param offset 记录所在位置
	 * @param count 获取记录数，大于0表示分页处理
	 * @param values prepare sql的参数信息
	 * @return 查询列表数据
	 */
	public List<Bean> query(Connection conn, String sql, int offset, int count, List<Object> values) {
		return query(conn, sql, offset, count, values, null);
	}

	/**
	 * 查询sql执行，采用通用游标方式支持分页
	 * 
	 * @param conn 数据库连接，如果参数conn为null时自己获取缺省数据源的conn
	 * @param sql sql语句
	 * @param offset 记录所在位置
	 * @param count 获取记录数，大于0表示分页处理
	 * @param values prepare sql的参数信息
	 * @param qc 回调方法
	 * @return 查询列表数据
	 */
//	public List<Bean> query(Connection conn, String sql, int offset, int count, List<Object> values, QueryCallback qc) {
//		RowHandlerList rh = new RowHandlerList();
//		queryCall(conn, sql, offset, count, values, rh, qc);
//		return rh;
//	}

	public void queryCall(Connection conn, String sql, List<Object> values, IRowHandler rh, QueryCallback qc) {
		boolean ownConn = false; // 是否为自身的conn
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (conn == null) {
				conn = DsMgr.getConnection();
				ownConn = true;
			}
			long startTime = System.currentTimeMillis(); // 起始时间
			if (values == null || values.size() == 0) { // 正常sql
				// 创建Statement对象与数据库进行数据查询操作
				stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				stmt.setMaxRows(50000);
				rs = stmt.executeQuery(sql);
			} else { // prepared sql
				pstmt = conn.prepareStatement(sql);
				pstmt.setMaxRows(50000);
				for (int i = 0; i < values.size(); i++) {
					pstmt.setObject(i + 1, values.get(i));
				}
				rs = pstmt.executeQuery();
			}
			long endTime = System.currentTimeMillis(); // 结束时间
			// 对结果集数据进行包装处理
			int icount = 0;
			int columnCount = 0;
			ArrayList<Bean> columnList = new ArrayList<Bean>();
			ResultSetMetaData rsmd = rs.getMetaData(); // 获取查询字段列表
			columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				Bean columnBean = new Bean();
				columnBean.set("NAME", rsmd.getColumnName(i));
				columnBean.set("TYPE", rsmd.getColumnType(i));
				columnBean.set("SCALE", rsmd.getScale(i));
				columnList.add(columnBean);
			}
			while ((rs.next())) {
				Bean bean = new Bean();
				for (int i = 0; i < columnCount; i++) {
					if (i == 0) { // 设置虚拟主键
						bean.setId(rs.getString(1));
					}
					Bean columnBean = columnList.get(i);
					switch (columnBean.getInt("TYPE")) {
					case Types.VARCHAR:
						Object value = rs.getObject(i + 1);
						if (value == null) {
							bean.set(columnBean.get("NAME"), "");
						} else {
							bean.set(columnBean.get("NAME"), value);
						}
						break;
					case Types.NUMERIC:
						value = rs.getObject(i + 1);
						if (value == null) {
							bean.set(columnBean.get("NAME"), 0);
						} else if (columnBean.getInt("SCALE") <= 0) {
							bean.set(columnBean.get("NAME"), ((BigDecimal) value).intValue());
						} else {
							bean.set(columnBean.get("NAME"), ((BigDecimal) value).toString());
						}
						break;
					case Types.BLOB:
						bean.set(columnBean.get("NAME"), rs.getBlob(i + 1));
						break;
					case Types.LONGVARBINARY:
						bean.set(columnBean.get("NAME"), rs.getLong(i + 1));
						break;
					case Types.LONGVARCHAR:
						bean.set(columnBean.get("NAME"), rs.getObject(i + 1));
						break;
					case Types.TIMESTAMP:
						bean.set(columnBean.get("NAME"), DateUtils.getByTimestamp(rs.getTimestamp(i + 1)));
						break;
					default:
						// 处理一般型字段
						bean.set(columnBean.get("NAME"), rs.getObject(i + 1));
					}
				}
				if (qc != null) { // 执行回调方法
					bean.set(Constant.PARAM_ROWNUM, icount); // 设置当前行，便于回调方法使用
					qc.call(columnList, bean);
				}
				if (rh != null) {
					bean.set(Constant.PARAM_ROWNUM, icount); // 设置当前行，便于回调方法使用
					rh.handle(columnList, bean);
				}
				icount++;
			} // end while
			if (qc != null) { // 执行回调方法提供列信息，无论是否有数据信息都执行此回调
				qc.end(icount, columnList);
			}
			if (rh != null) { // 执行回调方法提供列信息，无论是否有数据信息都执行此回调
				rh.end(icount, columnList);
			}
			long sqlTime = endTime - startTime;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			// 关闭结果集、数据交互及数据连接
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
	}

	public int execute(String sql) {
		return execute(DsMgr.getConnection(), sql);
	}

	public int execute(Connection conn, String sql) {
		return execute(conn, sql, null);
	}

	public int execute(String sql, List<Object> values) {
		return execute(DsMgr.getConnection(), sql, values);
	}

	public int execute(Connection conn, String sql, List<Object> values) {
		Statement stmt = null;
		PreparedStatement pstmt = null;
		int rtnVal = 0;
		boolean ownConn = false;
		long startTime = System.currentTimeMillis();
		try {
			if (conn == null) {
				conn = DsMgr.getConnection();
			}
			if ((values == null) || (values.size() == 0)) {
				stmt = conn.createStatement();
				sql = removeInvisibleChar(sql);
				rtnVal = stmt.executeUpdate(sql);
			} else {
				pstmt = conn.prepareStatement(sql);
				for (int i = 0; i < values.size(); i++) {
					Object val = values.get(i);
					if ((val instanceof String)) {
						val = removeInvisibleChar((String) val);
					}
					pstmt.setObject(i + 1, val);
					pstmt.setObject(i + 1, values.get(i));
				}
				rtnVal = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
			throw new RuntimeException("数据库错误!", e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				else if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				log.error(sql + "  " + e.getMessage());
				throw new RuntimeException("数据库错误!", e);
			}
		}
		return rtnVal;
	}

	/**
	 * 去掉不可见的Ascii字符避免前台出现错误
	 * 
	 * @param srcStr 原始字符串
	 * @return 移除不可见字符
	 */
	public static String removeInvisibleChar(String srcStr) {
		// 除tab \n\r之外，去掉其它字符
		final String regex = "[\\x00-\\x09\\x0b\\x0c\\x0e-\\x1f\\x7f]";

		Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

		Matcher mt = pattern.matcher(srcStr);
		StringBuffer sb = new StringBuffer();
		while (mt.find()) {
			mt.appendReplacement(sb, "");
		}
		mt.appendTail(sb);
		return sb.toString();
	}

	public int executeBatch(String psql, List<List<Object>> params) {
		return executeBatch(DsMgr.getConnection(), psql, params);
	}

	public int executeBatchSimple(String psql, List<Object[]> params) {
		List _params = new ArrayList<>();
		for (Object[] objs : params) {
			_params.add(Arrays.asList(objs));
		}
		return executeBatch(DsMgr.getConnection(), psql, _params);
	}

	public int executeBatch(Connection conn, String psql, List<List<Object>> params) {
		PreparedStatement stmt = null;
		int rtnVal = 0;
		boolean ownConn = false;
		StringBuilder logs = new StringBuilder("");
		try {
			if (conn == null) {
				conn = DsMgr.getConnection();
				ownConn = true;
			}
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(psql);

			int dataSize = params.size() - 1;
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < params.size(); i++) {
				List records = (List) params.get(i);
				for (int j = 0; j < records.size(); j++) {
					stmt.setObject(j + 1, records.get(j));
				}
				stmt.addBatch();
				if ((i == dataSize) || (i % MAX_SIZE == 0)) {
					int[] ns = stmt.executeBatch();
					conn.commit();
					for (int j = 0; j < ns.length; j++) {
						if (ns[j] > 0)
							rtnVal += ns[j];
						else if (ns[j] == -2) {
							rtnVal++;
						}
					}
				}
			}
			long endTime = System.currentTimeMillis();
			long sqlTime = endTime - startTime;
		} catch (SQLException e) {
			log.error(logs + " " + e.getMessage(), e);
			throw new RuntimeException("数据库错误!", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				log.error(psql + " " + e.getMessage(), e);
				throw new RuntimeException("数据库错误!", e);
			}
		}
		return rtnVal;
	}

}
