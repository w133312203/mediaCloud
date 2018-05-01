package com.hm.domain;

/**
 * 分组
 * @author magic
 */
public class Group {

	private Integer id;
	
	private Integer passportId;//账号关联字段
	
	private String title;//组别名称
	
	private Integer level;//组别排序等级
	
	private Integer groupId;//上级组别ID
	
	private Integer hierarchy;//层级
	
	private Integer type;//组别分类 0 驾驶学院 1 车主俱乐部 2 精彩活动 3 赛车运动 4 产品 5 用车记录
	
	private Integer uploadType = 0;//上传类型 0 全部 1 图片 2 视频
	
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

	public Integer getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}

	public Integer getUploadType() {
		return uploadType;
	}

	public void setUploadType(Integer uploadType) {
		this.uploadType = uploadType;
	}
	
}
