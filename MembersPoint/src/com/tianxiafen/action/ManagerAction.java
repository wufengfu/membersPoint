package com.tianxiafen.action;

import java.util.Map;

import javax.servlet.jsp.PageContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.entity.Manager;
import com.tianxiafen.service.IManagerService;
import com.tianxiafen.service.impl.ManagerServiceImpl;

public class ManagerAction extends ActionSupport {

	Map<String,Object> session = ActionContext.getContext().getSession();
	private String userName;
	private String password;
	private Manager manager;
	private String message;
	
	public String login(){
		if(userName==null || "".equals(userName) || password ==null || "".equals(password)){
			return "false";
		}
		IManagerService service = new ManagerServiceImpl();
		try {
			manager = service.login(userName, password);
			if(manager==null){
				message="用户名或密码不正确！";
				return "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message="登录失败！";
			return ERROR;
		}
		session.put("manager", manager);
		return SUCCESS;
	}
	public String logOut(){
		session.clear();
		return SUCCESS;
	}

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

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
