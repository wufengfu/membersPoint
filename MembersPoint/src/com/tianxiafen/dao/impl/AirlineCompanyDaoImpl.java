package com.tianxiafen.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tianxiafen.dao.IAirlineCompanyDao;
import com.tianxiafen.entity.Airlinecompany;
import com.tianxiafen.util.HibernateUtil;

public class AirlineCompanyDaoImpl implements IAirlineCompanyDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Airlinecompany> getAllCompany(int pageIndex,int pageSize) {
		List<Airlinecompany> companyList = new ArrayList<Airlinecompany>();
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria companyCri = session.createCriteria(Airlinecompany.class);
			companyCri.setFirstResult((pageIndex-1)*pageSize);
			companyCri.setMaxResults(pageSize);
			companyList = companyCri.list();
			tx.commit();
		}catch(Exception ex){
			if(null != tx){
				tx.rollback();
			}
			ex.printStackTrace();
		}finally {
			HibernateUtil.close(session);
		}
		return companyList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Airlinecompany> getAllCompany() {
		List<Airlinecompany> companyList = new ArrayList<Airlinecompany>();
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria companyCri = session.createCriteria(Airlinecompany.class);
			companyList = companyCri.list();
			tx.commit();
		}catch(Exception ex){
			if(null != tx){
				tx.rollback();
			}
			ex.printStackTrace();
		}finally {
			HibernateUtil.close(session);
		}
		return companyList;
	}

	@Override
	public Airlinecompany addCompany(Airlinecompany company) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.save(company);
			tx.commit();
		}catch(Exception ex){
			if(null != tx){
				tx.rollback();
			}
			ex.printStackTrace();
		}finally {
			HibernateUtil.close(session);
		}
		return company;
	}

	@Override
	public boolean deleteComplan(int companyId) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Airlinecompany company = session.get(Airlinecompany.class, companyId);
			session.delete(company);
			tx.commit();
		}catch(Exception ex){
			if(null != tx){
				tx.rollback();
			}
			ex.printStackTrace();
			return false;
		}finally {
			HibernateUtil.close(session);
		}
		return true;
	}

	@Override
	public Airlinecompany getCompanyByName(String companyName) {
		Airlinecompany company = null;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria comCri = session.createCriteria(Airlinecompany.class);
			comCri.add(Restrictions.eq("companyName", companyName));
			@SuppressWarnings("unchecked")
			List<Airlinecompany> list = comCri.list();
			if(list!=null && list.size()>0){
				company = list.get(0);						
			}
			tx.commit();
		}catch(Exception ex){
			if(null != tx){
				tx.rollback();
			}
			ex.printStackTrace();
		}finally {
			HibernateUtil.close(session);
		}
		return company;
	}

	@Override
	public boolean updateCompany(Airlinecompany company) {

		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.update(company);
			tx.commit();
		}catch(Exception ex){
			if(null != tx){
				tx.rollback();
			}
			ex.printStackTrace();
			return false;
		}finally {
			HibernateUtil.close(session);
		}
		return true;
	}

	@Override
	public Airlinecompany getCompanyById(int companyId) {
		Airlinecompany company = null;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			company = session.get(Airlinecompany.class, companyId);
			tx.commit();
		}catch(Exception ex){
			if(null != tx){
				tx.rollback();
			}
			ex.printStackTrace();
		}finally {
			HibernateUtil.close(session);
		}
		return company;
	}

	@Override
	public int getCompanyCount() {
		int count = 0;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria comCri = session.createCriteria(Airlinecompany.class);
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
