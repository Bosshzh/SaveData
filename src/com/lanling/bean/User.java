package com.lanling.bean;

import java.sql.Blob;

public class User {
	
	private String username;//�û��˺�
	private String password;//����
	private String name;//�ǳ�
	private String sex;//�Ա�
	private String province;//ʡ��
	private String city;//����
	private String email;//����
	private String photo;//ͷ��
	private String openid;//openid
	private String photoqq;//ͷ��·��
	private boolean isUsername;//���û��˺ŵ�½��
	
	
	//�û��˺ŵ�¼�ķ�
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
	//qq��¼��
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
