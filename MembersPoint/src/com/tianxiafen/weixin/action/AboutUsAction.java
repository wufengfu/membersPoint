package com.tianxiafen.weixin.action;

import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.entity.Systemutil;
import com.tianxiafen.service.ISystemUtilService;
import com.tianxiafen.service.impl.SystemUtilServiceImpl;

public class AboutUsAction extends ActionSupport {

	private Systemutil systemUtil;
	private String message;
	public String aboutUs(){
		try {
			ISystemUtilService service = new SystemUtilServiceImpl();
			systemUtil = service.getContent("aboutUs");
		} catch (Exception e) {
			e.printStackTrace();
			message = "获取失败请重新获取！";
			return ERROR;
		}
		return SUCCESS;
	}
	public Systemutil getSystemUtil() {
		return systemUtil;
	}
	public void setSystemUtil(Systemutil systemUtil) {
		this.systemUtil = systemUtil;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
