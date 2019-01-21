package com.molly.service;

import java.util.Map;

import com.molly.bean.UserBean;
import com.molly.util.PageUtil;

public interface IUserService {
	public String register(UserBean userBean);
	public Map<String, Object> showThisUsers(PageUtil util);
	public UserBean showUser(UserBean userBean);
	public String setAuth(UserBean userBean);
	public String deleteUser(UserBean userBean);
	public Map<String, Object> searchUsers(UserBean userBean,PageUtil pageUtil);
	public UserBean login(String name); 
}
