package com.tianxiafen.service;

import com.tianxiafen.entity.CommonKeyValue;
import com.tianxiafen.entity.User;

public interface IMessageVerifyService {

	/**
	 * 发送验证短信
	 * @param phoneNum 目的手机号码
	 * @param content 发送内容
	 * @return 是否发送成功
	 */
	public boolean sendMssage(User user,String phoneNum,String content);
	
	/**
	 * 获取账户余额
	 * @return
	 */
	public String getBalance();
	/**
	 * 获取状态列表
	 * @return
	 */
	public String getReport();
	
	/**
	 * 获取验证短信
	 * @return Map<验证码,短信内容>
	 */
	public CommonKeyValue getMessageContent();
}
