package com.hm.domain;

/**
 * 属性
 * @author magic
 */
public class Property {

	private Integer id;
	
	private Integer passportId;//账号关联字段
	
	private String title;//属性名称
	
	private Integer level;//组别排序等级
	
	private Integer type;//属性分类 0 图片 1 视频 
	
	private Integer groupId;//属于哪组的属性
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
