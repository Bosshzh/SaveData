package com.lanling.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BandingUtil {
	
	public static boolean isbangding(String openid) {
		Connection connection = JDBCUtil.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		boolean bangding = false;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select email from userqq where openid = '"+openid+"';");
			if(rs.next()) {
				bangding = true;
			}else {
				bangding = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, statement, connection);
		}
		return bangding;
	}

}
