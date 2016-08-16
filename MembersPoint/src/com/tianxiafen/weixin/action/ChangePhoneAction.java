package com.tianxiafen.weixin.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.cons.Constant;
import com.tianxiafen.entity.CommonKeyValue;
import com.tianxiafen.entity.Token;
import com.tianxiafen.entity.User;
import com.tianxiafen.service.IMessageVerifyService;
import com.tianxiafen.service.IUserService;
import com.tianxiafen.service.UtilService;
import com.tianxiafen.service.impl.MessageVerifyServiceImpl;
import com.tianxiafen.service.impl.UserServiceImpl;
import com.tianxiafen.service.impl.UtilServiceImpl;
import com.tianxiafen.util.WeixinUserUtil;

public class ChangePhoneAction extends ActionSupport {

	private String oldPhoneNum;
	private String newPhoneNum;
	private String vertifyCode;
	private String realVerifyCode = "123";
	private User user;
	private String message;
	
	private String code;
	private String state;
	
	Map<String, Object> session = ActionContext.getContext().getSession();
	public String verifyOldPhone(){
		testLogin();
		if(session.get("user")==null){
			UtilService service = new UtilServiceImpl();
			service.getOpenId(Constant.APPID,"https://124.65.180.14:8080/MembersPoint/verifyLogin.action",
					"fentianxia");
			return "wait";
		}
		oldPhoneNum = user.getPhoneNum();
		return SUCCESS;
	}
	public String verifyCode(){
		realVerifyCode = session.get("verifyCode").toString();
		user = (User) session.get("user");
		if(oldPhoneNum.equals(user.getPhoneNum())){
			if(vertifyCode.equals(realVerifyCode)){
				return SUCCESS;
			}else{
				message = "验证码输入错误！";
			}
		}else{
			message = "程序异常，请重新操作！";
		}
		return ERROR;
	}
	public String verifyNewPhone(){
		realVerifyCode = session.get("verifyCode").toString();
		user = (User) session.get("user");
			if(vertifyCode.equals(realVerifyCode)){
				IUserService service = new UserServiceImpl();
				boolean result = service.changePhone(user.getId(),newPhoneNum);
				if(result){
					message = "更改成功！";
					return SUCCESS;
				}
				else{
					message = "程序异常，请重新操作！";
					return ERROR;
				}
			}else{
				message = "验证码输入错误！";
			}
		return ERROR;
	}
	public String verifyLogin(){
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
			oldPhoneNum = user.getPhoneNum();
		}else{
			return ERROR;
		}
		return SUCCESS;
	}
//	public String sendVertify(){
//		user = (User) session.get("user");
//		IMessageVerifyService service = new MessageVerifyServiceImpl();
//		CommonKeyValue commonEntity= service.getMessageContent();
////		boolean result = service.sendMssage(user,phoneNum, commonEntity.getValue());
//		boolean result = true;
//		System.out.println("假装发送了"+commonEntity.getKey());
//		if(result){
//			session.put("verifyCode", commonEntity.getKey());
//			verifyResult = "success";
//			return SUCCESS;
//		}else{
//			verifyResult = "fail";
//			return "false";
//		}
//	}
	public void testLogin(){
		IUserService service = new UserServiceImpl();
		user = service.login("wxwufengfu");
		session.put("user", user);
		session.put("userId", user.getId());
	}
	public String getOldPhoneNum() {
		return oldPhoneNum;
	}
	public void setOldPhoneNum(String oldPhoneNum) {
		this.oldPhoneNum = oldPhoneNum;
	}
	public String getNewPhoneNum() {
		return newPhoneNum;
	}
	public void setNewPhoneNum(String newPhoneNum) {
		this.newPhoneNum = newPhoneNum;
	}
	public String getVertifyCode() {
		return vertifyCode;
	}
	public void setVertifyCode(String vertifyCode) {
		this.vertifyCode = vertifyCode;
	}
	public String getRealVerifyCode() {
		return realVerifyCode;
	}
	public void setRealVerifyCode(String realVerifyCode) {
		this.realVerifyCode = realVerifyCode;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	
	
}
