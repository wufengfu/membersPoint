package com.tianxiafen.weixin.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.entity.Systemutil;
import com.tianxiafen.service.ISystemUtilService;
import com.tianxiafen.service.impl.SystemUtilServiceImpl;

public class BusinessIntroAction extends ActionSupport {
	private List<Systemutil> systemUtil;
	private String message;
	public String businessIntro(){
		try {
			ISystemUtilService service = new SystemUtilServiceImpl();
			systemUtil = service.getContents("businessIntro");
		} catch (Exception e) {
			e.printStackTrace();
			message = "获取失败请重新获取！";
			return ERROR;
		}
		return SUCCESS;
	}
	
	public List<Systemutil> getSystemUtil() {
		return systemUtil;
	}

	public void setSystemUtil(List<Systemutil> systemUtil) {
		this.systemUtil = systemUtil;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
