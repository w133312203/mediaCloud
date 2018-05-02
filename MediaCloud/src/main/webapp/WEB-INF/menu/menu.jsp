<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp"%>
<div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        
                        <li>
                            <a href="#"><i class="fa fa-dashboard fa-fw"></i> AMG驾驶学院<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${ctx}/picture/index/1">图片</a>
                                </li>
                                <li>
                                    <a href="${ctx}/video/index/1">视频</a>
                                </li>
                                <li>
                                    <a href="${ctx}/group/index/1">分组</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> AMG车主俱乐部<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${ctx}/picture/index/2">图片</a>
                                </li>
                                <li>
                                    <a href="${ctx}/video/index/2">视频</a>
                                </li>
                                <li>
                                   <a href="${ctx}/group/index/2">分组</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-table fa-fw"></i> AMG精彩活动<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${ctx}/picture/index/3">图片</a>
                                </li>
                                <li>
                                    <a href="${ctx}/video/index/3">视频</a>
                                </li>
                                <li>
                                    <a href="${ctx}/group/index/3">分组</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-edit fa-fw"></i> AMG赛车运动<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${ctx}/picture/index/4">图片</a>
                                </li>
                                <li>
                                    <a href="${ctx}/video/index/4">视频</a>
                                </li>
                                <li>
                                    <a href="${ctx}/group/index/4">分组</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-wrench fa-fw"></i> AMG产品<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${ctx}/picture/index/5">图片</a>
                                </li>
                                <li>
                                    <a href="${ctx}/video/index/5">视频</a>
                                </li>
                                <li>
                                    <a href="${ctx}/group/index/5">分组</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i> AMG用车记录<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">市场活动用车车单</a>
                                </li>
                                <li>
                                    <a href="#">车辆零部件</a>
                                </li>
                                <li>
                                    <a href="#">AMG精品</a>
                                </li>
                                <li>
                                    <a href="#">AMG物料</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <c:if test="${sessionScope.euserPassport.type==1}">
                        	<li>
                            	<a href="${ctx}/enterprise/index"><i class="fa fa-sitemap fa-fw"></i> 企业用户管理</a>
                        	</li>
                        </c:if>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->