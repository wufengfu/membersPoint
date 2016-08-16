package com.tianxiafen.dao;

import java.util.List;

import com.tianxiafen.entity.User;
/**
 * 用户接口
 * @author Wufengfu
 *
 */
public interface IUserDao {

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
	 * 设置或者更改交易密码
	 * @param transactionPassword 交易密码
	 * @return 是否更改成功
	 */
	public boolean setTransactionPassword(int userId,String transactionPassword);
	/**
	 * 微信处用户获取登陆状态
	 * @param openId 用户微信标识
	 * @return 用户信息
	 */
	public User login(String openId);
	/**
	 * 根据用户ID改变用户电话好哦吗
	 * @param id 用户标识
	 * @param newPhoneNum 电话号码
	 * @return 是否成功
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
	 * 更改用户权限状态
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
