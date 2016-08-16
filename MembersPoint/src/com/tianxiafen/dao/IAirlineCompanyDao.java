package com.tianxiafen.dao;

import java.util.List;

import com.tianxiafen.entity.Airlinecompany;

public interface IAirlineCompanyDao {

	/**
	 * 获取所有航司信息
	 * @param pageIndex 页码
	 * @param pageSize 每页大小
	 * @return 航司列表
	 */
	public List<Airlinecompany> getAllCompany(int pageIndex,int pageSize);
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
	 * 更新航司信息
	 * @param company 航司内容
	 * @return 是否成功
	 */
	public boolean updateCompany(Airlinecompany company);
	/**
	 * 根据航空公司id获取航空公司相关内容
	 * @param companyId
	 * @return
	 */
	public Airlinecompany getCompanyById(int companyId);
	/**
	 * 获取航司总数
	 * @return
	 */
	public int getCompanyCount();
	
	public List<Airlinecompany> getAllCompany();
}
