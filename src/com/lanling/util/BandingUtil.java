package com.lanling.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BandingUtil {
	
	public static String isbangding(String username,String openid) {
		Connection connection = JDBCUtil.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		String email = "δ֪";
		try {
			statement = connection.createStatement();
			String sql = Util.isUsername(username, openid)?"select email from user where username = '"+username+"';":"select email from userqq where openid = '"+openid+"';";
			rs = statement.executeQuery(sql);
			if(rs.next()) {
				email = rs.getString("email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, statement, connection);
		}
		return email;
	}

}
