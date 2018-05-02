package com.hm.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.service.BaseService;
import com.hm.domain.EnterpriseUserPassport;

public interface EnterpriseUserPassportService extends BaseService<EnterpriseUserPassport>{
	
	/**
	 * 查找企业用户通行证
	 * @param email 邮箱
	 * @param mobilenum 电话
	 * @param password 密码
	 * @return 企业用户账户信息
	 */
	public EnterpriseUserPassport findEnterpriseUserPassport(String email, String mobilenum, String password);
	
	/**
	 * 查找企业用户通行证
	 * @param email 邮箱
	 * @param mobileNum 电话
	 * @param password 密码
	 * @return 企业用户账户信息
	 */
	public List<EnterpriseUserPassport> checkEmail(String email, Integer id);
	
	/**
	 * 查找企业用户通行证
	 * @param email 邮箱
	 * @param mobileNum 电话
	 * @param password 密码
	 * @return 企业用户账户信息
	 */
	public List<EnterpriseUserPassport> checkMobileNum(String mobileNum, Integer id);
	
	/**
	 * 查询企业用户信息
	 * @param id 企业用户id
	 * @return
	 */
	public Map<String, Object> findInfo(Integer id);
	
	/**
	 * 企业用户列表
	 * @param search
	 * @param first
	 * @param max
	 * @return
	 */
	public List<Map> list(String search, Integer first, Integer max);
	
	/**
	 * 企业用户总数
	 * @param search
	 * @return
	 */
	public Integer count(String search);
	
	/**
	 * 更新账户状态
	 * @param search
	 * @return
	 */
	public void updateStatus(Integer status, Integer id);

}
