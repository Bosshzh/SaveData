package com.lanling.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class YouXiangUtil {
	
	//�ж��û��Ƿ��Ѿ���������
	public static boolean isbangding(String username, String openid) {
		Connection connection = JDBCUtil.getConnection();//��ȡConnection����
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		};
		ResultSet rs = null;
		boolean bangding = false;//�Ƿ��Ѿ���������
		try {
			if(Util.isUsername(username, openid)) {
				//username��Ϊ�գ��û������˺������¼��
					rs = statement.executeQuery("select * from user where username = '"+username+"';");
					if(rs.next()) {
						if(rs.getString("email") != null) {
							bangding = true;
						}
					}
			}else {
				//openid��Ϊ�գ��û����õ�����qq��¼��
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
