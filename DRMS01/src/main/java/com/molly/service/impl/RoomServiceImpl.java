package com.molly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.molly.bean.RoomBean;
import com.molly.dao.IRoomDao;
import com.molly.service.IRoomService;
import com.molly.util.Page;
import com.molly.util.PageUtil;

@Service
public class RoomServiceImpl implements IRoomService {
	@Autowired
	private IRoomDao roomDao;

	
//	展示某一页的房间信息
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public Map<String, Object> showThisRooms(PageUtil inUtil) {
		Map<String, Object> roomsMap = new HashMap<>();
//		查阅一共有多少页
		int countSum = roomDao.findAllCount();
		int pageSize = 10;
		inUtil.setCountSum(countSum);
		inUtil.setPageSize(pageSize);
		PageUtil outUtil = Page.getPageUtil(inUtil);
		roomsMap.put("pageUtil", outUtil);
//		查看users
		List<RoomBean> rooms = roomDao.showThisRooms(outUtil);
		roomsMap.put("rooms", rooms);
		return roomsMap;
	}

	
//	增加房间信息
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public String addRoom(RoomBean roomBean) {
//		系统帮助做了非空校验
		int result = roomDao.insertRoom(roomBean);
		if (result > 0) {
			return "success";
		}
		return "fail";
	}

	
//	删除房间信息
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public String deleteRoom(RoomBean roomBean) {
//		先自己做非空和有效性校验
		if (roomBean == null) {
				return "非法操作";
		}
		int rid = roomBean.getRid();
		if (rid < 1) {
			return "非法操作";
		}
//		这个班是否还有学生的判断
		RoomBean room = roomDao.findRoomByRid(roomBean);
		int students = room.getRcap() - room.getRused();
		if (students > 0) {
			return "该房间还有人住，不能删除";
		}
		int result = roomDao.deleteRoom(roomBean);
		if (result > 0) {
			return "success";
		}
		return "fail";
	}

	
//	查看房间的详细信息，包括房间里面住的学生的信息
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public RoomBean findRoom(RoomBean roomBean) {
//		先自己做非空和有效性校验
		if (roomBean == null) {
				return null;
		}
		int rid = roomBean.getRid();
		if (rid < 1) {
			return null;
		}
		return roomDao.findRoomByRid(roomBean);
	}

	
//	通过字段查找某一页的房间门信息
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public Map<String, Object> searchRooms(RoomBean roomBean,String available, PageUtil inUtil) {
		if (roomBean == null || available == null || available.length() == 0 || inUtil == null) {
			return null;
		}
		if (inUtil.getThisPage() == 0) {
			return null;
		}
//		设置检索字段
		if (roomBean.getRaddress() == null || roomBean.getRaddress().length() == 0) {
			roomBean.setRaddress("%%");
		}else {
			String raddress = "%" + roomBean.getRaddress() + "%";
			roomBean.setRaddress(raddress);
		}
		
		if (roomBean.getRstate() == null || roomBean.getRstate().length() == 0) {
			roomBean.setRstate("%%");
		}else {
			String rstate = "%" + roomBean.getRstate() + "%";
			roomBean.setRstate(rstate);
		}
		
		if (roomBean.getRtype() == null || roomBean.getRtype().length() == 0) {
			roomBean.setRtype("%%");
		}else {
			String rType = "%" + roomBean.getRtype() + "%";
			roomBean.setRtype(rType);
		}
//		设置页数情况
		Map<String, Object> roomsMap = new HashMap<>();
		if (available.equals("可住房间")) {
//			查阅一共有多少页
			int countSum = roomDao.searchCountAva(roomBean);
			int pageSize = 10;
			inUtil.setCountSum(countSum);
			inUtil.setPageSize(pageSize);
			PageUtil outUtil = Page.getPageUtil(inUtil);
			roomsMap.put("pageUtil", outUtil);
//			查看users
			List<RoomBean> rooms = roomDao.findRoomByRoomAva(roomBean, outUtil);
			roomsMap.put("rooms", rooms);
			return roomsMap;
		}
//		查阅一共有多少页
		int countSum = roomDao.searchCountAll(roomBean);
		int pageSize = 10;
		inUtil.setCountSum(countSum);
		inUtil.setPageSize(pageSize);
		PageUtil outUtil = Page.getPageUtil(inUtil);
		roomsMap.put("pageUtil", outUtil);
//		查看users
		List<RoomBean> rooms = roomDao.findRoomByRoomAll(roomBean, outUtil);
		roomsMap.put("rooms", rooms);
		return roomsMap;
	}

}
