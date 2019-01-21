package com.molly.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.molly.bean.FixBean;
import com.molly.bean.RoomBean;
import com.molly.dao.IFixDao;
import com.molly.dao.IRoomDao;
import com.molly.service.IFixService;

@Service
public class FixServiceImpl implements IFixService {
	
	@Autowired
	private IFixDao fixDao;
	@Autowired
	private IRoomDao roomDao;
	
//	插入某条信息
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public String insertFix(FixBean fixBean) {
//		系统进行非空处理
		int result = fixDao.insertFix(fixBean);
		if (result > 0) {
			roomDao.badRoomByRid(fixBean.getRid());
			return "success";
		}
		return "fail";
	}

	
//	处理某条信息
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public String handleFix(FixBean fixBean) {
//		先自己做非空和有效性校验
		if (fixBean == null) {
				return "非法操作";
		}
		int fid = fixBean.getFid();
		if (fid < 1) {
			return "非法操作";
		}
		int result = fixDao.deleteFix(fixBean);
		if (result > 0) {
//			如果处理了这条，则检查这个rid是否都处理好了。
			int have = fixDao.findFixCountsByRid(fixBean.getRid());
			System.out.println("have"+have);
			if (have == 0) {
				System.out.println("我正在处理");
				FixBean fixBean2 = fixDao.findFixByFix(fixBean);
				roomDao.updateRoomByRid(fixBean2.getRid());
			}
			return "success";
		}
		return "fail";
	}

//	某个房间所有的设备处理信息
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public List<FixBean> showAll(RoomBean roomBean) {
//		先自己做非空和有效性校验
		if (roomBean == null) {
				return null;
		}
		int rid = roomBean.getRid();
		if (rid < 1) {
			return null;
		}
		List<FixBean> fixs = new ArrayList<>();
		fixs = fixDao.findFixsByRid(roomBean);
		return fixs;
	}

}
