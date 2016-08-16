package com.tianxiafen.weixin.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.cons.Constant;
import com.tianxiafen.entity.Pointrecord;
import com.tianxiafen.entity.Token;
import com.tianxiafen.entity.User;
import com.tianxiafen.service.IPointRecordService;
import com.tianxiafen.service.IUserService;
import com.tianxiafen.service.UtilService;
import com.tianxiafen.service.impl.PointRecordServiceImpl;
import com.tianxiafen.service.impl.UserServiceImpl;
import com.tianxiafen.service.impl.UtilServiceImpl;
import com.tianxiafen.util.CommonUtil;
import com.tianxiafen.util.WeixinUserUtil;

public class PointRecordAction extends ActionSupport {

	Map<String,Object> session = ActionContext.getContext().getSession();
	private User user;
	private String code;
	private String state;
	private String message;
	private String startTime;
	private String endTime;
	private int dealType;
	
	private List<Pointrecord> records;
	
	public String pointRecordList(){
		testLogin();
		if(session.get("user")==null){
			UtilService service = new UtilServiceImpl();
			service.getOpenId(Constant.APPID,"https://124.65.180.14:8080/MembersPoint/recordLogin.action",
					"fentianxia");
			return "wait";
		}
		IPointRecordService service = new PointRecordServiceImpl();
		records = service.getRecords((Integer)session.get("userId"), CommonUtil.formatStringToDate(startTime), CommonUtil.formatStringToDate(endTime), dealType);
		return SUCCESS;
		
	}
	public String recordLogin(){
		Token token = WeixinUserUtil.getUserToken(code, Constant.APPID,
				Constant.APPSECRET);

		if (token != null) {
			IUserService service = new UserServiceImpl();
			String openId = token.getOpenId();
			user = service.login(openId);
			if (user == null) {
				User weixinUser = WeixinUserUtil.getUserInfo(token.getAccessToken(), token.getOpenId());
				if (user != null) {
					user = service.register(weixinUser);
				}
			}
			//自动登陆状态
			WeixinUserUtil.autoLogin(session,user,user.getId());
		}
		//TODO 页面验证 message不为空时，提示message中的信息。
		message = "自动登陆成功，请重新填写申请！";
		return SUCCESS;
	}
	public void testLogin(){
		IUserService service = new UserServiceImpl();
		user = service.login("wxwufengfu");
		session.put("user", user);
		session.put("userId", user.getId());
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getDealType() {
		return dealType;
	}
	public void setDealType(int dealType) {
		this.dealType = dealType;
	}
	public List<Pointrecord> getRecords() {
		return records;
	}
	public void setRecords(List<Pointrecord> records) {
		this.records = records;
	}
	
	
}
