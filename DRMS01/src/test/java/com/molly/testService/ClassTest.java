package com.molly.testService;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.molly.bean.ClaBean;
import com.molly.service.IClassService;
import com.molly.util.PageUtil;

public class ClassTest {
	
	
	@Test
	public void insert(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IClassService service = ac.getBean(IClassService.class);
		ClaBean claBean = new ClaBean();
		claBean.setCname("四年级二班");
		claBean.setCteacher("蒋良材");
		claBean.setCtime("2018-10-13");
		System.out.println(service.addClass(claBean));
	}
	
	@Test
	public void delete(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IClassService service = ac.getBean(IClassService.class);
		ClaBean claBean = new ClaBean();
		claBean.setCid(1);
		System.out.println(service.deleteClass(claBean));
	}
	
	
	@Test
	public void show(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IClassService service = ac.getBean(IClassService.class);
		PageUtil inUtil = new PageUtil();
		inUtil.setThisPage(1);
		System.out.println(service.showThisClasses(inUtil));
	}
}
