package com.molly.service;

import java.util.Map;

import com.molly.bean.ClaBean;
import com.molly.util.PageUtil;

public interface IClassService {
	public Map<String, Object> showThisClasses(PageUtil inUtil);
	public String addClass(ClaBean claBean);
	public String deleteClass(ClaBean claBean);
}
