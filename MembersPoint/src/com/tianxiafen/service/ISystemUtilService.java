package com.tianxiafen.service;

import java.util.List;

import com.tianxiafen.entity.Systemutil;

public interface ISystemUtilService {
	/**
	 * 获取所有系统配置量
	 * @param pageIndex 页码
	 * @param pageSize 页面大小
	 * @return 系统配置量列表
	 */
	public List<Systemutil> getAllUtil(int pageIndex,int pageSize);
	/**
	 * 获取数目
	 * @return 数目
	 */
	public int getCount();
	/**
	 * 添加系统变量
	 * @param util 变量内容
	 * @return 添加成功之后的变量
	 */
	public Systemutil addSystemUtil(Systemutil util);
	
	/**
	 * 删除系统配置变量
	 * @param utilId 变量ID
	 * @return 是否删除成功
	 */
	public boolean deleteSystemUtil(int utilId);
	/**
	 * 通过系统配置的name获取其值
	 * @param name 系统配置的key（开发人员定义）
	 * @return 系统配置变量
	 */
	public Systemutil getContent(String name);
	
	/**
	 * 通过系统配置的name获取其值
	 * @param name 系统配置的key（开发人员定义）
	 * @return 系统配置变量列表
	 */
	public List<Systemutil> getContents(String name);
	/**
	 * 更新系统配置值
	 * @param util 配置值
	 * @return 是否更新成功
	 */
	public boolean updateSystemUtil(Systemutil util);
}
