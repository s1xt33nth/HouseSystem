package com.wust.entity;

public class PageData {
	private int currPage = 1;
	private int pageSize = 5;
	private int maxCount;
	private int sumPages;

	
	public PageData() {
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public int getSumPages() {
		this.sumPages = maxCount % pageSize == 0 ? maxCount / pageSize : maxCount / pageSize + 1;
		return sumPages;
	}

	public void setSumPages(int sumPages) {
		this.sumPages = sumPages;
	}

}
