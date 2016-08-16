package com.tianxiafen.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tianxiafen.dao.IMessageRecordDao;
import com.tianxiafen.entity.Airlinecompany;
import com.tianxiafen.entity.Messagerecord;
import com.tianxiafen.util.HibernateUtil;

public class MessageRecordDaoImpl implements IMessageRecordDao {

	@Override
	public boolean addMessageRecord(Messagerecord record) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(record);
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Messagerecord> getMessageRecord(String phoneNum, Date startTime,
			Date endTime,int pageIndex,int pageSize) {
		List<Messagerecord> records = null;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria recordCri = session.createCriteria(Messagerecord.class);
			if(phoneNum!=null && !"".equals(phoneNum)){
				recordCri.add(Restrictions.eq("phoneNum", phoneNum));
			}
			if(startTime!=null && !"".equals(startTime)){
				recordCri.add(Restrictions.ge("addTime", startTime));
			}
			if(endTime!=null && !"".equals(endTime)){
				recordCri.add(Restrictions.le("addTime", endTime));
			}
			recordCri.addOrder(Order.desc("addTime"));
			if(pageIndex!=0)
				recordCri.setFirstResult((pageIndex-1)*pageSize);
			if(pageSize!=0)
				recordCri.setMaxResults(pageSize);
			records = recordCri.list();
			for(Messagerecord record:records){
				record.getUser().toString();
			}
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
		return records;
	}

	@Override
	public int getMessageRecordCount(Date startTime, Date endTime) {
		int count = 0 ;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria recordCri = session.createCriteria(Messagerecord.class);
			if(startTime!=null && !"".equals(startTime))
				recordCri.add(Restrictions.ge("addTime", startTime));
			if(endTime!=null && !"".equals(endTime))
				recordCri.add(Restrictions.le("addTime", endTime));
			recordCri.setProjection(Projections.count("id"));
			count = Integer.parseInt(recordCri.uniqueResult().toString());
			tx.commit();
		}catch(Exception ex){
			if(null != tx){
				tx.rollback();
			}
			ex.printStackTrace();
			count = -1;
		}finally {
			HibernateUtil.close(session);
		}
		return count;
	}

	@Override
	public int getCount() {
		int count = 0;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria recordCri = session.createCriteria(Messagerecord.class);
			recordCri.setProjection(Projections.rowCount());
			count = Integer.parseInt(recordCri.uniqueResult().toString());
			tx.commit();
		}catch(Exception ex){
			if(null != tx){
				tx.rollback();
			}
			ex.printStackTrace();
		}finally {
			HibernateUtil.close(session);
		}
		return count;
	}

}
