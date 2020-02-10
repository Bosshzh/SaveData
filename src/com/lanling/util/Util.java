package com.lanling.util;

public class Util {
	
	//判断是账号密码登录还是第三方登录
	public static boolean isUsername(String username,String openid) {
		return "0".equals(username)?false:true;
	}

}
