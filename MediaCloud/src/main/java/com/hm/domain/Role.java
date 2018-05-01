package com.hm.domain;

/**
 * 角色表
 */
public class Role {
	
	private Integer id;
	
	private String roleName;//角色名称
	
	private String description;//角色描述
	
	private Integer type = 0;//0 企业账户 1管理账户 2 管理员账户

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
