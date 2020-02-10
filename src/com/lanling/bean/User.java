package com.lanling.bean;

import java.sql.Blob;

public class User {
	
	private String username;//用户账号
	private String password;//密码
	private String name;//昵称
	private String sex;//性别
	private String province;//省份
	private String city;//城市
	private String email;//邮箱
	private String photo;//头像
	private String openid;//openid
	private String photoqq;//头像路径
	private boolean isUsername;//是用户账号登陆的
	
	
	//用户账号登录的发
	public User(String username, String photo ,String name, String sex, String province, String city,String email,boolean isUsername) {
		super();
		this.username = username;
		this.photo = photo;
		this.name = name;
		this.sex = sex;
		this.province = province;
		this.city = city;
		this.email = email;
		this.isUsername = isUsername;
	}
	//qq登录的
	public User(String openid, String photoqq, String name, String sex, String province, String city, String email) {
		super();
		this.openid = openid;
		this.photoqq = photoqq;
		this.name = name;
		this.sex = sex;
		this.province = province;
		this.city = city;
		this.email = email;
		this.isUsername = isUsername;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isUsername() {
		return isUsername;
	}
	public void setUsername(boolean isUsername) {
		this.isUsername = isUsername;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhotoqq() {
		return photoqq;
	}
	public void setPhotoqq(String photoqq) {
		this.photoqq = photoqq;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
