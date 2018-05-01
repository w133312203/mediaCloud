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
	                    <h1 class="page-header">企业用户</h1>
	                </div>
	                <!-- /.col-lg-12 -->
	            </div>
            	<!-- /.row -->
            	<div class="row">
	                <div class="col-lg-12">
	                	<div id="toolbar">
           					 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addPropertyModal" onclick="add()">添加用户</button>
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
				               data-url="${ctx}/enterprise/list">
				            <thead>
				            <tr>
				                <th data-field="realName">姓名</th>
				                <th data-field="email">邮箱</th>
				                <th data-field="mobileNum">电话</th>
				                <th data-field="companyName">公司</th>
				                <th data-field="position">职位</th>
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
	
	    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">添加用户</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                      		<div class="row">
                      			<form action="${ctx}/enterprise/edit" id="editForm">
                      				<input type="hidden" name="id" id="id" />
	                       			<div class="form-group">
	                                	<label>姓名：</label>
	                                   	<input class="form-control" placeholder="姓名" id="realName" name="realName" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="姓名未填写">
	                                </div>
	                                <div class="form-group">
	                                	<label>邮箱：</label>
	                                	<span class="alertSpan" id="alertEmail1" value="" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="邮箱格式不正确，请填写正确格式的邮箱。例如：biz@ehui.net" ></span>
		                        		<span class="alertSpan" id="alertEmail2" value="" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="邮箱已经存在，请尝试其他的邮箱" ></span>
	                                   	<input class="form-control" placeholder="邮箱" id="email" name="email" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="邮箱未填写">
	                                </div>
	                                <div class="form-group">
	                                	<label>电话：</label>
	                                	<span class="alertSpan" id="alertMobile1" value="" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="手机格式不正确，请填写正确格式的手机。例如：13114555555" ></span>
		                        		<span class="alertSpan" id="alertMobile2" value="" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="手机已经存在，请尝试其他的手机" ></span>
	                                   	<input class="form-control" placeholder="电话" id="mobileNum" name="mobileNum" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="电话未填写">
	                                </div>
	                                <div class="form-group">
	                                	<label>公司：</label>
	                                   	<input class="form-control" placeholder="公司" id="companyName" name="companyName" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="公司未填写">
	                                </div>
	                                <div class="form-group">
	                                	<label>职位：</label>
	                                   	<input class="form-control" placeholder="职位" id="position" name="position" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="职位未填写">
	                                </div>
	                                <div class="form-group">
	                                	<label>密码：</label>
	                                   	<input class="form-control" placeholder="密码" id="password" name="password" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="密码未填写">
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
        
        <div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">删除用户</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                      		<div class="row">
                      			删除后将无法恢复，您确定删除所选的用户吗？
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
				$(this).popover('hide');
			}else {
				$(this).popover('show');
			} 
		});
	    
	    function add() {
	    	$('#myModalLabel').text('添加用户');
        	$('#realName').val("");
        	$("#id").val("");
        	$('#email').val("");
        	$('#mobileNum').val("");
        	$('#companyName').val("");
        	$('#position').val("");
        	$('#password').val("");
	    }
	    
	    function edit(row) {
	    	$('#myModalLabel').text('编辑用户');
        	$('#realName').val(row.realName);
        	$("#id").val(row.id);
        	$('#email').val(row.email);
        	$('#mobileNum').val(row.mobileNum);
        	$('#companyName').val(row.companyName);
        	$('#position').val(row.position);
        	$('#password').val(row.password);
	    }
	    
	    function del(id) {
	    	$("#delBtn").click(function(){
	    		window.location.href='${ctx}/enterprise/delete?id='+id;
	    	});
	    }
    	
    	function operateFormatter(value, row, index) {
    		return [
    	            '<a href="#" style="margin-right: 10px;" data-toggle="modal" data-target="#addModal" onclick="edit(\''+row+'\')">',
    	                '编辑',
    	            '</a>',
    	            '<a href="#" data-toggle="modal" data-target="#delModal" onclick="del(\''+row.id+'\')">',
    	                '删除',
    	            '</a>'
    	        ].join('');
    	}       
	    
	</script>
</html>
