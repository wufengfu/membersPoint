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

@SuppressWarnings("serial")
public class UserAction extends ActionSupport {

	private User user;
	Map<String, Object> session = ActionContext.getContext().getSession();
	private String code;
	private String state;
	
	private String oldPhoneNum;//旧的手机号码（比对）
	private String oldTransPass;//旧的交易密码（比对）
	private String vertifyCode;//手机验证码（优先级要高于交易密码验证码）
	private String passVerifyCode;//交易密码验证码
	private String realVerifyCode;
	
	private String phoneNum;//目的手机号码（发送验证码）
	private String verifyResult;

	/**
	 * 微信用户自动登录以及注册
	 * 
	 * @return
	 */
	public String verifyUser() {
		UtilService service = new UtilServiceImpl();
		service.getOpenId(Constant.APPID,"https://124.65.180.14:8080/MembersPoint/getUserToken.action",
				"fentianxia");
		return SUCCESS;
	}

	public String getUserToken() {
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
			autoLogin();
		}

		return SUCCESS;
	}

	/**
	 * 微信用户自动登陆
	 * 
	 * @return
	 */
	public String autoLogin() {
		// TODO 微信用户信息获取验证
		// TODO 放入session中的应该是用户自动登陆之后获取的userId
		user.setId(1);
		WeixinUserUtil.autoLogin(session, user, user.getId());
		return SUCCESS;
	}
	
	public String testGetUserInfo(){
		IUserService service = new UserServiceImpl();
		user = service.login("wxwufengfu");
		System.out.println(user.toString());
		session.put("user", user);
		session.put("userId", user.getId());
		return SUCCESS;
		
	}

	/**
	 * 完善信息
	 * 
	 * @return
	 */
	public String perfectInfo() {
		if(session.get("user")==null){
			System.out.println("93");
			return LOGIN;
		}
		if(session.get("verifyCode") == null){
			//没有发送验证码，或者验证码发送失败等。。。
			return LOGIN;
		}else{
			realVerifyCode = session.get("verifyCode").toString();
		}
		if(!user.getPhoneNum().equals(oldPhoneNum)){//新旧手机号码不同
			if(vertifyCode==null || "".equals(vertifyCode)){//验证码为空
				System.out.println("98");
				return LOGIN;
			}else{
				if(!realVerifyCode.equals(vertifyCode)){//验证码不同
					System.out.println("102");
					return LOGIN;
				}
			}
		}else{
			if(!user.getTransactionPassword().equals(oldTransPass)){
				if(passVerifyCode==null || "".equals(passVerifyCode)){
					//交易验证码为空，并且新旧值不同
					System.out.println("110");
					return LOGIN;
				}else{
					if(!realVerifyCode.equals(passVerifyCode)){
						//交易验证码不同，并且新旧值不同
						System.out.println("115");
						return LOGIN;
					}
				}
			}
		}
		user.setId((Integer) session.get("userId"));
		IUserService service = new UserServiceImpl();
		user = service.update(user);
		session.put("user", user);
		return SUCCESS;
	}
	
	public String sendVertify(){
		user = (User) session.get("user");
		IMessageVerifyService service = new MessageVerifyServiceImpl();
		CommonKeyValue commonEntity= service.getMessageContent();
		boolean result = service.sendMssage(user,phoneNum, commonEntity.getValue());
//		boolean result = true;
//		System.out.println("假装发送了"+commonEntity.getKey());
		if(result){
			session.put("verifyCode", commonEntity.getKey());
			verifyResult = "success";
			return SUCCESS;
		}else{
			verifyResult = "fail";
			return "false";
		}
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

	public String getOldPhoneNum() {
		return oldPhoneNum;
	}

	public void setOldPhoneNum(String oldPhoneNum) {
		this.oldPhoneNum = oldPhoneNum;
	}

	public String getOldTransPass() {
		return oldTransPass;
	}

	public void setOldTransPass(String oldTransPass) {
		this.oldTransPass = oldTransPass;
	}

	public String getVertifyCode() {
		return vertifyCode;
	}

	public void setVertifyCode(String vertifyCode) {
		this.vertifyCode = vertifyCode;
	}

	public String getPassVerifyCode() {
		return passVerifyCode;
	}

	public void setPassVerifyCode(String passVerifyCode) {
		this.passVerifyCode = passVerifyCode;
	}

	public String getRealVerifyCode() {
		return realVerifyCode;
	}

	public void setRealVerifyCode(String realVerifyCode) {
		this.realVerifyCode = realVerifyCode;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getVerifyResult() {
		return verifyResult;
	}

	public void setVerifyResult(String verifyResult) {
		this.verifyResult = verifyResult;
	}

}
