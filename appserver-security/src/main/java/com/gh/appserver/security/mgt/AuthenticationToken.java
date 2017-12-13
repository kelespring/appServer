package com.gh.appserver.security.mgt;

import java.io.Serializable;

public class AuthenticationToken implements Serializable {
	private static final long serialVersionUID = -1049969410734657350L;
	private String username;
	private String password;
	private boolean rememberMe = false;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}
