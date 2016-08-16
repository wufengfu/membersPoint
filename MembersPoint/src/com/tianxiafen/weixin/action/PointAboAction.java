package com.tianxiafen.weixin.action;

import java.sql.Timestamp;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.cons.Constant;
import com.tianxiafen.entity.Pointabouchemenet;
import com.tianxiafen.entity.Token;
import com.tianxiafen.entity.User;
import com.tianxiafen.service.IPointAbouchemenetService;
import com.tianxiafen.service.IUserService;
import com.tianxiafen.service.UtilService;
import com.tianxiafen.service.impl.PointAbouchemenetServiceImpl;
import com.tianxiafen.service.impl.UserServiceImpl;
import com.tianxiafen.service.impl.UtilServiceImpl;
import com.tianxiafen.util.CommonUtil;
import com.tianxiafen.util.WeixinUserUtil;

@SuppressWarnings("serial")
public class PointAboAction extends ActionSupport {
	private User user;
	private String code;
	private String state;
	private String message;
	Map<String,Object> session = ActionContext.getContext().getSession();
	private Pointabouchemenet pointAbo;
	
	public String pointAbouchemenet(){
		testLogin();
		if(session.get("user")==null){
			UtilService service = new UtilServiceImpl();
			service.getOpenId(Constant.APPID,"https://124.65.180.14:8080/MembersPoint/pointLogin.action",
					"fentianxia");
			return "wait";
		}
		pointAbo.setUser((User) session.get("user"));
		pointAbo.setAddTime(new Timestamp(System.currentTimeMillis()));
		IPointAbouchemenetService service = new PointAbouchemenetServiceImpl();
		pointAbo.setStatues(0);//设置默认状态
		pointAbo.setSerialNum(CommonUtil.getServialNum(0));//流水号
		boolean result = service.addPointAbo(pointAbo);
		if(result){
			message = "申请成功，请等待管理员审核！";
		}else{
			message = "申请失败，请重新操作！";
		}
		return SUCCESS;
	}
	public String pointLogin(){
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

	public Pointabouchemenet getPointAbo() {
		return pointAbo;
	}

	public void setPointAbo(Pointabouchemenet pointAbo) {
		this.pointAbo = pointAbo;
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
	
	
}
