package com.tianxiafen.service.impl;

import java.util.Date;
import java.util.List;

import com.tianxiafen.dao.IPointAbouchemenetDao;
import com.tianxiafen.dao.impl.PointAbouchemenetDaoImpl;
import com.tianxiafen.entity.Pointabouchemenet;
import com.tianxiafen.service.IPointAbouchemenetService;
import com.tianxiafen.util.CommonUtil;

public class PointAbouchemenetServiceImpl implements IPointAbouchemenetService {

	IPointAbouchemenetDao dao = new PointAbouchemenetDaoImpl();
	@Override
	public List<Pointabouchemenet> getPointAboList(String userName,String serialNum,String postscript, Date startTime,
			Date endTime,int statues,int pageIndex,int pageSize) {
		return dao.getPointAboList(userName,serialNum,postscript, startTime, endTime,statues,pageIndex,pageSize);
	}

	@Override
	public boolean addPointAbo(Pointabouchemenet pointAbo) {
		return dao.addPointAbo(pointAbo);
	}

	@Override
	public boolean updateStatuesPointAbo(Pointabouchemenet pointAbo) {
		
		return dao.updateStatuesPointAbo(pointAbo);
	}

	@Override
	public int getMaxId() {
		return dao.getMaxId();
	}

	@Override
	public int getCount() {
		return dao.getCount();
	}

}
