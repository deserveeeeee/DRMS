package com.molly.testdao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.molly.bean.ClaBean;
import com.molly.dao.IClassDao;
import com.molly.util.PageUtil;

public class ClassTest {
	
	
	@Test
	public void insert(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IClassDao dao = ac.getBean(IClassDao.class);
		ClaBean claBean = new ClaBean();
		claBean.setCname("一年级三班");
		claBean.setCteacher("姜伟娜");
		claBean.setCtime("2018-10-09");
		System.out.println(dao.insertClass(claBean));
	}
	
	@Test
	public void delete(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IClassDao dao = ac.getBean(IClassDao.class);
		ClaBean claBean = new ClaBean();
		claBean.setCid(2);
//		System.out.println(dao.deleteClass(claBean));
		System.out.println(dao.findClassByCid(claBean));
	}
	
	
	
	@Test
	public void show(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IClassDao dao = ac.getBean(IClassDao.class);
		PageUtil pageUtil = new PageUtil();
		pageUtil.setIndex(0);
		pageUtil.setPageSize(10);
		System.out.println(dao.showThisClasses(pageUtil));
	}
	
	
	
	@Test
	public void All(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IClassDao dao = ac.getBean(IClassDao.class);
		System.out.println(dao.showAllClasses());
	}
}
