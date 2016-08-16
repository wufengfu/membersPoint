package com.tianxiafen.service;

import java.util.List;

import com.tianxiafen.entity.Formula;

public interface IFormulaService {
	/**
	 * 根据航司Id获取该航司计算公式
	 * @param companyId 航司标识
	 * @param pageIndex 页码
	 * @param pageSize 页面大小
	 * @return 公式列表
	 */
	public List<Formula> getFormula(int companyId,int pageIndex,int pageSize);
	/**
	 * 获取公式总数目
	 * @return 公式总数目
	 */
	public int getCount();
	/**
	 * 添加计算公式
	 * @param formula 公式内容
	 * @return 已添加的公式
	 */
	public Formula addFormula(Formula formula);
	
	/**
	 * 删除计算公式
	 * @param formulaId 公式ID
	 * @return 是否删除成功
	 */
	public boolean deleteFormula(int formulaId);
	/**
	 * 修改计算公式
	 * @param formula 新的公式
	 * @return 修改之后的公式
	 */
	public Formula updateFormula(Formula formula);
}
