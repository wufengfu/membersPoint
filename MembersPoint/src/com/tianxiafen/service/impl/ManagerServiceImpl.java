package com.tianxiafen.service.impl;

import com.tianxiafen.dao.IManagerDao;
import com.tianxiafen.dao.impl.ManagerDaoImpl;
import com.tianxiafen.entity.Manager;
import com.tianxiafen.service.IManagerService;
import com.tianxiafen.util.MD5Util;

public class ManagerServiceImpl implements IManagerService {

	@Override
	public Manager login(String userName,String password) throws Exception {
		IManagerDao dao = new ManagerDaoImpl();
		return dao.login(userName,MD5Util.md5Encode(password));
	}

}
