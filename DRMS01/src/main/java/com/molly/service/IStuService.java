package com.molly.service;

import java.util.List;
import java.util.Map;

import com.molly.bean.RoomBean;
import com.molly.bean.StuBean;
import com.molly.util.PageUtil;

public interface IStuService {
	public Map<String, Object> reInsertStu();
	public String insertStu(StuBean stuBean);
	public Map<String, Object> showStu(PageUtil inUtil);
	public String deleteStu(StuBean stuBean);
	public List<RoomBean> reChangeRoom(StuBean stuBean);
	public String changeRoom(StuBean stuBean,RoomBean roomBean);
	public StuBean showStu(StuBean stuBean);
	public Map<String, Object> searchStu(StuBean stuBean,PageUtil inUtil);
}
