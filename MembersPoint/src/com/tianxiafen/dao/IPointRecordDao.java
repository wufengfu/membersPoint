package com.tianxiafen.dao;

import java.util.Date;
import java.util.List;

import com.tianxiafen.entity.Pointrecord;

public interface IPointRecordDao {

	/**
	 * 增加分值记录（系统调用）
	 * @param pointrecord 分值记录
	 * @return 是否成功
	 */
	public boolean addPointRecord(Pointrecord pointrecord);
	/**
	 * 查看积分交易记录（积分明细）
	 * @param companyId 航司标识
	 * @param userName 用户名
	 * @param servialNum 序列号
	 * @param dealStatus 交易状态
	 * @param dealType 交易类型
	 * @param comStatus 在途状态
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param pageIndex 页码
	 * @param pageSize 页面大小
	 * @return 积分记录列表
	 */
	public List<Pointrecord> getRecords(int companyId,String userName, String serialNum,
			int dealStatus, int dealType, int comStatus, Date startTime,
			Date endTime,int pageIndex,int pageSize);
	/**
	 * 获取页面大小
	 * @return
	 */
	public int getCount();
	/**
	 * 查看积分交易记录（积分明细）客户端
	 * @param userId 用户标识
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param dealType 处理类型
	 * @return 是否成功
	 */
	public List<Pointrecord> getRecords(Integer userId, Date startTime,
			Date endTime, int dealType);
}
