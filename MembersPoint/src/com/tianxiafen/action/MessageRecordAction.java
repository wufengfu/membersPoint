package com.tianxiafen.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.entity.Messagerecord;
import com.tianxiafen.service.IMessageRecordService;
import com.tianxiafen.service.impl.MessageRecordServiceImpl;

@SuppressWarnings("serial")
public class MessageRecordAction extends ActionSupport {

	Map<String,Object> session = ActionContext.getContext().getSession();
	private List<Messagerecord> records;
	private String phoneNum;
	private Date startTime;
	private Date endTime;
	
	private int pageIndex = 1;
	private int pageSize = 5;
	private int itemCount;
	private int pageCount;
	
	public String messageRecordList(){
		if(session.get("manager")==null)
			return LOGIN;
		records = new ArrayList<Messagerecord>();
		try {
			IMessageRecordService service = new MessageRecordServiceImpl();
			records = service.getMessageRecord(phoneNum, startTime, endTime,pageIndex,pageSize);
			
			itemCount = service.getCount();
			pageCount = (int) (Math.ceil((double)itemCount/pageSize));
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public List<Messagerecord> getRecords() {
		return records;
	}

	public void setRecords(List<Messagerecord> records) {
		this.records = records;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
