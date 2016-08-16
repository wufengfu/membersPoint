package com.tianxiafen.service.impl;

import java.util.Date;
import java.util.List;

import com.tianxiafen.dao.IPointRecordDao;
import com.tianxiafen.dao.impl.PointRecordDaoImpl;
import com.tianxiafen.entity.Pointrecord;
import com.tianxiafen.service.IPointRecordService;

public class PointRecordServiceImpl implements IPointRecordService {

	IPointRecordDao dao = new PointRecordDaoImpl();
	@Override
	public boolean addRecord(Pointrecord record) {
		
		return dao.addPointRecord(record);
	}
	@Override
	public List<Pointrecord> getRecords(int companyId,String userName, String serialNum,
			int dealStatus, int dealType, int comStatus, Date startTime,
			Date endTime,int pageIndex,int pageSize) {
		return dao.getRecords(companyId,userName,serialNum,dealStatus, dealType,comStatus, startTime, endTime,pageIndex,pageSize);
	}
	@Override
	public List<Pointrecord> getRecords(Integer userId, Date startTime,
			Date endTime, int dealType) {
		return dao.getRecords(userId,startTime,endTime,dealType);
	}
	@Override
	public int getCount() {
		return dao.getCount();
	}

}
