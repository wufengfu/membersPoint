package com.tianxiafen.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tianxiafen.dao.IPointRecordDao;
import com.tianxiafen.entity.Pointrecord;
import com.tianxiafen.entity.Pointremit;
import com.tianxiafen.util.HibernateUtil;

public class PointRecordDaoImpl implements IPointRecordDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Pointrecord> getRecords(int companyId,String userName, String serialNum,
			int dealStatus, int dealType, int comStatus, Date startTime,
			Date endTime,int pageIndex,int pageSize) {
		List<Pointrecord> pointRecords = new ArrayList<Pointrecord>();
		
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria pointRecordCri = session.createCriteria(Pointrecord.class);
			pointRecordCri.createAlias("user", "user");
			if(companyId!=0)
				pointRecordCri.add(Restrictions.eq("airlinecompany.id", companyId));
			if(userName!=null && !"".equals(userName))
				pointRecordCri.add(Restrictions.eq("user.fullName", userName));
			if(serialNum!=null && !"".equals(serialNum))
				pointRecordCri.add(Restrictions.eq("serialNum", serialNum));
			if(dealStatus!=0)
				pointRecordCri.add(Restrictions.eq("dealStatus", dealStatus));
			if(dealType!=0)
				pointRecordCri.add(Restrictions.eq("dealType", dealType));
			if(comStatus!=0)
				pointRecordCri.add(Restrictions.eq("comStatus", comStatus));
			if(startTime != null && !"".equals(startTime))
				pointRecordCri.add(Restrictions.ge("dealTime",startTime));
			if(endTime != null && !"".equals(endTime))
				pointRecordCri.add(Restrictions.le("dealTime",endTime));
			if(pageIndex!=0)
				pointRecordCri.setFirstResult((pageIndex-1)*pageSize);
			if(pageSize!=0)
				pointRecordCri.setMaxResults(pageSize);
			pointRecordCri.addOrder(Order.desc("dealTime"));
			pointRecords = pointRecordCri.list();
			for(Pointrecord record: pointRecords){
				record.getUser().toString();
				if(record.getDealType()==1)
					record.getAirlinecompany().toString();
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
		return pointRecords;
	}

	@Override
	public boolean addPointRecord(Pointrecord pointrecord) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(pointrecord);
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
	public List<Pointrecord> getRecords(Integer userId, Date startTime,
			Date endTime, int dealType) {
List<Pointrecord> pointRecords = new ArrayList<Pointrecord>();
		
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria pointRcordCri = session.createCriteria(Pointrecord.class);
			if(userId!=0)
				pointRcordCri.add(Restrictions.eq("user.id", userId));
			if(startTime != null && !"".equals(startTime))
				pointRcordCri.add(Restrictions.ge("dealTime",startTime));
			if(endTime != null && !"".equals(endTime))
				pointRcordCri.add(Restrictions.le("dealTime",endTime));
			if(dealType!=0)
				pointRcordCri.add(Restrictions.eq("dealType",dealType));
			pointRcordCri.addOrder(Order.desc("dealTime"));
			pointRecords = pointRcordCri.list();
			for(Pointrecord record: pointRecords){
				record.getUser().toString();
				if(record.getDealType()==1)
					record.getAirlinecompany().toString();
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
		return pointRecords;
	}

	@Override
	public int getCount() {
		int count = 0;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria recordCri = session.createCriteria(Pointrecord.class);
			recordCri.setProjection(Projections.rowCount());
			count = Integer.parseInt(recordCri.uniqueResult().toString());
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
		return count;
	}


}
