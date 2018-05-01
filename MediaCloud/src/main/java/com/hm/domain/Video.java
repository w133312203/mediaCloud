package com.hm.domain;
import java.util.Date;

/**
 * 视频
 * @author magic
 */
public class Video {

	private Integer id;
	
	private Integer passportId;//账号关联字段
	
	private String title;//视频标题
	
	private String fileName;//文件名称
	
	private String headImage;//视频头图
	
	private String formats;//视频格式
	
	private long size;//视频容量
	
	private String time;//视频时长
	
	private Integer status = 0 ;//视频状态
	
	private Date createTime = new Date();//创建时间
	
	private String od_address;//视频原始地址
	
	private String ld_address;//视频标清地址
	
	private Integer groupId;//分组名称
	
	private Integer propertyId;//属性Id
	
	private String videoId;//阿里云字段
	
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

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getFormats() {
		return formats;
	}

	public void setFormats(String formats) {
		this.formats = formats;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getOd_address() {
		return od_address;
	}

	public void setOd_address(String od_address) {
		this.od_address = od_address;
	}

	public String getLd_address() {
		return ld_address;
	}

	public void setLd_address(String ld_address) {
		this.ld_address = ld_address;
	}

	public Integer getDelType() {
		return delType;
	}

	public void setDelType(Integer delType) {
		this.delType = delType;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}
	
}
