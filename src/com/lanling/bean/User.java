package com.lanling.bean;

import java.sql.Blob;

public class User {
	
	private String email;//�����˺�
	private String password;//����
	private String name;//�ǳ�
	private String sex;//�Ա�
	private String province;//ʡ��
	private String city;//����
	private String photo;//ͷ��
	private String openid;//openid
	private String photoqq;//ͷ��·��
	private boolean isEmail;//��email��½��
	
	
	//�����˺�
	public User(String email, String password, String name, String sex, String province, String city,boolean isEmail, String photo) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.province = province;
		this.city = city;
		this.photo = photo;
		this.isEmail = isEmail;
	}
	//�û�ID
	public User(String email, String name, String sex, String province, String city, String openid, String photoqq,boolean isEmail) {
		super();
		this.email = email;
		this.name = name;
		this.sex = sex;
		this.province = province;
		this.city = city;
		this.openid = openid;
		this.photoqq = photoqq;
		this.isEmail = isEmail;
	}
	
	
	public boolean isEmail() {
		return isEmail;
	}
	public void setEmail(boolean isEmail) {
		this.isEmail = isEmail;
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
