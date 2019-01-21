package com.molly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.molly.bean.UserBean;
import com.molly.dao.IUserDao;
import com.molly.service.IUserService;
import com.molly.util.Page;
import com.molly.util.PageUtil;
import com.molly.util.RegexUtil;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao userDao;
	
	
//	注册功能
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public String register(UserBean userBean) {
//		自动自带的非空校验
//		手动非空校验和合法性校验
		if (userBean.getUphone() == null || userBean.getUphone().length() == 0) {
			return "请把信息填写完整";
		}
//		手动电话号码有效性校验
		if (!userBean.getUphone().matches(RegexUtil.REGEX_MOBILE)) {
			return "手机号码不合法";
		}
		Object obj = new SimpleHash("MD5", userBean.getUpwd(), userBean.getUname(), 1024); 
		userBean.setUpwd(obj.toString());
		userDao.insertUserBean(userBean);
		return "success";
	}

	
//	分页显示所有的users
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public Map<String, Object> showThisUsers(PageUtil inUtil) {
		Map<String, Object> usersMap = new HashMap<>();
//		查阅一共有多少页
		int countSum = userDao.findAllCount();
		int pageSize = 10;
		inUtil.setCountSum(countSum);
		inUtil.setPageSize(pageSize);
		PageUtil outUtil = Page.getPageUtil(inUtil);
		usersMap.put("pageUtil", outUtil);
//		查看users
		List<UserBean> users = userDao.showThisUsers(outUtil);
		usersMap.put("users", users);
		return usersMap;
	}

	
//	展示某个用户信息
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public UserBean showUser(UserBean userBean) {
		int uid = userBean.getUid();
		if (uid < 1) {
			return null;
		}
		return userDao.findUserByUid(userBean);
	}

	
//	改变某个用户的权限
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public String setAuth(UserBean userBean) {
		int uid = userBean.getUid();
		if (uid < 1) {
			return "非法操作";
		}
		if (userBean.getRoles() == null ||
			userBean.getRoles().length() == 0) {
			return "非法操作";
		}
		int result = userDao.updateUser(userBean);
		if (result > 0) {
			return "success";
		}
		return "权限没有改变";
	}

	
//	删除某个用户
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public String deleteUser(UserBean userBean) {
		int uid = userBean.getUid();
		if (uid < 1) {
			return "非法操作";
		}
		int result = userDao.deleteUser(userBean);
		if (result > 0) {
			return "success";
		}
		return "fail";
	}


//  查询某个用户
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public Map<String, Object> searchUsers(UserBean userBean, PageUtil inUtil) {
		if (userBean == null) {
			return null;
		}
//		设置检索字段
		if (userBean.getRoles() == null || userBean.getRoles().length() == 0) {
			userBean.setRoles("%%");
		}else {
			String roles = "%" + userBean.getRoles() + "%";
			userBean.setRoles(roles);
		}
		
		if (userBean.getUname() == null || userBean.getUname().length() == 0) {
			userBean.setUname("%%");
		}else {
			String uname = "%" + userBean.getUname() + "%";
			userBean.setUname(uname);
		}
//		设置页数情况
		Map<String, Object> usersMap = new HashMap<>();
//		查阅一共有多少页
		int countSum = userDao.findAllUsersByUser(userBean);
		int pageSize = 10;
		inUtil.setCountSum(countSum);
		inUtil.setPageSize(pageSize);
		PageUtil outUtil = Page.getPageUtil(inUtil);
		usersMap.put("pageUtil", outUtil);
//		查看users
		List<UserBean> users = userDao.findUsersByUser(userBean, outUtil);
		usersMap.put("users", users);
		return usersMap;
	}


	@Override
	public UserBean login(String name) {
		return userDao.findByUname(name);
	}
	
	
	
	
}
