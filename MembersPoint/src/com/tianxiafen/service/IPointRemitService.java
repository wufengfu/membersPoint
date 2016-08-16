package com.tianxiafen.service;

import java.util.Date;
import java.util.List;

import com.tianxiafen.entity.Pointremit;

public interface IPointRemitService {
	/**
	 * 获取积分汇出列表
	 * @param remitName 汇出姓名
	 * @param serialNum 序列号
	 * @param cardNum 卡号
	 * @param companyId 航司ID
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param statues 状态
	 * @param pageIndex 页码
	 * @param pageSize 页面大小
	 * @return 汇出列表
	 */
	public List<Pointremit> getPointRemit(String remitName,String serialNum,String cardNum,int companyId,Date startTime,Date endTime,Integer statues,int pageIndex,int pageSize);
	/**
	 * 添加汇出记录
	 * @param pointRemit 汇出记录
	 * @return 汇出记录
	 */
	public Pointremit addPointRemit(Pointremit pointRemit);
	/**
	 * 更改积分汇出记录的状态   -1：已删除
						0：未审核
						1：已汇出
						2：已到帐
	 * @param pointRemit 积分汇出记录
	 * @return 是否成功
	 */
	public boolean updatePointRemitStatues(Pointremit pointRemit);
	/**
	 * 获取记录的最大id
	 * @return 
	 */
	public int getMaxId();
	/**
	 * 获取条数统计
	 * @return
	 */
	public int getCount();
}
