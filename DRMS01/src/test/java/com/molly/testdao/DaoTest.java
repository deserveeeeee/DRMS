package com.molly.testdao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DaoTest {
	

	@Test
	public void testEncy(){
		Object obj = new SimpleHash("MD5", "123456", "molly", 1024);   //c4a452eafbe2f1d8dee4689d7b18335e  datadamin   15328822666
		Object obj1 = new SimpleHash("MD5", "123456", "王梦颖", 1024);   //1391c82dd6543fc7d8b26ddb3116fd02  superadmin  17683103983
		Object obj2 = new SimpleHash("MD5", "123456", "Amy", 1024);    //e5e286878d5fbc183136496d53899b46  roomadmin   13350967600
		System.out.println(obj);
		System.out.println(obj1);
		System.out.println(obj2);
		Object obj3 = new SimpleHash("MD5", "123456", "Tom", 1024);   //811845989a9e19b2878984d3d86a3c3e   dataadmin 15884112888
		System.out.println(obj3);
	}
	
	
	@Test
	public void names(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		String[] names = ac.getBeanDefinitionNames();
		for (String string : names) {
			System.out.println(string);
		}
		
		
	}
}
