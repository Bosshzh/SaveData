package com.lanling.bean;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;

import org.omg.CORBA.Environment;

public class UploadData {
	private String location;//����λ��
    private String province;//ʡ��
    private String city;//�м�
    private String district;//�ؼ�
    private double latitude = 0.0;//γ��
    private double longitude = 0.0;//����
    private String crop_sort = "С��";//��������
    private String land_sort = "ˮ��";//��������
    private int harvest;//����
    //���ϵ�����
    private String manure_sort = "��ʩ��";//���ϵ�����
    private int danfei;//����
    private int linfei;//�׷�
    private int jiafei;//�ط�
    private int qita;//������
    private String dongwufenbian = "��";//������
    private String nongyefeiqiwu = "��";//ũҵ������
    private String gongyefeiqiwu = "��";//��ҵ������
    private String shenghuolaji = "��";//��������
    private String nigou = "��";//�๸
    //��ˮ�Ĵ���
    private int water_number;//��ˮ����
    private Date waterDate_first;//��һ�ν�ˮʱ��
    private Date waterDate_second;//�ڶ��ν�ˮʱ��
    private Date waterDate_third;//�����ν�ˮʱ��
    private int waterNumber_first;//��һ�ν�ˮ��
    private int waterNumber_second;//�ڶ��ν�ˮ��
    private  int waterNumber_third;//�����ν�ˮ��
    //��һ��ʩ�ʵ�ʱ��
    private int manure_number;//ʩ�ʴ���
    private Date manureDate_first;//��һ��ʩ��ʱ��
    private Date manureDate_second;//�ڶ���ʩ��ʱ��
    private Date manureDate_third;//������ʩ��ʱ��
    private int manureNumber_first;//��һ��ʩ����
    private int manureNumber_second;//�ڶ���ʩ����
    private int manureNumber_third;//������ʩ����
    private String foreign_id;//���
    private String spray = "����ҩ";//�Ƿ��ҩ
    private String weed = "������";//�Ƿ����
    private Blob land_image1;//���ؾ���ͼ1
    private Blob land_image2;//���ؾ���ͼ2
    private Blob interview_image;//�û���̸ͼ
    private Date date;//�ϴ�ʱ��
    private String user_phone;//�ϴ��û��ֻ���
    
	public String getForeign_id() {
		return foreign_id;
	}
	public void setForeign_id(String foreign_id) {
		this.foreign_id = foreign_id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getCrop_sort() {
		return crop_sort;
	}
	public void setCrop_sort(String crop_sort) {
		this.crop_sort = crop_sort;
	}
	public String getLand_sort() {
		return land_sort;
	}
	public void setLand_sort(String land_sort) {
		this.land_sort = land_sort;
	}
	public int getHarvest() {
		return harvest;
	}
	public void setHarvest(int harvest) {
		this.harvest = harvest;
	}
	public String getManure_sort() {
		return manure_sort;
	}
	public void setManure_sort(String manure_sort) {
		this.manure_sort = manure_sort;
	}
	public int getDanfei() {
		return danfei;
	}
	public void setDanfei(int danfei) {
		this.danfei = danfei;
	}
	public int getLinfei() {
		return linfei;
	}
	public void setLinfei(int linfei) {
		this.linfei = linfei;
	}
	public int getJiafei() {
		return jiafei;
	}
	public void setJiafei(int jiafei) {
		this.jiafei = jiafei;
	}
	public int getQita() {
		return qita;
	}
	public void setQita(int qita) {
		this.qita = qita;
	}
	public String getDongwufenbian() {
		return dongwufenbian;
	}
	public void setDongwufenbian(String dongwufenbian) {
		this.dongwufenbian = dongwufenbian;
	}
	public String getNongyefeiqiwu() {
		return nongyefeiqiwu;
	}
	public void setNongyefeiqiwu(String nongyefeiqiwu) {
		this.nongyefeiqiwu = nongyefeiqiwu;
	}
	public String getGongyefeiqiwu() {
		return gongyefeiqiwu;
	}
	public void setGongyefeiqiwu(String gongyefeiqiwu) {
		this.gongyefeiqiwu = gongyefeiqiwu;
	}
	public String getShenghuolaji() {
		return shenghuolaji;
	}
	public void setShenghuolaji(String shenghuolaji) {
		this.shenghuolaji = shenghuolaji;
	}
	public String getNigou() {
		return nigou;
	}
	public void setNigou(String nigou) {
		this.nigou = nigou;
	}
	public int getWater_number() {
		return water_number;
	}
	public void setWater_number(int water_number) {
		this.water_number = water_number;
	}
	public Date getWaterDate_first() {
		return waterDate_first;
	}
	public void setWaterDate_first(Date waterDate_first) {
		this.waterDate_first = waterDate_first;
	}
	public Date getWaterDate_second() {
		return waterDate_second;
	}
	public void setWaterDate_second(Date waterDate_second) {
		this.waterDate_second = waterDate_second;
	}
	public Date getWaterDate_third() {
		return waterDate_third;
	}
	public void setWaterDate_third(Date waterDate_third) {
		this.waterDate_third = waterDate_third;
	}
	public int getWaterNumber_first() {
		return waterNumber_first;
	}
	public void setWaterNumber_first(int waterNumber_first) {
		this.waterNumber_first = waterNumber_first;
	}
	public int getWaterNumber_second() {
		return waterNumber_second;
	}
	public void setWaterNumber_second(int waterNumber_second) {
		this.waterNumber_second = waterNumber_second;
	}
	public int getWaterNumber_third() {
		return waterNumber_third;
	}
	public void setWaterNumber_third(int waterNumber_third) {
		this.waterNumber_third = waterNumber_third;
	}
	public int getManure_number() {
		return manure_number;
	}
	public void setManure_number(int manure_number) {
		this.manure_number = manure_number;
	}
	public Date getManureDate_first() {
		return manureDate_first;
	}
	public void setManureDate_first(Date manureDate_first) {
		this.manureDate_first = manureDate_first;
	}
	public Date getManureDate_second() {
		return manureDate_second;
	}
	public void setManureDate_second(Date manureDate_second) {
		this.manureDate_second = manureDate_second;
	}
	public Date getManureDate_third() {
		return manureDate_third;
	}
	public void setManureDate_third(Date manureDate_third) {
		this.manureDate_third = manureDate_third;
	}
	public int getManureNumber_first() {
		return manureNumber_first;
	}
	public void setManureNumber_first(int manureNumber_first) {
		this.manureNumber_first = manureNumber_first;
	}
	public int getManureNumber_second() {
		return manureNumber_second;
	}
	public void setManureNumber_second(int manureNumber_second) {
		this.manureNumber_second = manureNumber_second;
	}
	public int getManureNumber_third() {
		return manureNumber_third;
	}
	public void setManureNumber_third(int manureNumber_third) {
		this.manureNumber_third = manureNumber_third;
	}
	public String getSpray() {
		return spray;
	}
	public void setSpray(String spray) {
		this.spray = spray;
	}
	public String getWeed() {
		return weed;
	}
	public void setWeed(String weed) {
		this.weed = weed;
	}
	
	public Blob getLand_image1() {
		return land_image1;
	}
	public void setLand_image1(Blob land_image1) {
		this.land_image1 = land_image1;
	}
	public Blob getLand_image2() {
		return land_image2;
	}
	public void setLand_image2(Blob land_image2) {
		this.land_image2 = land_image2;
	}
	public Blob getInterview_image() {
		return interview_image;
	}
	public void setInterview_image(Blob interview_image) {
		this.interview_image = interview_image;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
    
    

}
