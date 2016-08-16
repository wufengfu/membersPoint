package com.tianxiafen.dao;

import com.tianxiafen.entity.Manager;

public interface IManagerDao {

	public Manager login(String userName, String password);
}
