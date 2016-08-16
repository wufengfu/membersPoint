package com.tianxiafen.service;

import java.util.Date;
import java.util.List;

import com.tianxiafen.entity.Pointabouchemenet;

public interface IPointAbouchemenetService {
	/**
	 * 获取积分汇入记录列表
	 * @param userName 用户名
	 * @param serialNum 流水号
	 * @param postscript 附言
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param statues 状态
	 * @param pageIndex 状态
	 * @param pageSize 状态
	 * @return 汇入列表
	 */
	public List<Pointabouchemenet> getPointAboList(String userName,String serialNum,String postscript,Date startTime,Date endTime,int statues,int pageIndex,int pageSize);
	/**
	 * 积分汇入
	 * @param pointAbo 积分汇入数据
	 * @return 是否插入成功
	 */
	public boolean addPointAbo(Pointabouchemenet pointAbo);
	/**
	 * 更新积分汇入记录的状态     -1：已删除
						0：未审核
						1：已汇出
						2：已到帐

	 * @param pointAbo	积分汇入状态
	 * @return
	 */
	public boolean updateStatuesPointAbo(Pointabouchemenet pointAbo);
	/**
	 * 获取汇出记录表的最大ID
	 * @return 最大ID
	 */
	public int getMaxId();
	/**
	 * 获取汇出申请统计
	 * @return
	 */
	public int getCount();
}
