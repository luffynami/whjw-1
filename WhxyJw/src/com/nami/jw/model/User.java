package com.nami.jw.model;

public class User {
	
	private String hm;
	private String pwd;
	private String secretcode;
	private String role;   //0:学生   1：老师    
	private String viewstate; //隐藏的input
	private String urlcode;
	
	public String getUrlcode() {
		return urlcode;
	}

	public void setUrlcode(String urlcode) {
		this.urlcode = urlcode;
	}

	public String getViewstate() {
		return viewstate;
	}

	public void setViewstate(String viewstate) {
		this.viewstate = viewstate;
	}

	public User(){}


	public String getHm() {
		return hm;
	}

	public void setHm(String hm) {
		this.hm = hm;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	@Override
	public String toString() {
		return "User [hm=" + hm + ", pwd=" + pwd + ", secretcode=" + secretcode
				+ ", role=" + role + ", viewstate=" + viewstate + ", urlcode="
				+ urlcode + "]";
	}

	public String getSecretcode() {
		return secretcode;
	}

	public void setSecretcode(String secretcode) {
		this.secretcode = secretcode;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
