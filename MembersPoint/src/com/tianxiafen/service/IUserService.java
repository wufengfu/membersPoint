package com.tianxiafen.service;

import java.util.List;

import com.tianxiafen.entity.Pointabouchemenet;
import com.tianxiafen.entity.User;

public interface IUserService {
	/**
	 * 从微信处拉取信息注册
	 * @param user 从微信处获取的信息
	 * @return user对象
	 */
	public User register(User user);
	/**
	 * 更新数据库中的用户信息
	 * @param user 用户自己完善的信息
	 * @return
	 */
	public User update(User user);
	/**
	 * 微信处用户获取登陆状态
	 * @param openId 用户微信标识
	 * @return 用户信息
	 */
	public User login(String openId);
	/**
	 * 更换用户的手机号码
	 * @param id 用户标识
	 * @param newPhoneNum 新的手机号码
	 */
	public boolean changePhone(Integer id, String newPhoneNum);
	/**
	 * 通过权限状态获取用户列表
	 * @param statues 用户状态
	 * @param fullName 全名
	 * @param phoneNum 电话号码
	 * @param pageIndex 页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public List<User> getUserList(int statues,String fullName,String phoneNum,int pageIndex,int pageSize);
	/**
	 * 更改用户的权限状态
	 * @param user 用户对象
	 * @return 是否成功
	 */
	public boolean updateUserStatues(User user);
	/**
	 * 获取用户总数
	 * @return
	 */
	public int getCount();
}
