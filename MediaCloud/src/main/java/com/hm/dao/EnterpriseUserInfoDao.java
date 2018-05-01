package com.hm.dao;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.domain.EnterpriseUserInfo;

public interface EnterpriseUserInfoDao extends BaseDao<EnterpriseUserInfo>{
	
	/**
	 * 查找企业用户信息
	 * @param passportId 账号ID
	 */
	public EnterpriseUserInfo findEnterpriseUserInfoByPassportId(@Param("passportId")Integer passportId);
	
}
