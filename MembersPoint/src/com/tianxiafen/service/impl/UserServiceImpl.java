package com.tianxiafen.service.impl;

import java.util.List;

import com.tianxiafen.dao.IUserDao;
import com.tianxiafen.dao.impl.UserDaoImpl;
import com.tianxiafen.entity.User;
import com.tianxiafen.service.IUserService;

public class UserServiceImpl implements IUserService {

	IUserDao dao = new UserDaoImpl();
	@Override
	public User register(User user) {
		if(user.getFamilyName()!=null && user.getRealName()!=null){
			user.setFullName(user.getFamilyName()+user.getRealName());
		}
		return dao.register(user);
	}

	@Override
	public User update(User user) {
		return dao.update(user);
	}

	@Override
	public User login(String openId) {
		//TODO 根据openId登录需要向微信请求获取openId
		return dao.login(openId);
	}

	@Override
	public boolean changePhone(Integer id, String newPhoneNum) {
		return dao.changePhone(id,newPhoneNum);
	}

	@Override
	public List<User> getUserList(int statues,String fullName,String phoneNum,int pageIndex,int pageSize) {
		return dao.getUserList(statues,fullName,phoneNum,pageIndex,pageSize);
	}

	@Override
	public boolean updateUserStatues(User user) {
		return dao.updateUserStatues(user);
	}

	@Override
	public int getCount() {
		return dao.getCount();
	}
	
	

}
