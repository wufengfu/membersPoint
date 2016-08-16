package com.tianxiafen.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;

import jjxt.sms.SMS;
import jjxt.sms.SendResult;

import com.tianxiafen.cons.Constant;
import com.tianxiafen.entity.CommonKeyValue;
import com.tianxiafen.entity.Messagerecord;
import com.tianxiafen.entity.Systemutil;
import com.tianxiafen.entity.User;
import com.tianxiafen.service.IMessageRecordService;
import com.tianxiafen.service.IMessageVerifyService;
import com.tianxiafen.service.ISystemUtilService;
import com.tianxiafen.util.CommonUtil;

public class MessageVerifyServiceImpl implements IMessageVerifyService {

	ISystemUtilService service = new SystemUtilServiceImpl();
	Systemutil accountUtil = service.getContent(Constant.ACCOUNT);
	Systemutil passWordtUtil = service.getContent(Constant.PASSWORD);
	String account = accountUtil==null?"":accountUtil.getContent();
	String password = passWordtUtil==null?"":passWordtUtil.getContent();
	@Override
	public boolean sendMssage(User user,String phoneNum, String content) {
		try {
			SendResult result = new SendResult();
			String urlContent = URLEncoder.encode(content,"GBK");
			result = SMS.send(account, password, phoneNum, urlContent, null, null);
			if(result.is_err()){
				IMessageRecordService service = new MessageRecordServiceImpl();
				Messagerecord record = new Messagerecord();
				record.setUser(user);
				record.setPhoneNum(phoneNum);
				record.setContent(content);
				record.setAddTime(new Timestamp(System.currentTimeMillis()));
//				record.setExt(null);
//				record.setReference(null);
				service.addMessageRecord(record);
				return true;
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getBalance() {
		SendResult result = new SendResult();
		try {
			result = SMS.balance(account, password);
			if(!result.is_err()){
				return result.get_message();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "error";
	}

	@Override
	public String getReport() {
		SendResult result = new SendResult();
		try {
			result = SMS.report(account, password);
			if(!result.is_err()){
				return result.get_message();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "error";
	}

	@Override
	public CommonKeyValue getMessageContent() {
		CommonKeyValue commonEntity = new CommonKeyValue();
		String code = CommonUtil.getVerifyCode();
		String content = "【天下分】验证码："+code+"。请不要把验证码泄露给其他人，如非本人操作，可不用理会";
		commonEntity.setKey(code);
		commonEntity.setValue(content);
		return commonEntity;
	}

}
