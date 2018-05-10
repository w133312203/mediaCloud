package com.hm.domain;

/**
 * 用车车单
 * @author magic
 */
public class Materiel {

	private Integer id;
	
	private String items;//条目
	
	private String nums;//数量
	
	private String bz;//备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
