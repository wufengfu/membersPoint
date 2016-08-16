package com.tianxiafen.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.entity.Airlinecompany;
import com.tianxiafen.entity.Formula;
import com.tianxiafen.entity.Pointremit;
import com.tianxiafen.service.IAirlineCompanyService;
import com.tianxiafen.service.IPointAbouchemenetService;
import com.tianxiafen.service.IPointRemitService;
import com.tianxiafen.service.impl.AirlineCompanyServiceImpl;
import com.tianxiafen.service.impl.PointAbouchemenetServiceImpl;
import com.tianxiafen.service.impl.PointRemitServiceImpl;

@SuppressWarnings("serial")
public class PointRemitApplyAction extends ActionSupport {

	Map<String,Object> session = ActionContext.getContext().getSession();
	private List<Pointremit> pointRemitList;
	private Pointremit pointRemit;
	private int userId;
	private int companyId;
	private Formula formula;
	private Airlinecompany company;
	private List<Airlinecompany> companys;
	private String remitName;
	private String serialNum;
	private String cardNum;
	private Date startTime;
	private Date endTime;
	private int statues = -2;//代表所有状态
	private String message;
	
	//分页
			private int pageIndex = 1;
			private int pageSize = 5;
			private int itemCount;
			private int pageCount;
	
	public String pointRemitList(){
		if(session.get("manager")==null)
			return LOGIN;
		try {
			IAirlineCompanyService comService = new AirlineCompanyServiceImpl();
			companys = comService.getAllCompany();
			company = comService.getCompanyById(companyId);
			IPointRemitService service = new PointRemitServiceImpl();
			pointRemitList = new ArrayList<Pointremit>();
			pointRemitList = service.getPointRemit(remitName,serialNum,cardNum,companyId,startTime,endTime,statues,pageIndex,pageSize);
			itemCount = service.getCount();
			pageCount = (int) (Math.ceil((double)itemCount/pageSize));
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	public String passPointRemit(){
		IPointRemitService service = new PointRemitServiceImpl();
		pointRemit.setStatues(1);//已汇入
		service.updatePointRemitStatues(pointRemit);
		return SUCCESS;
	}
	public String effectPointRemit(){
		IPointRemitService service = new PointRemitServiceImpl();
		pointRemit.setStatues(2);//已生效
		service.updatePointRemitStatues(pointRemit);
		return SUCCESS;
	}
	public String backPointRemit(){
		IPointRemitService service = new PointRemitServiceImpl();
		pointRemit.setStatues(-1);//驳回（已删除）
		service.updatePointRemitStatues(pointRemit);
		return SUCCESS;
	}

	public List<Pointremit> getPointRemitList() {
		return pointRemitList;
	}

	public void setPointRemitList(List<Pointremit> pointRemitList) {
		this.pointRemitList = pointRemitList;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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

	public int getStatues() {
		return statues;
	}

	public void setStatues(int statues) {
		this.statues = statues;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Pointremit getPointRemit() {
		return pointRemit;
	}
	public void setPointRemit(Pointremit pointRemit) {
		this.pointRemit = pointRemit;
	}
	public Formula getFormula() {
		return formula;
	}
	public void setFormula(Formula formula) {
		this.formula = formula;
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
	public String getRemitName() {
		return remitName;
	}
	public void setRemitName(String remitName) {
		this.remitName = remitName;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
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
