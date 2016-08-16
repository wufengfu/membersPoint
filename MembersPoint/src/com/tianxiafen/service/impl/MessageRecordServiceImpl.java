package com.tianxiafen.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tianxiafen.dao.IMessageRecordDao;
import com.tianxiafen.dao.impl.MessageRecordDaoImpl;
import com.tianxiafen.entity.Messagerecord;
import com.tianxiafen.service.IMessageRecordService;
import com.tianxiafen.util.HibernateUtil;

public class MessageRecordServiceImpl implements IMessageRecordService {

	IMessageRecordDao dao = new MessageRecordDaoImpl();
	@Override
	public boolean addMessageRecord(Messagerecord record) {
		return dao.addMessageRecord(record);
	}

	@Override
	public List<Messagerecord> getMessageRecord(String phoneNum, Date startTime,
			Date endTime,int pageIndex,int pageSize) {
		return dao.getMessageRecord(phoneNum, startTime, endTime,pageIndex,pageSize);
	}

	@Override
	public int getMessageRecordCount(Date startTime, Date endTime) {
		return dao.getMessageRecordCount(startTime, endTime);
	}

	@Override
	public int getCount() {
		return dao.getCount();
	}

}
