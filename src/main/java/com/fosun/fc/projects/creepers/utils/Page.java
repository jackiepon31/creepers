package com.fosun.fc.projects.creepers.utils;

public class Page {
	 private int currentPageNo; //当前页数
	 private int total;         //总页数
	 private int pageSize;      //每页显示条数
	 
	 // 得到总页数
	 public int getTotalPages() {
	  if (total == 0)
	   return 1;
	  return (total + pageSize - 1) / pageSize;
	 }
	 // 得到第一页页号
	 public int getFirstPageNo() {
	  return 1;
	 }
	 // 得到最后一页页号
	 public int getLastPageNo() {
	  return getTotalPages();
	 }
	 // 得到上一页页号
	 public int getPrePageNo() {
	  if (currentPageNo == 1) {
	   return 1;
	  }
	  return currentPageNo - 1;
	 }
	 // 得到下一页页号
	 public int getNextPageNo() {
	  if (currentPageNo == getTotalPages()) {
	   return currentPageNo;
	  }
	  return currentPageNo + 1;
	 }
	 
	 public int getCurrentPageNo() {
	  return currentPageNo;
	 }
	 public void setCurrentPageNo(int currentPageNo) {
	  this.currentPageNo = currentPageNo;
	 }
	 public int getTotal() {
	  return total;
	 }
	 public void setTotal(int total) {
	  this.total = total;
	 }
	 public int getPageSize() {
	  return pageSize;
	 }
	 public void setPageSize(int pageSize) {
	  this.pageSize = pageSize;
	 }

}
