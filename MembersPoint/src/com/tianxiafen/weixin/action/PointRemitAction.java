package com.tianxiafen.weixin.action;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.cons.Constant;
import com.tianxiafen.entity.Airlinecompany;
import com.tianxiafen.entity.Formula;
import com.tianxiafen.entity.Pointremit;
import com.tianxiafen.entity.Token;
import com.tianxiafen.entity.User;
import com.tianxiafen.service.IAirlineCompanyService;
import com.tianxiafen.service.IFormulaService;
import com.tianxiafen.service.IPointRemitService;
import com.tianxiafen.service.IUserService;
import com.tianxiafen.service.UtilService;
import com.tianxiafen.service.impl.AirlineCompanyServiceImpl;
import com.tianxiafen.service.impl.FormulaServiceImpl;
import com.tianxiafen.service.impl.PointRemitServiceImpl;
import com.tianxiafen.service.impl.UserServiceImpl;
import com.tianxiafen.service.impl.UtilServiceImpl;
import com.tianxiafen.util.CommonUtil;
import com.tianxiafen.util.WeixinUserUtil;

public class PointRemitAction extends ActionSupport {
	
	Map<String,Object> session = ActionContext.getContext().getSession();
	private Pointremit pointRemit;
	private Airlinecompany company;
	private List<Formula> formulas;
	private int mileage;
	private int point;
	private int flag;//标记来自哪个页面（1、国航、2、南航、3、东航等）
	private User user;
	private String code;
	private String state;
	private String message;
	
	public String addPointRemit(){
		//设置用户、航司、流水号、添加时间、状态等默认值
		pointRemit.setUser((User) session.get("user"));
		pointRemit.setAirlinecompany(company);
		pointRemit.setSerialNum(CommonUtil.getServialNum(1));
		pointRemit.setAddTime(new Timestamp(System.currentTimeMillis()));
		pointRemit.setStatues(0);
		IPointRemitService service = new PointRemitServiceImpl();
		try {
			pointRemit = service.addPointRemit(pointRemit);
		} catch (Exception e) {
			e.printStackTrace();
			message = "程序异常请重新添加！";
			return ERROR;
		}
		if(pointRemit.getId()==0 || pointRemit.getId()==null){
			message = "程序异常请重新添加！";
			return ERROR;
		}else{
			message = "添加成功，请等待管理人员审核！";
			return SUCCESS;
		}
		
		
	}
	public String pointRemit(){
		testLogin();
		if(session.get("user")==null){
			UtilService service = new UtilServiceImpl();
			service.getOpenId(Constant.APPID,"https://124.65.180.14:8080/MembersPoint/pointRemitLogin.action",
					"fentianxia");
			return "wait";
		}
		IAirlineCompanyService service = new AirlineCompanyServiceImpl();
		switch(flag){
			case 1://国航
				company = service.getCompanyByName("国航");
				break;
			case 2:
				company = service.getCompanyByName("南航");
				break;
			case 3:
				company = service.getCompanyByName("东航");
				break;
			case 4:
				company = service.getCompanyByName("亚万");
				break;
			case 5:
				company = service.getCompanyByName("亚航");
				break;
				
		}
		System.out.println(company.toString());
		return SUCCESS;
	}
	public String pointRemitLogin(){
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
		return pointRemit();
	}
	public String getPointsByMileage(){
		point = CommonUtil.getPointByMilage(company.getId(),mileage);
		return SUCCESS;
	}
	public void testLogin(){
		IUserService service = new UserServiceImpl();
		user = service.login("wxwufengfu");
		session.put("user", user);
		session.put("userId", user.getId());
	}
	
	public Airlinecompany getCompany() {
		return company;
	}
	public void setCompany(Airlinecompany company) {
		this.company = company;
	}
	public Pointremit getPointRemit() {
		return pointRemit;
	}
	public void setPointRemit(Pointremit pointRemit) {
		this.pointRemit = pointRemit;
	}
	public List<Formula> getFormulas() {
		return formulas;
	}
	public void setFormulas(List<Formula> formulas) {
		this.formulas = formulas;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
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
