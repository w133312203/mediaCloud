package com.hm.domain;
import java.util.Date;

/**
 * 图片
 * @author magic
 */
public class Picture {

	private Integer id;
	
	private Integer passportId;//账号关联字段
	
	private String title;//图片标题
	
	private String url;//图片地址
	
	private Date createTime = new Date();//创建时间
	
	private Integer groupId;//分组名称
	
	private Integer propertyId;//属性ID
	
	private Long size;//图片大小
	
	private Integer delType = 1;//0 删除 1 未删除

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Integer getDelType() {
		return delType;
	}

	public void setDelType(Integer delType) {
		this.delType = delType;
	}
	
}
