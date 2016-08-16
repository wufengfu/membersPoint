package com.tianxiafen.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.tianxiafen.entity.CommonKeyValue;
import com.tianxiafen.entity.User;
import com.tianxiafen.service.IAirlineCompanyService;
import com.tianxiafen.service.IMessageVerifyService;
import com.tianxiafen.service.impl.AirlineCompanyServiceImpl;
import com.tianxiafen.service.impl.MessageVerifyServiceImpl;
import com.tianxiafen.util.CommonUtil;
import com.tianxiafen.util.WeixinUserUtil;

public class UtilTest {

	public void testGetUserInfo(){
		// 获取接口访问凭证
	    String accessToken = WeixinUserUtil.getToken("wxfe37491279720a57", "dcace3fe7e0d05566827fdf8448ed908").getAccessToken();
	    /**
	     * 获取用户信息
	     */
	    User user = WeixinUserUtil.getUserInfo(accessToken, "ooK-yuJvd9gEegH6nRIen-gnLrVw");
	    System.out.println("OpenID：" + user.getOpenId());
	    System.out.println("关注状态：" + user.getSubscribe());
	    System.out.println("关注时间：" + user.getSubScribeTime());
	    System.out.println("昵称：" + user.getNickname());
	    System.out.println("性别：" + user.getSex());
	    System.out.println("国家：" + user.getCountry());
	    System.out.println("省份：" + user.getProvince());
	    System.out.println("城市：" + user.getCity());
	    System.out.println("语言：" + user.getLanguage());
	    System.out.println("头像：" + user.getHeadImageUrl());
	}
	public void testGetServialNum(){
		System.out.println(CommonUtil.getServialNum(0));
	}
	public void testGetRecord(){
//		IPointRecordService service = new PointRecordServiceImpl();
//		List<Pointrecord> records = service.getRecords(1, null, null, 2);
//		for(Pointrecord record : records){
//			System.out.println(record.toString());
//		}
		
	}
	public void testGetPointByMileage(){
		System.out.println("points is :"+ CommonUtil.getPointByMilage(1, 1500));
	}
	public void testMessageCode(){
		IMessageVerifyService service = new MessageVerifyServiceImpl();
		CommonKeyValue commonEntity= service.getMessageContent();
		User user = new User();
		user.setId(1);
		boolean result = service.sendMssage(user,"18612720895", commonEntity.getValue());
		System.out.println("execute result is:"+result);
		
	}
	public void getCount(){
		IAirlineCompanyService service = new AirlineCompanyServiceImpl();
		System.out.println(service.getCompanyCount());
		
	}
	@Test
	public void testgetCount(){
		int count = (int) (Math.ceil((double)6/5));
		System.out.println(count);
	}
	@Test
	public void testNormal(){
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
		System.out.println(format.format(new Date()));
		
	}
	
}
