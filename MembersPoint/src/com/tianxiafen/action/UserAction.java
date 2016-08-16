package com.tianxiafen.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.entity.User;
import com.tianxiafen.service.IUserService;
import com.tianxiafen.service.impl.UserServiceImpl;

public class UserAction extends ActionSupport {

	Map<String,Object> session = ActionContext.getContext().getSession();
	private int statues = -2;
	private List<User> users;
	private User user;
	private String fullName;
	private String phoneNum;
	//分页
	private int pageIndex = 1;
	private int pageSize = 15;
	private int itemCount;
	private int pageCount;
	
	public String userList(){
		if(session.get("manager")==null)
			return LOGIN;
		try {
			if("真实姓名".equals(fullName)){
				fullName=null;
			}
			if("电话号码".equals(phoneNum)){
				phoneNum =null;
			}
			IUserService service = new UserServiceImpl();
			users = service.getUserList(statues,fullName,phoneNum,pageIndex,pageSize);
			itemCount = service.getCount();
			pageCount = (int) (Math.ceil((double)itemCount/pageSize));
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	public String passVerify(){
		IUserService service = new UserServiceImpl();
		user.setStatues(1);//更改权限状态为普通权限
		service.updateUserStatues(user);
		return SUCCESS;
	}
	public String backVerify(){
		IUserService service = new UserServiceImpl();
		user.setStatues(-1);//更改权限状态为拉黑
		service.updateUserStatues(user);
		return SUCCESS;
	}
	public String favUser(){
		IUserService service = new UserServiceImpl();
		user.setStatues(2);//更改权限状态为优惠用户
		service.updateUserStatues(user);
		return SUCCESS;
	}
	public int getStatues() {
		return statues;
	}
	public void setStatues(int statues) {
		this.statues = statues;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
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
