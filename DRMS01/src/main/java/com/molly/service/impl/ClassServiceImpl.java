package com.molly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.molly.bean.ClaBean;
import com.molly.bean.UserBean;
import com.molly.dao.IClassDao;
import com.molly.dao.IStuDao;
import com.molly.service.IClassService;
import com.molly.util.Page;
import com.molly.util.PageUtil;

@Service
public class ClassServiceImpl implements IClassService {
	@Autowired
	private IClassDao classDao;
	@Autowired
	private IStuDao stuDao;
	
//	展示某一页的班级信息
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public Map<String, Object> showThisClasses(PageUtil inUtil) {
		Map<String, Object> classesMap = new HashMap<>();
//		查阅一共有多少页
		int countSum = classDao.findAllCount();
		int pageSize = 10;
		inUtil.setCountSum(countSum);
		inUtil.setPageSize(pageSize);
		PageUtil outUtil = Page.getPageUtil(inUtil);
		classesMap.put("pageUtil", outUtil);
//		查看users
		List<ClaBean> classes = classDao.showThisClasses(outUtil);
		classesMap.put("classes", classes);
		return classesMap;
	}
	
//	添加班级
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public String addClass(ClaBean claBean) {
//		系统帮助做了非空校验
		int result = classDao.insertClass(claBean);
		if (result > 0) {
			return "success";
		}
		return "fail";
	}

	
//	删除班级
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public String deleteClass(ClaBean claBean) {
//		先自己做非空和有效性校验
		if (claBean == null) {
				return "非法操作";
		}
		int cid = claBean.getCid();
		if (cid < 1) {
			return "非法操作";
		}
//		这个班是否还有学生的判断
		int students = stuDao.findStudentsByCid(claBean);
		if (students > 0) {
			return "该班级还有学生，不能删除";
		}
		int result = classDao.deleteClass(claBean);
		if (result > 0) {
			return "success";
		}
		return "fail";
	}

}
