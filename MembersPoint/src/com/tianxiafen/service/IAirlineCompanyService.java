package com.tianxiafen.service;

import java.util.List;

import com.tianxiafen.entity.Airlinecompany;

public interface IAirlineCompanyService {
	/**
	 * 获取所有航司信息
	 * @return 航司列表
	 */
	public List<Airlinecompany> getAllCompany(int pageIndex,int pageSize);
	/**
	 * 获取所有航司信息
	 * @return 航司列表
	 */
	public List<Airlinecompany> getAllCompany();
	/**
	 * 添加航空公司
	 * @param company 航空公司信息
	 * @return 已经添加的航空公司信息
	 */
	public Airlinecompany addCompany(Airlinecompany company);
	
	/**
	 * 删除航空公司
	 * @param companyId 航空公司ID
	 * @return 是否删除成功
	 */
	public boolean deleteComplan(int companyId);
	
	/**
	 * 根据航空公司名称获取航空公司相关内容
	 * @param companyName
	 * @return
	 */
	public Airlinecompany getCompanyByName(String companyName);
	/**
	 * 根据航空公司id获取航空公司相关内容
	 * @param companyId
	 * @return
	 */
	public Airlinecompany getCompanyById(int companyId);
	
	/**
	 * 更新航司信息
	 * @param company 航司内容
	 * @return 是否成功
	 */
	public boolean updateCompany(Airlinecompany company);
	/**
	 * 获取count总数
	 * @return
	 */
	public int getCompanyCount();
}
