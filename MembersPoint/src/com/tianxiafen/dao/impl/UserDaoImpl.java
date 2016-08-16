package com.tianxiafen.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tianxiafen.dao.IUserDao;
import com.tianxiafen.entity.Pointabouchemenet;
import com.tianxiafen.entity.Pointrecord;
import com.tianxiafen.entity.User;
import com.tianxiafen.util.HibernateUtil;

public class UserDaoImpl implements IUserDao {

	@Override
	public User register(User user) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(user);
			
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
		return user;
	}

	@Override
	public User update(User user) {
		if(user.getId()==null || user.getId()==0)
			return null;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql = "update User user set user.familyName = :familyName,user.familyNameSpell = :familyNameSpell,user.realName = :realName,"
					+ "user.realNameSpell = :realNameSpell,user.phoneNum = :phoneNum,user.transactionPassword = :transactionPassword where user.id = :userId";
			Query query = session.createQuery(hql);
			query.setParameter("familyName", user.getFamilyName());
			query.setParameter("familyNameSpell", user.getFamilyNameSpell());
			query.setParameter("realName", user.getRealName());
			query.setParameter("realNameSpell", user.getRealNameSpell());
			query.setParameter("phoneNum", user.getPhoneNum());
			query.setParameter("transactionPassword", user.getTransactionPassword());
			query.setParameter("userId", user.getId());
			query.executeUpdate();
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
			return null;
		} finally {
			HibernateUtil.close(session);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User login(String openId) {
		User user = null;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria userCri = session.createCriteria(User.class);
			userCri.add(Restrictions.eq("openId",openId));
			List<User> list = userCri.list();
			if(list.size()>0)
				user = list.get(0);
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
		return user;
	}

	@Override
	public boolean setTransactionPassword(int userId,String transactionPassword) {
		if(userId==0)
			return false;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql = "update User user set user.transactionPassword = :transactionPassword where user.id = :userId";
			Query query = session.createQuery(hql);
			query.setParameter("transactionPassword", transactionPassword);
			query.setParameter("userId", userId);
			query.executeUpdate();
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
	public boolean changePhone(Integer id, String newPhoneNum) {
		if(id==0)
			return false;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql = "update User user set user.phoneNum = :phoneNum where user.id = :userId";
			Query query = session.createQuery(hql);
			query.setParameter("phoneNum", newPhoneNum);
			query.setParameter("userId", id);
			query.executeUpdate();
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
	public List<User> getUserList(int statues,String fullName,String phoneNum,int pageIndex,int pageSize) {
		List<User> users = new ArrayList<User>();
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria userCri = session.createCriteria(User.class);
			if(statues!=-2)//代表查看所有状态的
				userCri.add(Restrictions.eq("statues", statues));
			if(fullName!=null && !"".equals(fullName)){
				userCri.add(Restrictions.like("fullName", "%"+fullName+"%"));
			}
			if(phoneNum!=null && !"".equals(phoneNum)){
				userCri.add(Restrictions.like("phoneNum", "%"+phoneNum+"%"));
			}
			if(pageIndex!=0)
				userCri.setFirstResult((pageIndex-1)*pageSize);
			if(pageSize!=0)
				userCri.setMaxResults(pageSize);
			users = userCri.list();
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
		return users;
	}

	@Override
	public boolean updateUserStatues(User user) {

		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql = "update User user set user.statues = :statues where user.id = :userId";
			Query query = session.createQuery(hql);
			query.setParameter("statues", user.getStatues());
			query.setParameter("userId", user.getId());
			query.executeUpdate();
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
	public int getCount() {
		int count = 0;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria userCri = session.createCriteria(User.class);
			userCri.setProjection(Projections.rowCount());
			count = Integer.parseInt(userCri.uniqueResult().toString());
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
