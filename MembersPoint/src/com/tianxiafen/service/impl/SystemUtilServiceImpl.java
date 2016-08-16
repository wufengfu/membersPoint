package com.tianxiafen.service.impl;

import java.util.List;

import com.tianxiafen.dao.ISystemUtilDao;
import com.tianxiafen.dao.impl.SystemUtilDaoImpl;
import com.tianxiafen.entity.Systemutil;
import com.tianxiafen.service.ISystemUtilService;

public class SystemUtilServiceImpl implements ISystemUtilService {

	ISystemUtilDao dao = new SystemUtilDaoImpl();
	@Override
	public List<Systemutil> getAllUtil(int pageIndex,int pageSize) {
		return dao.getAllUtil(pageIndex,pageSize);
	}

	@Override
	public Systemutil addSystemUtil(Systemutil util) {
		return dao.addSystemUtil(util);
	}

	@Override
	public boolean deleteSystemUtil(int utilId) {
		return dao.deleteSystemUtil(utilId);
	}

	@Override
	public Systemutil getContent(String name) {
		return dao.getContent(name);
	}

	@Override
	public boolean updateSystemUtil(Systemutil util) {
		return dao.updateSystemUtil(util);
	}

	@Override
	public List<Systemutil> getContents(String name) {
		return dao.getContents(name);
	}

	@Override
	public int getCount() {
		return dao.getCount();
	}

}
