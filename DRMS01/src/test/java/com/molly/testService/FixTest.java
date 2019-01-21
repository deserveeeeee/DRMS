package com.molly.testService;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.molly.bean.FixBean;
import com.molly.bean.RoomBean;
import com.molly.service.IFixService;

public class FixTest {
	
	@Test
	public void handle(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IFixService service = ac.getBean(IFixService.class);
		FixBean fixBean = new FixBean();
		fixBean.setFid(5);
		System.out.println(service.handleFix(fixBean));
	}
	
	@Test
	public void insert(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IFixService service = ac.getBean(IFixService.class);
		FixBean fixBean = new FixBean();
		fixBean.setFcontent("天然气不能点燃");
		fixBean.setFtime("2019-01-12");
		fixBean.setRid(2);
		System.out.println(service.insertFix(fixBean));
	}
	
	
	@Test
	public void show(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IFixService service = ac.getBean(IFixService.class);
		RoomBean roomBean = new RoomBean();
		roomBean.setRid(1);
		System.out.println(service.showAll(roomBean));
	}
}
