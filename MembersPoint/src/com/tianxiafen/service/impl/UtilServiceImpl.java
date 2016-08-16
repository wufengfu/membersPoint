package com.tianxiafen.service.impl;

import com.tianxiafen.service.UtilService;
import com.tianxiafen.util.WeixinUserUtil;

public class UtilServiceImpl implements UtilService {

	@Override
	public String getPointByMoney() {
		// TODO 通过输入的金钱数获得计分数
		return null;
	}

	@Override
	public void getOpenId(String appid, String redirect_uri,String state) {
		WeixinUserUtil.getUserCode(appid, redirect_uri, state);
	}

}
