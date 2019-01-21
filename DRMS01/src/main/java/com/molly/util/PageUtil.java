package com.molly.util;

import javax.validation.constraints.Min;

public class PageUtil {
	@Min(value=1,message="{error}")
	private int thisPage;
	private int pageSize;
	private int countSum;
	private int index;
	private int allPages;   //总的页数
	public int getThisPage() {
		return thisPage;
	}
	public void setThisPage(int thisPage) {
		this.thisPage = thisPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCountSum() {
		return countSum;
	}
	public void setCountSum(int countSum) {
		this.countSum = countSum;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getAllPages() {
		return allPages;
	}
	public void setAllPages(int allPages) {
		this.allPages = allPages;
	}
	@Override
	public String toString() {
		return "PageUtil [thisPage=" + thisPage + ", pageSize=" + pageSize + ", countSum=" + countSum + ", index="
				+ index + ", allPages=" + allPages + "]";
	}
	
	
}
