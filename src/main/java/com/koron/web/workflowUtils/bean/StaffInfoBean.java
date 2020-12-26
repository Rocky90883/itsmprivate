package com.koron.web.workflowUtils.bean;

import java.util.ArrayList;
import java.util.List;

public class StaffInfoBean {
	public static final List<StaffInfoBean> staff = new ArrayList<>();
	public static final List<StaffInfoBean> depList = new ArrayList<>();
	private String name;
	private String department;
	private String loginid;
	private String pingyin = "";
	private String wb;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the pingyin
	 */
	public String getPingyin() {
		return pingyin;
	}
	/**
	 * @param pingyin the pingyin to set
	 */
	public void setPingyin(String pingyin) {
		this.pingyin = pingyin;
	}
	/**
	 * @return the wb
	 */
	public String getWb() {
		return wb;
	}
	/**
	 * @param wb the wb to set
	 */
	public void setWb(String wb) {
		this.wb = wb;
	}
	/**
	 * @return the loginid
	 */
	public String getLoginid() {
		return loginid;
	}
	/**
	 * @param loginid the loginid to set
	 */
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
}
