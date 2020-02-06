package com.lanling.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class YouXiangUtil {
	
	public static boolean isbangding(String openid) {
		Connection connection = JDBCUtil.getConnection();//获取Connection对象
		Statement statement = null;
		ResultSet rs = null;
		boolean bangding = false;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from userqq where openid = '"+openid+"';");
			if(rs.next()) {
				if(rs.getString("email") != null) {
					bangding = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, statement, connection);
		}
		return bangding;
	}

}
