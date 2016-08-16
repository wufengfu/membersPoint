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

import com.tianxiafen.dao.IPointAbouchemenetDao;
import com.tianxiafen.entity.Pointabouchemenet;
import com.tianxiafen.entity.Pointrecord;
import com.tianxiafen.entity.User;
import com.tianxiafen.util.HibernateUtil;

public class PointAbouchemenetDaoImpl implements IPointAbouchemenetDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Pointabouchemenet> getPointAboList(String userName,String serialNum,String postscript, Date startTime,
			Date endTime,Integer statues,int pageIndex,int pageSize) {

		List<Pointabouchemenet> pointList = new ArrayList<Pointabouchemenet>();

		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria pointCri = session.createCriteria(Pointabouchemenet.class);
			pointCri.createAlias("user", "user");
			if (userName != null && !"".equals(userName))
				pointCri.add(Restrictions.eq("user.fullName", userName));
			if (serialNum != null && !"".equals(serialNum))
				pointCri.add(Restrictions.eq("serialNum", serialNum));
			if (postscript != null && !"".equals(postscript))
				pointCri.add(Restrictions.eq("postscript", postscript));
			if (startTime != null && !"".equals(startTime))
				pointCri.add(Restrictions.ge("addTime", startTime));
			if (endTime != null && !"".equals(endTime))
				pointCri.add(Restrictions.le("addTime", endTime));
			if(statues!=null && statues!=-2)
				pointCri.add(Restrictions.eq("statues", statues));
			if(pageIndex!=0)
				pointCri.setFirstResult((pageIndex-1)*pageSize);
			if(pageSize!=0)
				pointCri.setMaxResults(pageSize);
			pointCri.addOrder(Order.desc("addTime"));
			pointList = pointCri.list();
			for(Pointabouchemenet point : pointList){
				point.getUser().toString();
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
		return pointList;
	}

	@Override
	public boolean addPointAbo(Pointabouchemenet pointAbo) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(pointAbo);
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
	public boolean updateStatuesPointAbo(Pointabouchemenet pointAbo) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql = "update Pointabouchemenet point set point.statues = :statues,point.passTime = :passTime where point.id = :pointAboId";
			Query query = session.createQuery(hql);
			query.setParameter("statues", pointAbo.getStatues());
			query.setParameter("pointAboId", pointAbo.getId());
			query.setParameter("passTime", new Timestamp(System.currentTimeMillis()));//处理时间定为现在	
			query.executeUpdate();
			if(pointAbo.getStatues()==1){//如果改变的状态为汇入
				pointAbo = session.get(Pointabouchemenet.class, pointAbo.getId());
				//添加积分变动记录
				Pointrecord record = new Pointrecord();
				record.setUser(pointAbo.getUser());
				record.setSerialNum(pointAbo.getSerialNum());
				record.setDealPoint(pointAbo.getPoints());
				record.setDealStatus(1);//成功
				record.setComStatus(1);//已生效
				record.setDealType(2);//转入
				record.setDealTime(new Timestamp(System.currentTimeMillis()));
				session.save(record);
				//更改用户积分
				User user = session.get(User.class, pointAbo.getUser().getId());
				int oldPointBalance = user.getPointBalance();
				user.setPointBalance(oldPointBalance+pointAbo.getPoints());
				session.update(user);
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
			String hql = "select max(p.id) from Pointabouchemenet p ";
			Query query = session.createQuery(hql);
			result = (Integer) query.uniqueResult();
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
			Criteria aboCri = session.createCriteria(Pointabouchemenet.class);
			aboCri.setProjection(Projections.rowCount());
			count = Integer.parseInt(aboCri.uniqueResult().toString());
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
