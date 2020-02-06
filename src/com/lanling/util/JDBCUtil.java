package com.lanling.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtil {
	
	private static DataSource dataSource=null;
	
	static{
		dataSource=new ComboPooledDataSource("mysql");
	}
	//获取到数据库的连接
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	//关闭数据库
	public static void close(ResultSet rs,Statement statement,Connection connection) {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(statement != null && !statement.isClosed()) {
				statement.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(connection != null && !connection.isClosed()) {
				 connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
