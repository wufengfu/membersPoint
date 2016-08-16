package com.tianxiafen.service;

import com.tianxiafen.entity.Manager;

public interface IManagerService {

	/**
	 * 后台管理员登录
	 * @param userName 用户名
	 * @param password 密码
	 * @return
	 * @throws Exception 
	 */
	public Manager login(String userName, String password) throws Exception;
}
