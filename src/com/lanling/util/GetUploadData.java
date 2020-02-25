package com.lanling.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lanling.bean.UploadData;
import com.lanling.bean.User;

public class GetUploadData {
	
	
	
	public ArrayList<UploadData> getUploadData(String username,String openid) {
		String sql = "";
		if(!"0".equals(openid)) {
			//第三方qq登录
			sql = "select * from message where openid = '"+openid+"';";
		}else if(!"0".equals(username)) {
			//账号密码登录方式
			sql = "select * from message where username = '"+username+"';";
		}else {
			return null;
		}
		ArrayList<UploadData> lists = new ArrayList<>();
		Connection connection = null;
		Statement statement1 = null;
		Statement statement2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		connection = JDBCUtil.getConnection();
		try{
			statement1 = connection.createStatement();
			rs1 = statement1.executeQuery(sql);
			while(rs1.next()) {
				UploadData data = new UploadData();
				data.setLocation(rs1.getString("location"));
				data.setProvince(rs1.getString("province"));
				data.setCity(rs1.getString("city"));
				data.setDistrict(rs1.getString("district"));
				data.setLatitude(rs1.getDouble("latitude"));
				data.setLongitude(rs1.getDouble("longitude"));
				data.setLand_sort(rs1.getString("land_sort"));
				data.setCrop_sort(rs1.getString("crop_sort"));
				data.setHarvest(rs1.getInt("harvest"));
				data.setManure_sort(rs1.getString("manure_sort"));
				data.setDanfei(rs1.getInt("manuresortdanfei"));
				data.setLinfei(rs1.getInt("manuresortlinfei"));
				data.setJiafei(rs1.getInt("manuresortjiafei"));
				data.setQita(rs1.getInt("manuresortqita"));
				data.setDongwufenbian(rs1.getString("dongwufenbian"));
				data.setNongyefeiqiwu(rs1.getString("nongyefeiqiwu"));
				data.setGongyefeiqiwu(rs1.getString("gongyefeiqiwu"));
				data.setShenghuolaji(rs1.getString("shenghuolaji"));
				data.setNigou(rs1.getString("nigou"));
				data.setWater_number(rs1.getInt("water_number"));
				data.setWaterDate_first(rs1.getDate("watertimefirst"));
				data.setWaterDate_second(rs1.getDate("watertimesecond"));
				data.setWaterDate_third(rs1.getDate("watertimethird"));
				data.setWaterNumber_first(rs1.getInt("waternumberfirst"));
				data.setWaterNumber_second(rs1.getInt("waternumbersecond"));
				data.setWaterNumber_third(rs1.getInt("waternumberthird"));
				data.setManure_number(rs1.getInt("manure_number"));
				data.setManureDate_first(rs1.getDate("manuretimefirst"));
				data.setManureDate_second(rs1.getDate("manuretimesecond"));
				data.setManureDate_third(rs1.getDate("manuretimethird"));
				data.setManureNumber_first(rs1.getInt("manurenumberfirst"));
				data.setManureNumber_second(rs1.getInt("manurenumbersecond"));
				data.setManureNumber_third(rs1.getInt("manurenumberthird"));
				data.setSpray(rs1.getString("spray"));
				data.setWeed(rs1.getString("weed"));
//				statement2 = connection.createStatement();
//				rs2 = statement2.executeQuery("select * from images where id = '"+rs1.getString("id")+"';");
//				if(rs2.next()) {
//					data.setLand_image1(rs2.getBlob("landimage1"));
//					data.setLand_image2(rs2.getBlob("landimage2"));
//					data.setInterview_image(rs2.getBlob("interview"));
//				}
				lists.add(data);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(statement2 != null) {
				try {
					statement2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(statement1 != null) {
				try {
					statement1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return lists;
	}
	
	public User getUser(String username,String openid) {
		User user = null;
		Connection connection = JDBCUtil.getConnection();//�õ����ݿ������
		Statement statement1 = null;
		ResultSet rs1 = null;
		try {
			
			if(Util.isUsername(username, openid)) {//������û��˺ŵ�½�Ļ�
				statement1 = connection.createStatement();
				rs1 = statement1.executeQuery("select * from user where username='"+username+"';");
				if(rs1.next()) {
					user = new User(username,rs1.getString("photo"),rs1.getString("name"),rs1.getString("sex"),rs1.getString("province"),rs1.getString("city"),rs1.getString("email"),true);
				}
			}else {//�����qq��¼�Ļ�
				statement1 = connection.createStatement();
				rs1 = statement1.executeQuery("select * from userqq where openid = '"+openid+"';");
				if(rs1.next()) {
					user = new User(openid,rs1.getString("photo"),rs1.getString("name"),rs1.getString("sex"),rs1.getString("province"),rs1.getString("city"),rs1.getString("email"));
				}
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(statement1 != null) {
				try {
					statement1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return user;
	}

}
