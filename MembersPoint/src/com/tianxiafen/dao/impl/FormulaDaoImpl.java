package com.tianxiafen.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tianxiafen.dao.IFormulaDao;
import com.tianxiafen.entity.Airlinecompany;
import com.tianxiafen.entity.Formula;
import com.tianxiafen.util.HibernateUtil;

public class FormulaDaoImpl implements IFormulaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Formula> getFormula(int companyId,int pageIndex,int pageSize) {
		List<Formula> formulaList = new ArrayList<Formula>();
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria pointCri = session.createCriteria(Formula.class,"formula");
			if(companyId!=0)
				pointCri.add(Restrictions.eq("airlinecompany.id", companyId));
			pointCri.addOrder(Order.asc("airlinecompany.id"));
			pointCri.addOrder(Order.asc("levelValue"));//按照层次值排序
			if(pageIndex!=0)
				pointCri.setFirstResult((pageIndex-1)*pageSize);
			if(pageSize!=0)
				pointCri.setMaxResults(pageSize);
			formulaList = pointCri.list();
			for(Formula formula:formulaList){
				formula.getAirlinecompany().getCompanyName();
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
		return formulaList;
	}

	@Override
	public Formula addFormula(Formula formula) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.save(formula);
			tx.commit();
		}catch(Exception ex){
			if(null != tx){
				tx.rollback();
			}
			ex.printStackTrace();
		}finally {
			HibernateUtil.close(session);
		}
		return formula;
	}

	@Override
	public boolean deleteFormula(int formulaId) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Formula formula = session.get(Formula.class, formulaId);
			session.delete(formula);
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
	public Formula updateFormula(Formula formula) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.update(formula);
			tx.commit();
		}catch(Exception ex){
			if(null != tx){
				tx.rollback();
			}
			ex.printStackTrace();
		}finally {
			HibernateUtil.close(session);
		}
		return formula;
	}

	@Override
	public int getCount() {
		int count = 0;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria forCri = session.createCriteria(Formula.class);
			forCri.setProjection(Projections.rowCount());
			count = Integer.parseInt(forCri.uniqueResult().toString());
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
