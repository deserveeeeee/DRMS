package com.molly.testService;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.molly.bean.ClaBean;
import com.molly.bean.RoomBean;
import com.molly.bean.StuBean;
import com.molly.bean.UserBean;
import com.molly.dao.IRoomDao;
import com.molly.service.IStuService;
import com.molly.util.Page;
import com.molly.util.PageUtil;

public class StuService {
	
	@Test
	public void reInsertStu(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuService service = ac.getBean(IStuService.class);
		System.out.println(service.reInsertStu());
	}
	
	
	@Test
	public void insertStu(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuService service = ac.getBean(IStuService.class);
		StuBean stuBean = new StuBean();
		ClaBean claBean = new ClaBean();
		claBean.setCid(3);
		stuBean.setCla(claBean);
		RoomBean roomBean = new RoomBean();
		roomBean.setRid(16);
		stuBean.setRoom(roomBean);
		stuBean.setImgs("/D:/imgs/23123.jpg");
		stuBean.setSgender("男");
		stuBean.setSname("汪文栋");
		stuBean.setStime("2018-07-21");
		stuBean.setSphone("18583672275");
		System.out.println(service.insertStu(stuBean));
	}
	
	
	
	@Test
	public void show(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuService service = ac.getBean(IStuService.class);
		PageUtil inUtil = new PageUtil();
		inUtil.setThisPage(1);
		System.out.println(service.showStu(inUtil));
	}
	
	
	
	@Test
	public void delete(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuService service = ac.getBean(IStuService.class);
		StuBean stuBean = new StuBean();
		stuBean.setSid(8);
		System.out.println(service.deleteStu(stuBean));
	}
	
	
	@Test
	public void reChangeRoom(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuService service = ac.getBean(IStuService.class);
		StuBean stuBean = new StuBean();
		stuBean.setSid(2);
		System.out.println(service.reChangeRoom(stuBean));
	}
	
	
	
	@Test
	public void changeRoom(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuService service = ac.getBean(IStuService.class);
		StuBean stuBean = new StuBean();
		stuBean.setSid(2);
		RoomBean roomBean = new RoomBean();
		roomBean.setRid(4);
		System.out.println(service.changeRoom(stuBean, roomBean));
	}
	
	
	@Test
	public void showStu(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuService service = ac.getBean(IStuService.class);
		StuBean stuBean = new StuBean();
		stuBean.setSid(2);
		System.out.println(service.showStu(stuBean));
	}
	
	
	
	
	@Test
	public void searchStu(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuService service = ac.getBean(IStuService.class);
		PageUtil inUtil = new PageUtil();
		inUtil.setThisPage(1);
		StuBean stuBean = new StuBean();
		ClaBean claBean = new ClaBean();
		claBean.setCname("");
		RoomBean roomBean = new RoomBean();
		roomBean.setRaddress("人");
		stuBean.setSname("");
		stuBean.setCla(claBean);
		stuBean.setRoom(roomBean);
		System.out.println(service.searchStu(stuBean, inUtil));
	}
}
