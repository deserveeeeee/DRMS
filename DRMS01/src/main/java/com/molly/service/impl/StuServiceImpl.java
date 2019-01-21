package com.molly.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.molly.bean.ClaBean;
import com.molly.bean.RoomBean;
import com.molly.bean.StuBean;
import com.molly.bean.UserBean;
import com.molly.dao.IClassDao;
import com.molly.dao.IRoomDao;
import com.molly.dao.IStuDao;
import com.molly.service.IStuService;
import com.molly.util.Page;
import com.molly.util.PageUtil;

@Service
public class StuServiceImpl implements IStuService {
		
	@Autowired
	private IStuDao stuDao;
	@Autowired
	private IRoomDao roomDao;
	@Autowired
	private IClassDao classDao;
	
//	预插入学生信息
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public Map<String, Object> reInsertStu() {
		Map<String, Object> reInsertMap = new HashMap<>();
//		前端点击学生入住以后触发这个方法，并且返回房间和班级。
//		至于男女宿舍是在前端，根据用户的选择来改变，所以直接返回所有的可以住的宿舍信息和所有的班级信息
		List<ClaBean> classes = classDao.findAllClasses();
		reInsertMap.put("classes", classes);
		List<RoomBean> rooms = roomDao.findAllRooms();
		reInsertMap.put("rooms", rooms);
		return reInsertMap;
	}
	
//	插入新的学生信息
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public String insertStu(StuBean stuBean) {
//		系统辅助非空校验：需要大部分信息API
//		先校验是否有这个班级
		ClaBean claBean = classDao.findClassByCid(stuBean.getCla());
		if (claBean == null) {
			return "非法操作";
		}
//		再校验是否能住在这个性别的这个房间里面还能住人
		RoomBean roomBean = roomDao.findRoomByRid(stuBean.getRoom());
		if (roomBean == null) {
			return "非法操作";
		}
		if (roomBean.getRhave() == 0) {
			return "该房间已经住满，请另外选择房间";
		}
		String sGender = stuBean.getSgender();
		System.out.println(sGender);
		System.out.println(roomBean.getRtype());
		if ((sGender.equals("男") && roomBean.getRtype().equals("男生宿舍"))
			||
			(sGender.equals("女") && roomBean.getRtype().equals("女生宿舍"))) {
//			现在可以办理入住了
			int result = stuDao.insertStu(stuBean);
			roomDao.addRused(roomBean);
			return "success";
		}
		return "房间类型选择错误";
	}
	
	
//	展示某一页的学生信息
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public Map<String, Object> showStu(PageUtil inUtil) {
//		需要这是第几页的信息API
		Map<String, Object> stusMap = new HashMap<>();
//		查阅一共有多少页
		int countSum = stuDao.findAllCount();
		int pageSize = 10;
		inUtil.setCountSum(countSum);
		inUtil.setPageSize(pageSize);
		PageUtil outUtil = Page.getPageUtil(inUtil);
		stusMap.put("pageUtil", outUtil);
//		查看users
		List<StuBean> students = stuDao.showThisStus(outUtil);
		stusMap.put("students", students);
		return stusMap;
	}
	
//	删除某一个学生，办理学生退房手续
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public String deleteStu(StuBean stuBean) {
//		需要学生id：API
		if (stuBean == null) {
			return "非法操作";
		}
		if (stuBean.getSid() == 0) {
			return "非法操作";
		}
		StuBean stuBean2 = stuDao.findStuBySid(stuBean);
		if (stuBean2 == null) {
			return "非法操作";
		}
		int result = stuDao.deleteStu(stuBean2);
		if (result > 0) {
			roomDao.reRused(stuBean2.getRoom());
			return "success";
		}
		return "fail";
	}
	
	
//	预改变房间
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public List<RoomBean> reChangeRoom(StuBean stuBean) {
//		需要学生的ID：API
		if (stuBean == null) {
			return null;
		}
		if (stuBean.getSid() == 0) {
			return null;
		}
		StuBean stuBean2 = stuDao.findStuBySid(stuBean);
		if (stuBean2 == null) {
			return null;
		}
		String sGender = stuBean2.getSgender();
		int rid = stuBean2.getRoom().getRid();
		List<RoomBean> rooms = roomDao.findAllRooms();
		List<RoomBean> reRooms = new ArrayList<>();
		for (RoomBean roomBean : rooms) {
			if ((sGender.equals("男") && roomBean.getRtype().equals("男生宿舍"))
					||
				(sGender.equals("女") && roomBean.getRtype().equals("女生宿舍"))) {
				reRooms.add(roomBean);
			}
			if (roomBean.getRid() == rid) {
				reRooms.remove(roomBean);
			}
		}
		return reRooms;
	}
	
//	某个学生换房间
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public String changeRoom(StuBean stuBean,RoomBean roomBean) {
//		需要学生的ID和预备换过去的那个房间rid，并且分别封装为两个对象传入后台：API
//		控制校验
		if (stuBean == null || roomBean == null) {
			return "非法操作";
		}
//		传参校验
		if (stuBean.getSid() == 0 || roomBean.getRid() == 0) {
			return "非法操作";
		}
//		是否有这个学生校验
		StuBean stuBean2 = stuDao.findStuBySid(stuBean);
		RoomBean roomBean2 = roomDao.findRoomByRid(roomBean);
		if (stuBean2 == null || roomBean2 == null) {
			return "非法操作";
		}
		int oldRid = stuBean2.getRoom().getRid();
		int newRid = roomBean2.getRid();
		if (oldRid == newRid) {
			return "还是原房间，并未改变";
		}
		String sGender = stuBean2.getSgender();
		String rType = roomBean2.getRtype();
		if ((sGender.equals("男") && rType.equals("男生宿舍"))
				||
			(sGender.equals("女") && rType.equals("女生宿舍"))) {
//		进行换房间：换该学生的房间还有换相对应的宿舍的数量，让旧房间少掉一个人，让新房价增加一个人
			stuDao.updateStuRoom(stuBean2, roomBean2);
			roomDao.reRused(stuBean2.getRoom());
			roomDao.addRused(roomBean2);
			return "success";
		}
		return "非法操作";
	}
	
	
//	展示某个学生的详细信息
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public StuBean showStu(StuBean stuBean) {
//		需要学生的ID：API
//		控制校验
		if (stuBean == null) {
			return null;
		}
//		传参校验
		if (stuBean.getSid() == 0) {
			return null;
		}
		StuBean stuBean2 = stuDao.findStuBySid(stuBean);
		return stuBean2;
	}
	
	
//	通过字段检索某些学生并且分页显示
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public Map<String, Object> searchStu(StuBean stuBean, PageUtil inUtil) {
//		学生姓名、班级、所在房间的中文字段
//		控制校验
		if (stuBean == null || inUtil == null) {
			return null;
		}
		if (inUtil.getThisPage() == 0) {
			return null;
		}
//		字段配置
//		配置学生姓名
		if (stuBean.getSname() == null || stuBean.getSname().length() == 0) {
			stuBean.setSname("%%");
		}else {
			String sName = "%" + stuBean.getSname() + "%";
			stuBean.setSname(sName);
		}
//		配置班级名
		ClaBean claBean = new ClaBean();
		String cName = "";
		if (stuBean.getCla() == null) {
			cName = "%%";
		}else {
			if (stuBean.getCla().getCname() == null || stuBean.getCla().getCname().length() == 0) {
				cName = "%%";				
			}else {
				cName = "%" + stuBean.getCla().getCname() + "%";
			}
		}
		claBean.setCname(cName);
		stuBean.setCla(claBean);
//		配置房间名
		RoomBean roomBean = new RoomBean();
		String raddress = "";
		if (stuBean.getRoom() == null) {
			raddress = "%%";
		}else {
			if (stuBean.getRoom().getRaddress() == null || stuBean.getRoom().getRaddress().length() == 0) {
				raddress = "%%";				
			}else {
				raddress = "%" + stuBean.getRoom().getRaddress() + "%";
			}
		}	
		roomBean.setRaddress(raddress);
		stuBean.setRoom(roomBean);
//		设置页数情况
		Map<String, Object> stusMap = new HashMap<>();
//		查阅一共有多少页
		int countSum = stuDao.searchCount(stuBean);
		int pageSize = 10;
		inUtil.setCountSum(countSum);
		inUtil.setPageSize(pageSize);
		PageUtil outUtil = Page.getPageUtil(inUtil);
		stusMap.put("pageUtil", outUtil);
//		查看users
		List<StuBean> students = stuDao.findStuByStuBean(stuBean, outUtil);
		stusMap.put("students", students);
		return stusMap;
	}


	
}
