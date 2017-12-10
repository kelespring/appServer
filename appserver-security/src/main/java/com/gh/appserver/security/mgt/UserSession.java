package com.gh.appserver.security.mgt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.appserver.web.common.CommonConstant;

public class UserSession implements Serializable {
	
	private static final long serialVersionUID = 1L;
	// 用户名
	private String userName;
	// 密码
	private String password;
	// 记住密码（cookies方式用户身份）
	private boolean isRemember;
	// 令牌（记住密码cookies中存储的加密数据【用户】）
	private String token;
	// 状态(0未登录，1已经登陆）
	private boolean isLogin;
	// 终端类型
	private Byte devType;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRemember() {
		return isRemember;
	}

	public void setRemember(boolean isRemember) {
		this.isRemember = isRemember;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public Byte getDevType() {
		return devType;
	}

	public void setDevType(Byte devType) {
		this.devType = devType;
	}

	public static void main(String[] args) throws Exception {
		UserSession userSession = new UserSession();
		userSession.setDevType(CommonConstant.DEVTYPE_PC);
		FileOutputStream fos = new FileOutputStream("/Users/chao/Documents/workspace/logs/a.log");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(userSession);
		oos.flush();
		oos.close();
		fos.close();
		// Student对象反序列化过程
		FileInputStream fis = new FileInputStream("/Users/chao/Documents/workspace/logs/a.log");
		ObjectInputStream ois = new ObjectInputStream(fis);
		UserSession st1 = (UserSession) ois.readObject();
		System.out.println(st1.getDevType());
	}

}