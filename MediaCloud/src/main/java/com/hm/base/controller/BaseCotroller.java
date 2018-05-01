package com.hm.base.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.hm.domain.EnterpriseUserPassport;

public class BaseCotroller {
	
	@Autowired
	protected HttpServletRequest request;
	
	/**
	 * 向页面输出
	 */
	public void printWriter(HttpServletResponse response, Object object) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println(object);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected EnterpriseUserPassport getSessionPassport() {
		EnterpriseUserPassport sessionPassport = (EnterpriseUserPassport) request.getSession().getAttribute("euserPassport");
		return sessionPassport;
	}
}
