package com.tianxiafen.dao;

import java.util.Date;
import java.util.List;

import com.tianxiafen.entity.Messagerecord;

public interface IMessageRecordDao {

	/**
	 * 添加短信发送记录
	 * @param recor 短信发送记录
	 * @return 是否添加成功
	 */
	public boolean addMessageRecord(Messagerecord record);
	/**
	 * 查看短信记录
	 * @param phoneNum 目的号码
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param pageIndex 页码
	 * @param pageSize 页面大小
	 * @return 短信记录列表
	 */
	public List<Messagerecord> getMessageRecord(String phoneNum,Date startTime,Date endTime,int pageIndex,int pageSize);
	/**
	 * 获取短信记录总数目
	 * @return 短信记录总数目
	 */
	public int getCount();
	/**
	 * 获取短信记录统计
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 统计数目
	 */
	public int getMessageRecordCount(Date startTime,Date endTime);
}
