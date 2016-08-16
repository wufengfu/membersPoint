package com.tianxiafen.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tianxiafen.dao.IPointRemitDao;
import com.tianxiafen.entity.Pointabouchemenet;
import com.tianxiafen.entity.Pointrecord;
import com.tianxiafen.entity.Pointremit;
import com.tianxiafen.entity.User;
import com.tianxiafen.util.HibernateUtil;

public class PointRemitDaoImpl implements IPointRemitDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Pointremit> getPointRemit(String remitName,String serialNum,String cardNum,int companyId,Date startTime,Date endTime,Integer statues,int pageIndex,int pageSize) {
		List<Pointremit> pointRemitList = new ArrayList<Pointremit>();
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria pointRemitCri = session.createCriteria(Pointremit.class);
			if(remitName != null && !"".equals(remitName)){
				pointRemitCri.add(Restrictions.eq("remitName", remitName));
			}
			if(serialNum != null && !"".equals(serialNum)){
				pointRemitCri.add(Restrictions.eq("serialNum", serialNum));
			}
			if(serialNum != null && !"".equals(cardNum)){
				pointRemitCri.add(Restrictions.eq("cardNum", cardNum));
			}
			
			if(companyId!=0)
				pointRemitCri.add(Restrictions.eq("airlinecompany.id", companyId));
			if(startTime != null && !"".equals(startTime))
				pointRemitCri.add(Restrictions.ge("addTime",startTime));
			if(endTime != null && !"".equals(endTime))
				pointRemitCri.add(Restrictions.le("addTime",endTime));
			if(statues!=null && statues!=-2)
				pointRemitCri.add(Restrictions.eq("statues",statues));
			pointRemitCri.addOrder(Order.desc("addTime"));
			if(pageIndex!=0)
				pointRemitCri.setFirstResult((pageIndex-1)*pageSize);
			if(pageSize!=0){
				pointRemitCri.setMaxResults(pageSize);
			}
			pointRemitList = pointRemitCri.list();
			for(Pointremit remit:pointRemitList){
				remit.getUser().toString();
				remit.getAirlinecompany().toString();
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
		return pointRemitList;
	}

	@Override
	public Pointremit addPointRemit(Pointremit pointRemit) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			if(pointRemit.getFamilyName()!=null && pointRemit.getRealName()!=null){
				pointRemit.setRemitName(pointRemit.getFamilyName()+pointRemit.getRealName());
			}
			int id = (Integer) session.save(pointRemit);
			pointRemit = session.get(Pointremit.class, id);
			User user = session.get(User.class, pointRemit.getUser().getId());
			user.setPointBalance(user.getPointBalance()-pointRemit.getPoints());
			session.update(user);
			tx.commit();
		}catch(Exception ex){
			if(null != tx){
				tx.rollback();
			}
			ex.printStackTrace();
		}finally {
			HibernateUtil.close(session);
		}
		return pointRemit;
	}

	@Override
	public boolean updatePointRemitStatues(Pointremit pointRemit) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql = "update Pointremit point set point.statues = :statues";
			if(pointRemit.getStatues()==1 || pointRemit.getStatues()==-1){
				hql+=",point.passTime = :passTime";
			}
			hql +=" where point.id = :pointId";
			Query query = session.createQuery(hql);
			query.setParameter("statues", pointRemit.getStatues());
			query.setParameter("pointId", pointRemit.getId());
			if(pointRemit.getStatues()==1 || pointRemit.getStatues()==-1){
				query.setParameter("passTime", new Timestamp(System.currentTimeMillis()));	
			}
			query.executeUpdate();
			if(pointRemit.getStatues()==1){//如果改变的状态为汇入
				pointRemit = session.get(Pointremit.class, pointRemit.getId());
				Pointrecord record = new Pointrecord();
				record.setUser(pointRemit.getUser());
				record.setAirlinecompany(pointRemit.getAirlinecompany());
				record.setSerialNum(pointRemit.getSerialNum());
				record.setDealPoint(pointRemit.getPoints());
				record.setDealStatus(1);
				record.setDealType(1);
				record.setDealTime(new Timestamp(System.currentTimeMillis()));
				session.save(record);
				
				User user = session.get(User.class, pointRemit.getUser().getId());
				int oldPointBalance = user.getPointBalance();
				if(oldPointBalance-pointRemit.getPoints()<0){//用户积分不够
					return false;
				}
				user.setPointBalance(oldPointBalance-pointRemit.getPoints());
				session.update(user);
				
			}
			if(pointRemit.getStatues()==2){//如果改变的状态为已生效
				pointRemit = session.get(Pointremit.class, pointRemit.getId());
				Criteria recordCri = session.createCriteria(Pointrecord.class);
				recordCri.add(Restrictions.eq("serialNum", pointRemit.getSerialNum()));
				@SuppressWarnings("unchecked")
				List<Pointrecord> records = recordCri.list();
				if(records!=null && records.size()>=0){
					Pointrecord record = records.get(0);
					record.setComStatus(1);//已生效
					session.update(record);
				}else{
					return false;
				}
			}
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
			return false;
		} finally {
			HibernateUtil.close(session);
		}
		return true;
	}

	@Override
	public int getMaxId() {
		int result = 0;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql = "select max(p.id) from Pointremit p ";
			Query query = session.createQuery(hql);
			result = query.uniqueResult()==null?0:(Integer) query.uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
		return result;
		
	}

	@Override
	public int getCount() {
		int count = 0;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria remitCri = session.createCriteria(Pointremit.class);
			remitCri.setProjection(Projections.rowCount());
			count = Integer.parseInt(remitCri.uniqueResult().toString());
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
