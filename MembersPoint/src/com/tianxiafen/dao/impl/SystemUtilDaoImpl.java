package com.tianxiafen.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tianxiafen.dao.ISystemUtilDao;
import com.tianxiafen.entity.Airlinecompany;
import com.tianxiafen.entity.Systemutil;
import com.tianxiafen.util.HibernateUtil;

public class SystemUtilDaoImpl implements ISystemUtilDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Systemutil> getAllUtil(int pageIndex,int pageSize) {
		List<Systemutil> utils = new ArrayList<Systemutil>();
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria sysCri = session.createCriteria(Systemutil.class);
			if(pageIndex!=0)
				sysCri.setFirstResult((pageIndex-1)*pageSize);
			if(pageSize!=0)
				sysCri.setMaxResults(pageSize);
			utils = sysCri.list();
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
		return utils;
	}

	@Override
	public Systemutil addSystemUtil(Systemutil util) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(util);
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
		return util;
	}

	@Override
	public boolean deleteSystemUtil(int utilId) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Systemutil util = session.get(Systemutil.class, utilId);
			session.delete(util);
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
	public Systemutil getContent(String name) {
		Systemutil util = null;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria utilCri = session.createCriteria(Systemutil.class);
			utilCri.add(Restrictions.eq("name", name));
			@SuppressWarnings("unchecked")
			List<Systemutil> utils = utilCri.list();
			if(utils!=null && utils.size()>0){
				util = utils.get(0);			}
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
		return util;
	}

	@Override
	public boolean updateSystemUtil(Systemutil util) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(util);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Systemutil> getContents(String name) {
		List<Systemutil> utils = new ArrayList<Systemutil>();
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria utilCri = session.createCriteria(Systemutil.class);
			utilCri.add(Restrictions.eq("name", name));
			utils = utilCri.list();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
		return utils;
	}

	@Override
	public int getCount() {
		int count = 0;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria comCri = session.createCriteria(Systemutil.class);
			comCri.setProjection(Projections.rowCount());
			count = Integer.parseInt(comCri.uniqueResult().toString());
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
