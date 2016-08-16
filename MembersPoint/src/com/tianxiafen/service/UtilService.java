package com.tianxiafen.service;

public interface UtilService {

	/**
	 * 通过输入的金钱数获得计分数
	 * @return 积分数
	 */
	public String getPointByMoney();
	/**
	 * 获取微信用户的openId
	 * @param appid 公众号的唯一标识
	 * @param redirect_uri 授权后重定向的回调链接地址，请使用urlencode对链接进行处理
	 * @param response_type 返回类型，请填写code
	 * @param scope 应用授权作用域，snsapi_base （只能获取用户openid），snsapi_userinfo
	 * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
	 * @return
	 */
	public void getOpenId(String appid,String redirect_uri,String state);
}
