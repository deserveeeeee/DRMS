package com.molly.testdao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.molly.bean.FixBean;
import com.molly.bean.RoomBean;
import com.molly.bean.UserBean;
import com.molly.dao.IFixDao;
import com.molly.dao.IRoomDao;
import com.molly.util.PageUtil;



public class RoomTest {
	
	
	@Test
	public void insert(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IRoomDao dao = ac.getBean(IRoomDao.class);
		RoomBean roomBean = new RoomBean();
		roomBean.setRaddress("华府大道南段");
		roomBean.setRcap(8);
		roomBean.setRlandlord("冯弘博");
		roomBean.setRpayment("季付");
		roomBean.setRphone("13964507501");
		roomBean.setRplan("3居室");
		roomBean.setRtime("2018-02-03");
		roomBean.setRtype("南生宿舍");
		System.out.println(dao.insertRoom(roomBean));
	}
	
	
	@Test
	public void show(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IRoomDao dao = ac.getBean(IRoomDao.class);
		PageUtil util = new PageUtil();
		util.setIndex(0);
		util.setPageSize(10);
		System.out.println(dao.showThisRooms(util));
	}
	
	
	
	@Test
	public void fix(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IFixDao dao = ac.getBean(IFixDao.class);
		FixBean fixBean = new FixBean();
		fixBean.setFcontent("热水器不能调温度");
		fixBean.setFtime("2019-01-18");
		fixBean.setRid(2);
		System.out.println(dao.insertFix(fixBean));
	}
	
	@Test
	public void udate(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IFixDao dao = ac.getBean(IFixDao.class);
		FixBean fixBean = new FixBean();
		fixBean.setFid(2);
		System.out.println(dao.deleteFix(fixBean));
	}
	
	
	
	@Test
	public void fixx(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IFixDao dao = ac.getBean(IFixDao.class);
		RoomBean roomBean = new RoomBean();
		roomBean.setRid(1);
		System.out.println(dao.findFixsByRid(roomBean));
	}
	
	
	@Test
	public void find(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IRoomDao dao = ac.getBean(IRoomDao.class);
		RoomBean roomBean = new RoomBean();
		roomBean.setRaddress("%科%");
		roomBean.setRstate("%%");
		roomBean.setRtype("%%");
		PageUtil pageUtil = new PageUtil();
		pageUtil.setIndex(0);
		pageUtil.setPageSize(10);
		List<RoomBean> rooms = dao.findRoomByRoomAll(roomBean,pageUtil);
		String available = "可住房间";
		if (available.equals("可住房间")) {
			List<RoomBean> reRooms = new ArrayList<>();
			for (RoomBean room : rooms) {
				if (room.getRcap() - room.getRused() >= 1) {
					reRooms.add(room);
				}
			}
			System.out.println(reRooms);
		}else {
			System.out.println(rooms);
		}
		
	}
	
	
	
	@Test
	public void room(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IRoomDao dao = ac.getBean(IRoomDao.class);
		RoomBean roomBean = new RoomBean();
		roomBean.setRid(1);
		System.out.println(dao.findRoomByRid(roomBean));
	}
	
	
	
}
