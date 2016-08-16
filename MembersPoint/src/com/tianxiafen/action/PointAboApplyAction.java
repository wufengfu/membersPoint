package com.tianxiafen.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.entity.Pointabouchemenet;
import com.tianxiafen.service.IPointAbouchemenetService;
import com.tianxiafen.service.impl.PointAbouchemenetServiceImpl;

@SuppressWarnings("serial")
public class PointAboApplyAction extends ActionSupport {

	Map<String,Object> session = ActionContext.getContext().getSession();
	private List<Pointabouchemenet> pointAboList;
	private int userId;
	private String fullName;
	private String serialNum;
	private String postscript;
	private Date startTime;
	private Date endTime;
	private int statues = -2;//代表所有状态
	private String message;
	private Pointabouchemenet pointAbo;
	
	//分页
		private int pageIndex = 1;
		private int pageSize = 5;
		private int itemCount;
		private int pageCount;
	
	public String pointAboList(){
		if(session.get("manager")==null)
			return LOGIN;
		try {
			IPointAbouchemenetService service = new PointAbouchemenetServiceImpl();
			pointAboList = new ArrayList<Pointabouchemenet>();
			pointAboList = service.getPointAboList(fullName,serialNum,postscript, startTime, endTime, statues,pageIndex,pageSize);
			itemCount = service.getCount();
			pageCount = (int) (Math.ceil((double)itemCount/pageSize));
		} catch (Exception e) {
			e.printStackTrace();
			message = "程序异常获取失败";
			return ERROR;
		}
		return SUCCESS;
	}
	public String passPointAbo(){
		IPointAbouchemenetService service = new PointAbouchemenetServiceImpl();
		pointAbo.setStatues(1);
		service.updateStatuesPointAbo(pointAbo);
		return SUCCESS;
	}
	public String backPointAbo(){
		IPointAbouchemenetService service = new PointAbouchemenetServiceImpl();
		pointAbo.setStatues(-1);//驳回（已删除）
		service.updateStatuesPointAbo(pointAbo);
		return SUCCESS;
	}

	public List<Pointabouchemenet> getPointAboList() {
		return pointAboList;
	}

	public void setPointAboList(List<Pointabouchemenet> pointAboList) {
		this.pointAboList = pointAboList;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
	public Pointabouchemenet getPointAbo() {
		return pointAbo;
	}
	public void setPointAbo(Pointabouchemenet pointAbo) {
		this.pointAbo = pointAbo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getPostscript() {
		return postscript;
	}
	public void setPostscript(String postscript) {
		this.postscript = postscript;
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
