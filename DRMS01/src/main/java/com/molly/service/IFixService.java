package com.molly.service;

import java.util.List;

import com.molly.bean.FixBean;
import com.molly.bean.RoomBean;

public interface IFixService {
	
	public String insertFix(FixBean fixBean);
	public String handleFix(FixBean fixBean);
	public List<FixBean> showAll(RoomBean roomBean);
}
