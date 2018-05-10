package com.hm.domain;

/**
 * 用车车单
 * @author magic
 */
public class CarList {

	private Integer id;
	
	private String model;//车辆型号
	
	private String vin;//vin码
	
	private String color;//颜色
	
	private String status;//车辆状态
	
	private String location;//车辆所在地
	
	private String comm;//系统编码
	
	private String ins;//进里程数
	
	private String outs;//出里程数

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public String getIns() {
		return ins;
	}

	public void setIns(String ins) {
		this.ins = ins;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOuts() {
		return outs;
	}

	public void setOuts(String outs) {
		this.outs = outs;
	}
	
}
