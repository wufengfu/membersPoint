package com.tianxiafen.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.entity.Airlinecompany;
import com.tianxiafen.entity.Pointabouchemenet;
import com.tianxiafen.entity.Pointrecord;
import com.tianxiafen.service.IAirlineCompanyService;
import com.tianxiafen.service.IPointRecordService;
import com.tianxiafen.service.impl.AirlineCompanyServiceImpl;
import com.tianxiafen.service.impl.PointRecordServiceImpl;

@SuppressWarnings("serial")
public class PointRecordAction extends ActionSupport {

	private List<Pointrecord> records;
	Map<String,Object> session = ActionContext.getContext().getSession();
	private int companyId;
	private Airlinecompany company;
	private List<Airlinecompany> companys;
	private String userName;
	private String serialNum;
	private Date startTime;
	private Date endTime;
	private int dealStatus;//交易状态
	private int comStatus;//在途状态
	private int dealType;//0代表所有交易类型
	private String message;
	
	//分页
	private int pageIndex = 1;
	private int pageSize = 5;
	private int itemCount;
	private int pageCount;
	
	public String pointRecordList(){
		if(session.get("manager")==null)
			return LOGIN;
		try {
			IAirlineCompanyService comService = new AirlineCompanyServiceImpl();
			companys = comService.getAllCompany();
			company = comService.getCompanyById(companyId);
			IPointRecordService service = new PointRecordServiceImpl();
			records= new ArrayList<Pointrecord>();
			records = service.getRecords(companyId,userName,serialNum,dealStatus, dealType,comStatus, startTime, endTime,pageIndex,pageSize);
			itemCount = service.getCount();
			pageCount = (int) (Math.ceil((double)itemCount/pageSize));
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public List<Pointrecord> getRecords() {
		return records;
	}

	public void setRecords(List<Pointrecord> records) {
		this.records = records;
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

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public int getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(int dealStatus) {
		this.dealStatus = dealStatus;
	}

	public int getComStatus() {
		return comStatus;
	}

	public void setComStatus(int comStatus) {
		this.comStatus = comStatus;
	}

	public Airlinecompany getCompany() {
		return company;
	}

	public void setCompany(Airlinecompany company) {
		this.company = company;
	}

	public List<Airlinecompany> getCompanys() {
		return companys;
	}

	public void setCompanys(List<Airlinecompany> companys) {
		this.companys = companys;
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
