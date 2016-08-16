package com.tianxiafen.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.entity.Airlinecompany;
import com.tianxiafen.service.IAirlineCompanyService;
import com.tianxiafen.service.impl.AirlineCompanyServiceImpl;

public class AirCompanyAction extends ActionSupport {
	
	Map<String,Object> session = ActionContext.getContext().getSession();
	private List<Airlinecompany> companys;
	private Airlinecompany company;
	
	private int pageIndex = 1;
	private int pageSize = 5;
	private int itemCount;
	private int pageCount;
	
	public String getCompanyList(){
		if(session.get("manager")==null)
			return LOGIN;
		try {
			IAirlineCompanyService service = new AirlineCompanyServiceImpl();
			companys = service.getAllCompany(pageIndex,pageSize);
			itemCount = service.getCompanyCount();
			pageCount = (int) (Math.ceil((double)itemCount/pageSize));
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS;
	}
	public String addCompany(){
		IAirlineCompanyService service = new AirlineCompanyServiceImpl();
		company = service.addCompany(company);
		if(company.getId()!=null && company.getId()!=0)
			return SUCCESS;
		return ERROR;
	}
	public String updateCompany(){
		IAirlineCompanyService service = new AirlineCompanyServiceImpl();
		boolean result = service.updateCompany(company);
		if(result)
			return SUCCESS;
		return ERROR;
		
	}
	public List<Airlinecompany> getCompanys() {
		return companys;
	}
	public void setCompanys(List<Airlinecompany> companys) {
		this.companys = companys;
	}
	public Airlinecompany getCompany() {
		return company;
	}
	public void setCompany(Airlinecompany company) {
		this.company = company;
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
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
}
