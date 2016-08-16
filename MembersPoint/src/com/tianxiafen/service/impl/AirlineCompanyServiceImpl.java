package com.tianxiafen.service.impl;

import java.util.List;

import com.tianxiafen.dao.IAirlineCompanyDao;
import com.tianxiafen.dao.impl.AirlineCompanyDaoImpl;
import com.tianxiafen.entity.Airlinecompany;
import com.tianxiafen.service.IAirlineCompanyService;

public class AirlineCompanyServiceImpl implements IAirlineCompanyService {

	IAirlineCompanyDao dao = new AirlineCompanyDaoImpl();
	@Override
	public List<Airlinecompany> getAllCompany(int pageIndex,int pageSize) {
		return dao.getAllCompany(pageIndex,pageSize);
	}
	@Override
	public List<Airlinecompany> getAllCompany() {
		return dao.getAllCompany();
	}

	@Override
	public Airlinecompany addCompany(Airlinecompany company) {
		return dao.addCompany(company);
	}

	@Override
	public boolean deleteComplan(int companyId) {
		return dao.deleteComplan(companyId);
	}

	@Override
	public Airlinecompany getCompanyByName(String companyName) {
		return dao.getCompanyByName(companyName);
	}

	@Override
	public boolean updateCompany(Airlinecompany company) {
		return dao.updateCompany(company);
	}

	@Override
	public Airlinecompany getCompanyById(int companyId) {
		return dao.getCompanyById(companyId);
	}

	@Override
	public int getCompanyCount() {
		return dao.getCompanyCount();
	}

}
