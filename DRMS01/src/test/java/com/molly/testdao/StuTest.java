package com.molly.testdao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.molly.bean.ClaBean;
import com.molly.bean.RoomBean;
import com.molly.bean.StuBean;
import com.molly.dao.IStuDao;
import com.molly.util.PageUtil;

public class StuTest {
	
	
	@Test
	public void insert(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuDao dao = ac.getBean(IStuDao.class);
		ClaBean cla = new ClaBean();
		cla.setCid(3);
		RoomBean room = new RoomBean();
		room.setRid(2);
		StuBean stuBean = new StuBean();
		stuBean.setCla(cla);
		stuBean.setRoom(room);
		stuBean.setSgender("女");
		stuBean.setSname("许柳思");
		stuBean.setSphone("18673529424");
		stuBean.setStime("2018-12-23");
		System.out.println(dao.insertStu(stuBean));
	}
	
	
	@Test
	public void sele(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuDao dao = ac.getBean(IStuDao.class);
		StuBean stuBean = new StuBean();
		ClaBean cla = new ClaBean();
		cla.setCid(1);
		RoomBean room = new RoomBean();
		room.setRid(1);
		stuBean.setSid(1);
		stuBean.setCla(cla);
		stuBean.setRoom(room);
		System.out.println(dao.findStuBySid(stuBean));
	}
	
	
	@Test
	public void update(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuDao dao = ac.getBean(IStuDao.class);
		RoomBean room = new RoomBean();
		room.setRid(1);
		StuBean stuBean = new StuBean();
		stuBean.setSid(1);
		System.out.println(dao.updateStuRoom(stuBean,room));
	}
	
	@Test
	public void show(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuDao dao = ac.getBean(IStuDao.class);
		StuBean stuBean = new StuBean();
		stuBean.setSid(1);
		System.out.println(dao.findStuBySid(stuBean));
	}
	
	
	@Test
	public void showThisPage(){
		PageUtil util = new PageUtil();
		util.setIndex(0);
		util.setPageSize(10);
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuDao dao = ac.getBean(IStuDao.class);
		System.out.println(dao.showThisStus(util));
	}
	
	
	@Test
	public void showByRid(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuDao dao = ac.getBean(IStuDao.class);
		RoomBean room = new RoomBean();
		room.setRid(1);
		System.out.println(dao.findStudentsByRid(1));
	}
	
	
	@Test
	public void find(){
		ClaBean claBean = new ClaBean();
		claBean.setCname("%二%");
		RoomBean roomBean = new RoomBean();
		roomBean.setRaddress("%科%");
		StuBean stuBean = new StuBean();
		stuBean.setSname("%许%");
		stuBean.setCla(claBean);
		stuBean.setRoom(roomBean);
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuDao dao = ac.getBean(IStuDao.class);
		PageUtil pageUtil = new PageUtil();
		pageUtil.setIndex(0);
		pageUtil.setPageSize(10);
		System.out.println(dao.findStuByStuBean(stuBean,pageUtil));
	}
	
	
	@Test
	public void delete(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IStuDao dao = ac.getBean(IStuDao.class);
		StuBean stuBean = new StuBean();
		stuBean.setSid(3);
		System.out.println(dao.deleteStu(stuBean));
	}
}
