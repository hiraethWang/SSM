package cn.cisdom.pojo;

import java.util.Date;

public class Login {

	private int LoginId;
	private String user;
	private Date time;
	public int getLoginId() {
		return LoginId;
	}
	public void setLoginId(int loginId) {
		LoginId = loginId;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

}
