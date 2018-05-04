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

	    <link href="${ctx}/dist/css/sb-admin-2.css" rel="stylesheet">

	    <link href="${ctx}/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    
	    <link href="${ctx}/vendor/bootstrap-table/css/bootstrap-table.css" rel="stylesheet">
	    
	    <link href="${ctx}/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    
	    <style>
	    	.groupTitle {
	    		margin-top: -10px;
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
	                    <h1 class="page-header">分组</h1>
	                </div>
	                <!-- /.col-lg-12 -->
	            </div>
            	<!-- /.row -->
            	<div class="row">
            		<div class="col-lg-12 groupTitle">
            			<c:forEach var="g" items="${groupList}">
            				<a href="${ctx}/group/index/${g.id}">${g.title}</a> > 
            			</c:forEach>
            			分组
            		</div>
            	</div>
            	<div class="row">
	                <div class="col-lg-12">
	                	<div id="toolbar">
           					 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addGroupModal" onclick="addGroup()">添加组别</button>
           				</div>
           				<table id="table"
				               data-toggle="table"
				               data-toolbar="#toolbar"
				               data-pagination="true"
				               data-search="true"
				               data-cache="false"
				               data-show-refresh="true"
				               data-show-columns="true"
				               data-page-number="1"
				               data-page-size="10"
				               data-page-list="[10,20,30]"
				               data-side-pagination="server"
				               data-url="${ctx}/group/list?id=${group.id}">
				            <thead>
				            <tr>
				                <th data-field="title">分组名称</th>
				                <th data-field="level">分组排序</th>
				                <th data-field="uploadType" data-formatter="upFormatter">可上传类型</th>
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
	
	    <div class="modal fade" id="addGroupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">编辑分组</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                      		<div class="row">
                      			<form action="${ctx}/group/edit" id="groupForm">
                      				<input type="hidden" name="id" id="groupId" />
	                       			<input type="hidden" name="groupId" value="${group.id}" />
	                       			<input type="hidden" name="type" value="${group.type}" />
	                       			<input type="hidden" name="hierarchy" value="${group.hierarchy}" />
	                       			<div class="form-group">
	                                	<label>分组名称：</label>
	                                   	<input class="form-control" placeholder="分组名称" id="title" name="title" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="分组名称未填写">
	                                </div>
	                                <div class="form-group">
	                                	<label>排序级别：</label>
	                                   	<input class="form-control" placeholder="排序级别" id="level" name="level" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="只能输入数字，按数字从小到大排序">
	                                </div>
	                                <div class="form-group">
	                                	<label>上传类型：</label><br/>
                                        <label class="radio-inline">
                                            <input type="radio" name="uploadType" value="0" checked="checked">全部
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="uploadType" value="1">图片
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="uploadType" value="2">视频
                                        </label>
	                                </div>
                                </form>
                      		</div>
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
        
        <div class="modal fade" id="delGroupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">删除分组</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                      		<div class="row">
                      			删除后将无法恢复，您确定删除所选的分组吗？
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
		
		$("[data-toggle=popover]").focus(function () {
			if($(this).val()!='') {
				if($(this).attr("id")=="level") {
					var level = $("#level").val();
					if(level!=""&&!isRealNum(level)) {
						$(this).popover('show');
					}else {
						$(this).popover('hide');
					}
				}else {
					$(this).popover('hide');
				}
			}else {
				$(this).popover('show');
			} 
		});
	    
	    function addGroup() {
	    	$("#groupId").val("");
	    	$("#title").val("");
	    	$("#level").val("");
	    	$("[name='uploadType'][value='0']").prop("checked", "checked");
	    }
	    
	    function editGroup(id,title,level,uploadType) {
	    	$("#groupId").val(id);
	    	$("#title").val(title);
	    	$("#level").val(level);
	    	if(uploadType==""||uploadType==undefined) {
	    		$("[name='uploadType'][value='0']").prop("checked", "checked");
	    	}else {
	    		$("[name='uploadType'][value='"+useType+"']").prop("checked", "checked");
	    	}
	    }
	    
	    function delGroup(id) {
	    	$("#delBtn").click(function(){
	    		window.location.href='${ctx}/group/delete?id='+id;
	    	});
	    }
	    
	    function submit() {
	    	var title = $("#title").val();
	    	var level = $("#level").val();
	    	var flag = true;
	    	if(title=="") {
	    		flag = false;
	    		$("#title").focus();
	    	}
	    	if(level!=""&&!isRealNum(level)) {
	    		flag = false;
	    		$("#level").focus();
	    	}
	    	if(flag) {
	    		$("#groupForm").submit();
	    	}
	    }
	    
	    function upFormatter(value, row, index) {
	    	if(value=='0') {
	    		return "全部";
	    	}else if(value=='1') {
	    		return "图片";
	    	}else if(value=='2') {
	    		return "视频";
	    	}
	    }
    	
    	function operateFormatter(value, row, index) {
    		if(row.hierarchy==4) {
    			return [
    				'<a href="${ctx}/property/index/'+row.id+'/0" style="margin-right: 10px;">',
    	                '添加属性',
    	            '</a>',
    	            '<a href="#" style="margin-right: 10px;" data-toggle="modal" data-target="#addGroupModal" onclick="editGroup(\''+row.id+'\',\''+row.title+'\',\''+row.level+'\',\''+row.uploadType+'\')">',
    	                '编辑',
    	            '</a>',
    	            '<a href="#" data-toggle="modal" data-target="#delGroupModal" onclick="delGroup(\''+row.id+'\')">',
    	                '删除',
    	            '</a>'
    	        ].join('');
    		}else {
    			return [
    	            '<a href="${ctx}/group/index/'+row.id+'" style="margin-right: 10px;">',
    	                '添加分组',
    	            '</a>',
    	            '<a href="${ctx}/property/index/'+row.id+'/0" style="margin-right: 10px;">',
    	                '添加属性',
    	            '</a>',
    	            '<a href="#" style="margin-right: 10px;" data-toggle="modal" data-target="#addGroupModal" onclick="editGroup(\''+row.id+'\',\''+row.title+'\',\''+row.level+'\',\''+row.uploadType+'\')">',
    	                '编辑',
    	            '</a>',
    	            '<a href="#" data-toggle="modal" data-target="#delGroupModal" onclick="delGroup(\''+row.id+'\')">',
    	                '删除',
    	            '</a>'
    	        ].join('');
    		}
    	}
    	
    	function isRealNum(val){
    		if(val === "" || val ==null){
        		return false;
    		}
    		if(!isNaN(val)){
        		return true;
    		}else{
        		return false;
    		}
		}           
	    
	</script>
</html>
