package com.tianxiafen.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.tianxiafen.dao.IManagerDao;
import com.tianxiafen.entity.Airlinecompany;
import com.tianxiafen.entity.Manager;
import com.tianxiafen.util.HibernateUtil;

public class ManagerDaoImpl implements IManagerDao {

	@Override
	public Manager login(String userName,String password) {
		Manager manager = null;
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria managerCri = session.createCriteria(Manager.class);
			managerCri.add(Restrictions.eq("userName", userName));
			managerCri.add(Restrictions.eq("passWord",password));
			@SuppressWarnings("unchecked")
			List<Manager> list = managerCri.list();
			if(list!=null && list.size()>0){
				manager = list.get(0);
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
		return manager;
	}

}
