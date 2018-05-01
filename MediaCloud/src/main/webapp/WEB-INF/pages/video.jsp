<%@page import="com.hm.utils.ApplicationUtil"%>
<%@page import="com.hm.domain.Group"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">

    	<title>媒体云</title>

	    <!-- Bootstrap Core CSS -->
	    <link href="${ctx}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

	    <!-- MetisMenu CSS -->
	    <link href="${ctx}/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
	    
	    <link href="${ctx}/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

	    <link href="${ctx}/dist/css/sb-admin-2.css" rel="stylesheet">
	    
	    <link href="${ctx}/vendor/bootstrap-table/css/bootstrap-table.css" rel="stylesheet">
	    
	    <style type="text/css">
	    	.upload_div {
	    		text-align: center;
    			border: 1px solid #e5e5e5;
    			border-radius: 5px;
    			padding: 80px 33px;
	    	}
	    	.upload_div p {
	    		font-size:12px;
	    		text-align: left;
	    		color:#aaa;
	    	}
	    	.alert{
	    		display:none;
	    	}
	    	#selGroupList>.active>a, #selGroupList>.active>a:hover, #selGroupList>.active>a:focus {
	    		color:#262626;
	    		background-color: #eeeeee;
	    	}
	    	#eGroupList>.active>a, #eGroupList>.active>a:hover, #eGroupList>.active>a:focus {
	    		color:#262626;
	    		background-color: #eeeeee;
	    	}
	    	.groupList {
	    		border: 1px solid #ccc;
			    border-radius: 4px;
			    padding: 6px 12px;
			    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
			    list-style-type:none;
	    	}
	    	
	    </style>
	    
	</head>

	<body>
    	<div id="wrapper">
        	<!-- Navigation -->
        	<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
	            <%@include file="/WEB-INF/header/header.jsp"%>
				<%@include file="/WEB-INF/menu/menu.jsp"%>  
        	</nav>

        	<div id="page-wrapper">
	            <div class="row">
	                <div class="col-lg-12">
	                    <h1 class="page-header">视频</h1>
	                </div>
	                <!-- /.col-lg-12 -->
	            </div>
            	<!-- /.row -->
            	<div class="row">
	                <div class="col-lg-12">
	                	<div id="toolbar">
           					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#uploadModal" onclick="initUploadDiv()">上传视频</button>
           					<%-- <button type="button" class="btn btn-primary" onclick="window.location.href='${ctx}/property/index/${group.id}/1'">添加属性</button> --%>
           				</div>
           				<table id="table"
				               data-toggle="table"
				               data-toolbar="#toolbar"
				               data-pagination="true"
				               data-search="true"
				               data-cache="false"
				               data-show-refresh="true"
				               data-show-columns="true"
				               data-click-to-select="true"
				               data-page-number="1"
				               data-page-size="10"
				               data-page-list="[10,20,30]"
				               data-side-pagination="server"
				               data-url="${ctx}/video/list?type=${group.type}">
				            <thead>
				            <tr>
				                <th data-field="state" data-checkbox="true"></th>
				                <th data-field="title">视频名称</th>
				                <th data-field="status" data-formatter="statusFormatter">视频状态</th>
				                <th data-field="groupTitle">分类</th>
				                <th data-field="createTime">创建时间</th>
				                <th data-formatter="operateFormatter">操作</th>
				            </tr>
				            </thead>
				        </table>
       				 </div>
	                <!-- /.col-lg-12 -->
	            </div>
	            <!-- /.row -->
        	</div>
        	<!-- /#page-wrapper -->
	    </div>
	    <!-- /#wrapper -->
		
	    <!-- jQuery -->
	    <script src="${ctx}/vendor/jquery/jquery.min.js"></script>
	
	    <!-- Bootstrap Core JavaScript -->
	    <script src="${ctx}/vendor/bootstrap/js/bootstrap.min.js"></script>
	
	    <!-- Metis Menu Plugin JavaScript -->
	    <script src="${ctx}/vendor/metisMenu/metisMenu.min.js"></script>
	
	    <script src="${ctx }/vendor/bootstrap-table/js/bootstrap-table.js"></script>
	    <script src="${ctx }/vendor/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>
	    <script src="${ctx }/vendor/bootstrap-table/js/bootstrap-table-mobile.min.js"></script>
	
	    <!-- Custom Theme JavaScript -->
	    <script src="${ctx}/dist/js/sb-admin-2.js"></script>
	    
	    <script src="${ctx}/vendor/aliyun-vod/aliyun-sdk.min.js"></script>
	    
	    <script src="${ctx}/vendor/aliyun-vod/vod-sdk-upload-1.0.6.min.js"></script>
	    
	    <script src="${ctx}/dist/js/vodUpload.js"></script>
	
	    <div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog" style="width:800px">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">上传视频</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                    		<div class="row alert alert-danger">
                                存在不支持的格式或非视频文件，已自动过滤
                            </div>
                    		<div class="row upload_div" id="select_file_div">
                      			<div>
                      				<p class="upload_text">支持3GP、ASF、AVI、DAT、DV、FLV、F4V、GIF、M2T、M3U8、M4V、MJ2、MJPEG、MKV、MOV、MP4、MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、SWF、TS、VOB、WMV、WEBM 等视频格式上传</p>
                      				<button type="button" class="btn btn-primary" id="selectVideo" >选择视频</button>
                      			</div>
                      		</div>
                      		<div class="row" style="display: none" id="video_info_div">
                       			<input type="file" name="file" id="files"/>
                       			<input type="hidden" name="videoId" id="videoId" />
                       			<input type="hidden" id="groupId" value="${group.id}" />
                       			<table id="videoTable">
					            	<thead>
					            		<tr>
							                <th data-field="title" data-width="170px" data-formatter="titleFormatter">视频标题</th>
							                <th data-field="formats" data-width="65px">视频格式</th>
							                <th data-field="size" data-width="100px">视频大小</th>
							                <th data-field="groupId" data-width="170px" data-formatter="groupFormatter">分类</th>
							                <th data-width="120px" data-formatter="voperateFormatter">操作</th>
					            		</tr>
					            	</thead>
				        		</table>
				        		<ul id="groupList" style="display: none">
					        		<c:forEach var="g" items="${groupList}">
					        			<li name="${g.id}">${g.title}</li>
					        			<ul>
					        				<c:forEach var="gs" items="${g.groupList}">
					        					<li name="${gs.id}">${gs.title}</li>
					        				</c:forEach>
					        			</ul>
					        		</c:forEach>
				        		</ul>
                      		</div>
                      		<div class="row" style="display: none" id="upload_info_div">
                       			<table id="uploadTable">
					            	<thead>
					            		<tr>
							                <th data-field="title" data-width="170px">视频标题</th>
							                <th data-field="formats" data-width="65px">视频格式</th>
							                <th data-field="size" data-width="100px">视频大小</th>
							                <th data-width="170px" data-formatter="uploadFormatter">上传状态</th>
							                <th data-width="120px" data-formatter="uoperateFormatter">操作</th>
					            		</tr>
					            	</thead>
				        		</table>
                      		</div>
                    	</div>
                   	</div>
                    <div class="modal-footer">
                    	<button type="button" id="uploadVideo" class="btn btn btn-primary disabled" >上传</button>
                        <button type="button" name="cld" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">编辑视频信息</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                    		<form action="${ctx}/video/edit" id="editForm" method="post" enctype="multipart/form-data">
                    			<input type="hidden" name="id" />
	                       		<input type="hidden" name="groupId" />
	                       		<input type="file" id="upload_headimage" accept="image/*" name="imageFile" style="display:none" />
	                    		<div class="row">
		                        	<div class="form-group" style="text-align: center;">
				                        <img src="" style="width: 300px;height: 150px" id="headimage">
				                        <p style="margin: 10px 0px">
				                        	<button type="button" name="button" onclick="uploadImage()" class="btn btn-primary btn-sm">上传视频封面</button>
				                        </p>
				                    </div>
				                </div>
	                      		<div class="row">
	                       			<div class="form-group">
	                                	<label>视频标题：</label>
	                                   	<input class="form-control" placeholder="视频标题" name="title" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="视频标题未填写">
	                                </div>
	                      		</div>
	                      		<div class="row">
	                       			<div class="form-group">
	                                	<label>视频分组：</label>
	                            		<li class="dropdown groupList">
	                            			<a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:#333;text-decoration:none;width: 100%;display: block;">
	                            				<span id="eGTitle">未分组</span> <i class="fa fa-caret-down"></i>
	                            			</a>
	                            			<ul class="dropdown-menu" id="eGroupList">
	                            				<li>
	                            					<a href="javascript:void(0)" onclick="eSelectGroup(this)" style="color:#262626" name="${group.id}" >未分组</a>
	                            				</li>
	                            				<c:forEach var="g" items="${groupList}"> 
	                            					<li>
	                            						<c:if test="${g.groupList.size()==0}">
	                            							<a href="javascript:void(0)" onclick="eSelectGroup(this)" name="${g.id}" >${g.title}</a>
	                            						</c:if>
	                            						<c:if test="${g.groupList.size()>0}">
	                            							<a href="javascript:void(0)" onclick="noClose()" data-stopPropagation="true" name="${g.id}" >${g.title}<span class="fa arrow"></span></a>
	                            						</c:if>
	                            						<ul class="nav nav-second-level">
	                            							<c:forEach var="g1" items="${g.groupList}"> 
	                            								<li>
	                            									<c:if test="${g1.groupList.size()==0}">
	                               										<a href="javascript:void(0)" onclick="eSelectGroup(this)" style="padding: 3px 20px 3px 35px;color:#262626" name="${g1.id}" >${g1.title}</a>
	                               									</c:if>
	                               									<c:if test="${g1.groupList.size()>0}">
	                               										<a href="javascript:void(0)" onclick="noClose()" data-stopPropagation="true" style="padding: 3px 20px 3px 35px;color:#262626" name="${g1.id}" >${g1.title}<span class="fa arrow"></span></a>
	                               									</c:if>
	                               									<ul class="nav nav-third-level">
	                               										<c:forEach var="g2" items="${g1.groupList}"> 
	                            											<li>
	                                  											<c:if test="${g2.groupList.size()==0}">
					                               									<a href="javascript:void(0)" onclick="eSelectGroup(this)" style="padding: 3px 20px 3px 50px;color:#262626" name="${g2.id}" >${g2.title}</a>
					                               								</c:if>
					                               								<c:if test="${g2.groupList.size()>0}">
					                               									<a href="javascript:void(0)" onclick="noClose()" data-stopPropagation="true" style="padding: 3px 20px 3px 50px;color:#262626" name="${g2.id}" >${g2.title}<span class="fa arrow"></span></a>
					                               								</c:if>
	                                  											<ul class="nav nav-third-level">
	                                  												<c:forEach var="g3" items="${g2.groupList}"> 
	                                  													<li>
			                                   												<a href="javascript:void(0)" onclick="eSelectGroup(this)" style="padding: 3px 20px 3px 65px;color:#262626" name="${g3.id}" >${g3.title}</a>
			                                   											</li>
	                                  												</c:forEach>
	                                  											</ul>
	                                  										</li>
	                               										</c:forEach>
	                               									</ul>
	                               								</li>
	                            							</c:forEach>
	                            						</ul>
	                            					</li>
	                            				</c:forEach>
	                            			</ul>
	                            		</li>
	                                </div>
	                      		</div>
	                      		<div class="row">
	                       			<div class="form-group">
	                                	<label>视频属性：</label>
	                                	<select class="form-control" name="propertyId" id="selProperty">
	                                		<option value="">未选择</option>
	                                		<c:forEach var="p" items="${propertyList}"> 
	                                			<option value="${p.id}">${p.title}</option>
	                                		</c:forEach>
	                                	</select>
	                                </div>
	                      		</div>
                      		</form>
                    	</div>
                   	</div>
                    <div class="modal-footer">
                    	<button type="button" id="saveBtn" onclick="submit()" class="btn btn btn-primary" >保存</button>
                        <button type="button" name="cld" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        
        <div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">删除视频</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                      		<div class="row">
                      			删除后将无法恢复，您确定删除所选的视频吗？
                      		</div>
                    	</div>
                   	</div>
                    <div class="modal-footer">
                    	<button type="button" id="delBtn" class="btn btn btn-primary" >删除</button>
                        <button type="button" name="cld" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
	</body>
	 
	<script type="text/javascript">
		var $table = $('#table');
		var $videoTable = $('#videoTable');
		var $uploadTable = $('#uploadTable');
		var isVideoTable = false;
		var isUploadTable = false;
		var file = "";
		var fileList = ['3GP','ASF','AVI','DAT','DV','FLV','F4V','GIF','M2T','M3U8','M4V','MJ2','MJPEG','MKV','MOV','MP4','MPE','MPG','MPEG','MTS','OGG','QT','RM','RMVB','SWF','TS','VOB','WMV','WEBM'];
		$("#selectVideo").click(function () {
			$("#files").click();
	    });
	    
	    function uploadImage() {
			$("#upload_headimage").click(); //隐藏了input:file样式后，点击头像就可以本地上传
		   	$("#upload_headimage").on("change",function(){
				var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
			   	if(objUrl) {
			   		$("#headimage").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
			    }
		    });
	    }
	    
	    $("#eGroupList").metisMenu();
	    
	    $("#files").on("change",function(){
			file = this.files[0];
			if(file!=""&&file!=undefined) {
				var fileName = file.name;
				var index = fileName.lastIndexOf(".");
				var formats = fileName.substring(index+1);
				var size = file.size;
				size = bytesToSize(size);
				formats = formats.toUpperCase();
				if(!IsInArray(fileList,formats)){
					openAlert();
					return false;
				}
				$("#select_file_div").hide();
				$("#upload_info_div").hide();
				var title = fileName.replace('.'+formats,'');
				var data = [{'title':title,'formats':formats,'size':size}];
				if(isVideoTable) {
					$videoTable.bootstrapTable('append',data);
				}else {
					$videoTable.bootstrapTable({data: data});
					isVideoTable = true;
				}
				$("#video_info_div").show();
				$("#uploadVideo").removeClass("disabled");
			}
	    });
	    
	    $("#uploadVideo").click(function() {
	    	if(file!="") {
	    		var title = $("#title").val();
	    		var fileName = file.name;
	    		var size = file.size;
	    		var index = fileName.lastIndexOf(".");
	    		var formats = fileName.substring(index+1);
	    		var groupId = $("#groupId").val();
	    		$.ajax({  
        			type:'POST',
        			url:'${ctx}/video/getUploadUrl',  
        			dataType:'json',
        			data:{"title":title,"fileName":fileName,"size":size,"formats":formats,"groupId":groupId},  
        			success:function(data){//返回json结果  
            			var jsonStr = eval(data);
            			if(jsonStr.CODE==10001) {
            				uploadAuth = jsonStr.uploadAuth;
	            			uploadAddress = jsonStr.uploadAddress;
	            			accessKeyId = jsonStr.accessKeyId;
							accessKeySecret = jsonStr.accessKeySecret;
							endpoint = jsonStr.endpoint;
	    					bucket = jsonStr.bucket;
							objectPre = jsonStr.objectPre;
							$("#videoId").val(jsonStr.videoId);
							var userData = '{"Vod":{"UserData":"{"IsShowWaterMark":"false","Priority":"7"}"}}';
							uploader.addFile(file, null, null, null, userData);
							$("#select_file_div").hide();
							$("#video_info_div").hide();
							$("#upload_info_div").show();
							size = bytesToSize(size);
							var data = [{'title':title,'formats':formats,'size':size}];
							if(isUploadTable) {
								$uploadTable.bootstrapTable('append',data);
							}else {
								$uploadTable.bootstrapTable({data: data});
								isUploadTable = true;
							}
							$("#uploadVideo").addClass("disabled");
	        				start();
            			}else {
            				alert(jsonStr.MSG);
            			}
        			}  
    			});  
	    	}
	    });
	    
	    function voperateFormatter(value, row, index) {
	    		
    		return [
    	            '<a href="#" onclick="initUploadDiv()">',
    	                '删除',
    	            '</a>'
    	        ].join('');
    	}
    	
    	function uoperateFormatter(value, row, index) {
	    		
    		return [
    	            '<a href="#" onclick="changeStatus(this)">',
    	                '取消',
    	            '</a>'
    	        ].join('');
    	}
    	
    	function changeStatus(obj) {
    		var list = uploader.listFiles();
    		var status = list[0].state;
    		if(status=='Uploading') {
    			uploader.stopUpload();
    			$(obj).text("恢复上传");
    		}else if(status=='Stoped') {
    			uploader.startUpload();
    			$(obj).text("取消");
    		}
    	}
    	
    	function titleFormatter(value, row, index) {
	    		
    		return '<input type="text" id="title" value="'+value+'"/>';
    	}
    	
    	function bytesToSize(bytes) {
    		if (bytes === 0) return '0 B';
    		var k = 1000, // or 1024
        	sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
        	i = Math.floor(Math.log(bytes) / Math.log(k)); 
   			return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
		}
		
		function initUploadDiv() {
			clearUpload();
			initUpload();
			$("#select_file_div").show();
			$("#video_info_div").hide();
			$("#upload_info_div").hide();
			if(isVideoTable) {
				$videoTable.bootstrapTable('removeAll');
			}
			if(isUploadTable) {
				$uploadTable.bootstrapTable('removeAll');
			}
			$("#groupId").val(${group.id});
		}
		
		function uploadFormatter(value, row, index) {
			return [
					'<div class="progress progress-striped active" style="width:74%;float:left;margin-bottom:0px">',
					'<div class="progress-bar progress-bar-success" role="progressbar" id="upLine" ',
		        	'aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" ',
		        	'style="width: 0%;"></div></div>',
		        	'<p style="margin:0px;float:left;margin-left:10px" id="upLineText">0%</p>'
		    	].join('');
		}
		
		function operateFormatter(value, row, index) {
    		
    		return [
    				'<a href="#" style="margin-right: 10px;">',
    	                '查看',
    	            '</a>',
    	            '<a href="#" style="margin-right: 10px;" data-toggle="modal" data-target="#editModal" onclick="editVideo(\''+row.id+'\',\''+row.headImage+'\',\''+row.title+'\',\''+row.groupId+'\',\''+row.groupTitle+'\',\''+row.propertyId+'\')">',
    	                '编辑',
    	            '</a>',
    	            '<a href="#" data-toggle="modal" data-target="#delModal" onclick="delVideo(\''+row.id+'\')">',
    	                '删除',
    	            '</a>'
    	        ].join('');
    	}
    	
    	function editVideo(id, headImage, title, groupId, groupTitle, propertyId) {
    		$("#editForm [name='id']").val(id);
    		$("#editForm [name='title']").val(title);
    		if(groupId=="undefined") {
    			$("#editForm [name='groupId']").val("");
    		}else {
    			$("#editForm [name='groupId']").val(groupId);
    		}
    		if(headImage=="undefined") {
    			$("#headimage").attr("src","");
    		}else {
    			if(headImage.indexOf("http")==-1) {
    				var prefix = '<%=ApplicationUtil.PICTURE_DOMAIN%>';
    				$("#headimage").attr("src",prefix+headImage);
    			}else {
    				$("#headimage").attr("src",headImage);
    			}
    		}
    		if(groupTitle=="undefined") {
    			$("#eGTitle").text("未分组");
    		}else {
    			$("#eGTitle").text(groupTitle);
    		}
    		if(propertyId==null||propertyId=="undefined") {
    			$("#selProperty").find("option").eq(0).attr("selected",true);
    		}else {
    			$("#selProperty").val(propertyId);
    		}
    	}
    	
    	function delVideo(id) {
	    	$("#delBtn").click(function(){
	    		window.location.href='${ctx}/video/delete?id='+id;
	    	});
	    }
    	
    	function statusFormatter(value, row, index) {
    	
    		if(value==0) {
    			return '上传中';
    		}else if(value==1) {
    			return '上传失败';
    		}else if(value==2) {
    			return '上传完成';
    		}else if(value==3) {
    			return '转码中';
    		}else if(value==4) {
    			return '转码失败';
    		}else if(value==5) {
    			return '审核中';
    		}else if(value==6) {
    			return '屏蔽';
    		}else if(value==7) {
    			return '正常';
    		}
    	}
    	
    	function groupFormatter(value, row, index) {
    		return [
    			'<div style="position:absolute">',
    			'<li class="dropdown" onclick="initMenu()" style="list-style-type:none">',
                    '<a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:#333;text-decoration:none">',
                        '<span id="gTitle">未分组</span> <i class="fa fa-caret-down"></i>',
                    '</a>',
                    '<ul class="dropdown-menu" id="selGroupList">',
                    	'<li>',
	                    	'<a href="javascript:void(0)" onclick="selectGroup(this)" style="color:#262626" name="${group.id}" >未分组</a>',
	                    '</li>',
                    	<%
                    		List<Map> list1 = (List<Map>)request.getAttribute("groupList");
                    		for(int i=0;i<list1.size();i++) {%>
                    		'<li>',
                    			<%
                    				List<Map> list2 = (List<Map>)list1.get(i).get("groupList");
                    				if(list2.size()>0) {%>
                    				'<a href="#" onclick="noClose()" data-stopPropagation="true" name="<%=list1.get(i).get("id")%>" ><%=list1.get(i).get("title") %><span class="fa arrow"></span></a>',
                    			<%}else {%>
                    				'<a href="#" onclick="selectGroup(this)" name="<%=list1.get(i).get("id")%>" ><%=list1.get(i).get("title") %></a>',
                    			<%}%>
	                            '<ul class="nav nav-second-level">',
	                            	<%for(int n=0;n<list2.size();n++) {%>
		                                '<li>',
		                                	<%
				                    			List<Map> list3 = (List<Map>)list2.get(n).get("groupList");
				                    			if(list3.size()>0) {%>
				                    			'<a href="#" onclick="noClose()" data-stopPropagation="true" name="<%=list2.get(n).get("id")%>" style="padding: 3px 20px 3px 35px;color:#262626"><%=list2.get(n).get("title")%><span class="fa arrow"></span></a>',
				                    		<%}else {%>
				                    			'<a href="#" onclick="selectGroup(this)" name="<%=list2.get(n).get("id")%>" style="padding: 3px 20px 3px 35px;color:#262626"><%=list2.get(n).get("title")%></a>',
				                    		<%}%>
		                                    '<ul class="nav nav-third-level">',
				                            	<%for(int m=0;m<list3.size();m++) {%>
					                                '<li>',
					                                	<%
				                    						List<Map> list4 = (List<Map>)list3.get(m).get("groupList");
				                    						if(list3.size()>0) {%>
				                    							'<a href="#" onclick="noClose()" data-stopPropagation="true" name="<%=list3.get(m).get("id")%>" style="padding: 3px 20px 3px 50px;color:#262626"><%=list3.get(m).get("title")%><span class="fa arrow"></span></a>',
				                    						<%}else {%>
				                    							'<a href="#" onclick="selectGroup(this)" name="<%=list3.get(m).get("id")%>" style="padding: 3px 20px 3px 50px;color:#262626"><%=list3.get(m).get("title")%></a>',
				                    						<%}%>
					                                    	'<ul class="nav nav-third-level">',
							                            	<%for(int z=0;z<list4.size();z++) {%>
								                                '<li>',
								                                    '<a href="#" onclick="selectGroup(this)" name="<%=list4.get(z).get("id")%>" style="padding: 3px 20px 3px 65px;color:#262626"><%=list4.get(z).get("title")%></a>',
								                                '</li>',
							                                <%}%>
							                            '</ul>',
					                                '</li>',
				                                <%}%>
				                            '</ul>',
		                                '</li>',
	                                <%}%>
	                            '</ul>',
                        	'</li>',
                        <%}%>
                    '</ul>',
                '</li></div>'
    			].join('');
    	}
    	
    	function noClose() {
    		$("ul.dropdown-menu").on("click", "[data-stopPropagation]", function(e) {  
        		e.stopPropagation();  
    		});  
    	}
    	
    	function selectGroup(obj) {
    		var groupId = $(obj).attr("name");
    		var title = $(obj).text();
    		$("#groupId").val(groupId);
    		$("#gTitle").text(title);
    	}
    	
    	function initMenu() {
    		$("#selGroupList").metisMenu();
    	}
		
		function clearUpload() {
			var files=document.getElementById('files');
			if(file.outerHTML) {
            	files.outerHTML=file.outerHTML;
            }else {
            	files.value="";
            }
			file = "";
            //uploader.cleanList();
		}
		
		function openAlert() {
			$(".alert").show();
			 setTimeout(function(){$(".alert").hide();},5000);
		}
		
		function IsInArray(arr,val){ 
			for(var i=0;i<arr.length;i++) {
				if(arr[i]==val) {
					return true;
				}
			}
			clearUpload();
			return false;
		} 
		
		function eSelectGroup(obj) {
			var groupId = $(obj).attr("name");
    		var title = $(obj).text();
    		$("#editForm [name='groupId']").val(groupId);
    		$("#eGTitle").text(title);
		}
		
		function submit() {
			var title = $("#title").val();
	    	var flag = true;
	    	if(title=="") {
	    		flag = false;
	    		$("#title").focus();
	    	}
	    	if(flag) {
	    		$("#editForm").submit();
	    	}
		}
		
		function getObjectURL(file) {
	    	var url = null ;
	    	if (window.createObjectURL!=undefined) { // basic
	    		url = window.createObjectURL(file) ;
	    	} else if (window.URL!=undefined) { // mozilla(firefox)
	    		url = window.URL.createObjectURL(file) ;
	    	} else if (window.webkitURL!=undefined) { // webkit or chrome
	    		url = window.webkitURL.createObjectURL(file) ;
	    	}
	    	return url ;
    	}
	    
	</script>
</html>
