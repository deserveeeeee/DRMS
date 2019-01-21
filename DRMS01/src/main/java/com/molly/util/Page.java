package com.molly.util;

public class Page {
	
	//	根据传入的thisPage得到PageUtil
	public static PageUtil getPageUtil(PageUtil pageUtil){
//		先查看所有的书籍的数量
//		int countSum = userDao.findAllCount();  在pageUtil里面
		int pageSize = pageUtil.getPageSize();   //外面使用的时候传入
		int countSum = pageUtil.getCountSum();
//		再计算分为多少页
		int allPages = (countSum%pageSize == 0)?(countSum/pageSize):(countSum/pageSize+1);
//		得到传入的thisPage
//		计算index
		int index = (pageUtil.getThisPage()-1)*pageSize;
		PageUtil util = new PageUtil();
		util.setCountSum(countSum);
		util.setThisPage(pageUtil.getThisPage());
		util.setAllPages(allPages);
		util.setIndex(index);
		util.setPageSize(pageSize);
		return util;
	}
}
