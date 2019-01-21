package com.molly.testService;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.molly.bean.RoomBean;
import com.molly.service.IRoomService;
import com.molly.util.Page;
import com.molly.util.PageUtil;

public class RoomTest {
	
	
	@Test
	public void add(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IRoomService service = ac.getBean(IRoomService.class);
		RoomBean roomBean = new RoomBean();
		roomBean.setRaddress("永丰路16号");
		roomBean.setRcap(8);
		roomBean.setRlandlord("詹飞雨");
		roomBean.setRmoney(1100);
		roomBean.setRphone("18825149697");
		roomBean.setRplan("四居室");
		roomBean.setRstate("正常");
		roomBean.setRtime("2018-07-12");
		roomBean.setRtype("男生宿舍");
		roomBean.setRpayment("季付");
		System.out.println(service.addRoom(roomBean));
	}
	
	@Test
	public void delete(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IRoomService service = ac.getBean(IRoomService.class);
		RoomBean roomBean = new RoomBean();
		roomBean.setRid(1);
		System.out.println(service.deleteRoom(roomBean));
	}
	
	@Test
	public void find(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IRoomService service = ac.getBean(IRoomService.class);
		RoomBean roomBean = new RoomBean();
		roomBean.setRid(1);
		System.out.println(service.findRoom(roomBean));
	}
	
	
	@Test
	public void search(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IRoomService service = ac.getBean(IRoomService.class);
		RoomBean roomBean = new RoomBean();
		roomBean.setRaddress("%蜀%");
		String available = "可住房间";
		PageUtil oPageUtil = new PageUtil();
		oPageUtil.setThisPage(1);
		System.out.println(service.searchRooms(roomBean, available, oPageUtil));
	}
	
	
	@Test
	public void show(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IRoomService service = ac.getBean(IRoomService.class);
		PageUtil oPageUtil = new PageUtil();
		oPageUtil.setThisPage(1);
		System.out.println(service.showThisRooms(oPageUtil));
	}
}
