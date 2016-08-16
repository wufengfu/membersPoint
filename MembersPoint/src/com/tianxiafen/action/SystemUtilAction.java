package com.tianxiafen.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.entity.Systemutil;
import com.tianxiafen.service.ISystemUtilService;
import com.tianxiafen.service.impl.SystemUtilServiceImpl;

@SuppressWarnings("serial")
public class SystemUtilAction extends ActionSupport {
	Map<String,Object> session = ActionContext.getContext().getSession();
	private List<Systemutil> utilList;
	private Systemutil util;
	
	private int pageIndex = 1;
	private int pageSize = 5;
	private int itemCount;
	private int pageCount;
	
	public String utilList(){
		if(session.get("manager")==null)
			return LOGIN;
		try {
			ISystemUtilService service = new SystemUtilServiceImpl();
			utilList = service.getAllUtil(pageIndex,pageSize);
			itemCount = service.getCount();
			pageCount = (int) (Math.ceil((double)itemCount/pageSize));
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	public String addUtil(){
		ISystemUtilService service = new SystemUtilServiceImpl();
		service.addSystemUtil(util);
		return SUCCESS;
	}
	public String udpateUtil(){
		ISystemUtilService service = new SystemUtilServiceImpl();
		service.updateSystemUtil(util);
		return SUCCESS;
	}
	public List<Systemutil> getUtilList() {
		return utilList;
	}
	public void setUtilList(List<Systemutil> utilList) {
		this.utilList = utilList;
	}
	public Systemutil getUtil() {
		return util;
	}
	public void setUtil(Systemutil util) {
		this.util = util;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
}
