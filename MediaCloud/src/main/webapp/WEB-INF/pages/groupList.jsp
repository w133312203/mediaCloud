<%@page import="com.hm.utils.ApplicationUtil"%>
<%@page import="com.hm.domain.Group"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp"%>

	<c:forEach var="g" items="${gpList}"> 
		<c:set var="gpList" value='${g.groupList}' scope="request"></c:set>
		<c:set var="h" value='${g.hierarchy-1}' scope="request"></c:set>
		<li>
			
	    	<c:if test="${gpList==null}">
	    		<c:if test="${h==0}">
	    			<a href="javascript:void(0)"  style="color:#262626" name="${g.id}" >${g.title}</a>
	    		</c:if>
	    		<c:if test="${h>0}">
	    			<a href="javascript:void(0)"  style="padding: 3px 20px 3px ${35+(h-1)*15}px;color:#262626" name="${g.id}" >${g.title}</a>
	    		</c:if>
			</c:if>
			<c:if test="${gpList.size()==0}">
	    		<c:if test="${h==0}">
	    			<a href="javascript:void(0)"  style="color:#262626" name="${g.id}" >${g.title}</a>
	    		</c:if>
	    		<c:if test="${h>0}">
	    			<a href="javascript:void(0)"  style="padding: 3px 20px 3px ${35+(h-1)*15}px;color:#262626" name="${g.id}" >${g.title}</a>
	    		</c:if>
			</c:if>
			<c:if test="${gpList.size()>0}">
				<c:if test="${h==0}">
					<a href="javascript:void(0)"  data-stopPropagation="true" style="color:#262626" name="${g.id}" >${g.title}<span class="fa arrow"></span></a>
				</c:if>
				<c:if test="${h>0}">
					<a href="javascript:void(0)"  data-stopPropagation="true" style="padding: 3px 20px 3px ${35+(h-1)*15}px;color:#262626" name="${g.id}" >${g.title}<span class="fa arrow"></span></a>
	    		</c:if>
			</c:if>
			<ul class="nav nav-second-level">
	      		<c:import url="groupList.jsp"></c:import>
	      	</ul>
	     </li>
	</c:forEach>
