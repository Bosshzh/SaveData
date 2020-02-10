package com.lanling.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class YouXiangUtil {
	
	//判断用户是否已经绑定了邮箱
	public static boolean isbangding(String username, String openid) {
		Connection connection = JDBCUtil.getConnection();//获取Connection对象
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		};
		ResultSet rs = null;
		boolean bangding = false;//是否已经绑定了邮箱
		try {
			if(Util.isUsername(username, openid)) {
				//username不为空，用户是用账号密码登录的
					rs = statement.executeQuery("select * from user where username = '"+username+"';");
					if(rs.next()) {
						if(rs.getString("email") != null) {
							bangding = true;
						}
					}
			}else {
				//openid不为空，用户是用第三方qq登录的
					rs = statement.executeQuery("select * from userqq where openid = '"+openid+"';");
					if(rs.next()) {
						if(rs.getString("email") != null) {
							bangding = true;
						}
					}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, statement, connection);
		}
		return bangding;
	}

}
