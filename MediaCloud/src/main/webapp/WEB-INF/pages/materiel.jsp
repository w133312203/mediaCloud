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
	                    <h1 class="page-header">物料</h1>
	                </div>
	                <!-- /.col-lg-12 -->
	            </div>
            	<!-- /.row -->
            	<div class="row">
	                <div class="col-lg-12">
	                	<div id="toolbar">
           					 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addModal" onclick="add()">添加物料</button>
           					 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#uploadModal" onclick="add()">导入表格</button>
           				</div>
           				<table id="table"
				               data-toggle="table"
				               data-toolbar="#toolbar"
				               data-pagination="true"
				               data-search="true"
				               data-trim-on-search="false"
				               data-cache="false"
				               data-show-refresh="true"
				               data-show-columns="true"
				               data-page-number="1"
				               data-page-size="10"
				               data-page-list="[10,20,30]"
				               data-side-pagination="server"
				               data-url="${ctx}/materiel/list">
				            <thead>
				            <tr>
				                <th data-field="items">项</th>
				                <th data-field="nums">数量</th>
				                <th data-field="bz">备注</th>
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
                       	<h4 class="modal-title" id="myModalLabel">编辑物料</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                      		<div class="row">
                      			<form action="${ctx}/materiel/edit" id="editForm" method="post">
                      				<input type="hidden" name="id" id="materielId" />
	                       			<div class="form-group">
	                                	<label>项：</label>
	                                   	<input class="form-control" placeholder="项" id="items" name="items" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="项未填写">
	                                </div>
	                                <div class="form-group">
	                                	<label>数量：</label>
	                                   	<input class="form-control" placeholder="数量" id="nums" name="nums" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="数量未填写">
	                                </div>
	                                <div class="form-group">
	                                	<label>备注：</label>
	                                   	<input class="form-control" placeholder="备注" id="bz" name="bz" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="备注未填写">
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
        <div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
       		<div class="modal-dialog" style="width:800px">
           		<div class="modal-content">
               		<div class="modal-header">
                   		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       	<h4 class="modal-title" id="myModalLabel">上传表格</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                    		<div class="row alert alert-danger">
                                存在不支持的格式或非视频文件，已自动过滤
                            </div>
                    		<div class="row upload_div" id="select_file_div">
                    			<form action="${ctx}/materiel/importExcel" id="importExcel" method="post" enctype="multipart/form-data">
	                      			<div>
	                      				<input type="file" name="file" id="files"/>
	                      				<p class="upload_text">支持3GP、ASF、AVI、DAT、DV、FLV、F4V、GIF、M2T、M3U8、M4V、MJ2、MJPEG、MKV、MOV、MP4、MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、SWF、TS、VOB、WMV、WEBM 等视频格式上传</p>
	                      				<button type="button" class="btn btn-primary" id="selectExcel" >选择表格</button>
	                      			</div>
                      			</form>
                      		</div>
                    	</div>
                   	</div>
                    <div class="modal-footer">
                    	<button type="button" id="uploadExcel" class="btn btn btn-primary disabled" >上传</button>
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
                       	<h4 class="modal-title" id="myModalLabel">删除车单</h4>
                   	</div>
                   	<div class="modal-body">
                    	<div class="container-fluid">
                      		<div class="row">
                      			删除后将无法恢复，您确定删除所选的车单吗？
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
		
		$("#selectExcel").click(function () {
			$("#files").click();
	    });
	    
	    $("#files").on("change",function(){
			file = this.files[0];
			if(file!=""&&file!=undefined) {
				$("#uploadExcel").removeClass("disabled");
			}
	    });
	    
	    $("#uploadExcel").click(function() {
	    	$("#importExcel").submit();
	    });
	    
	    function add() {
	    	$("#materielId").val("");
	    	$("#items").val("");
	    	$("#nums").val("");
	    	$("#bz").val("");
	    }
	    
	    function edit(index) {
	    	var data = $table.bootstrapTable('getData');
	    	$("#materielId").val(data[index].id);
	    	$("#items").val(data[index].items);
	    	$("#nums").val(data[index].nums);
	    	$("#bz").val(data[index].bz);
	    }
	    
	    function del(id) {
	    	$("#delBtn").click(function(){
	    		window.location.href='${ctx}/materiel/delete?id='+id;
	    	});
	    }
	    
	    function submit() {
	    	$("#editForm").submit();
	    }
    	
    	function operateFormatter(value, row, index) {
    		return [
    	            '<a href="#" style="margin-right: 10px;" data-toggle="modal" data-target="#addModal" onclick="edit(\''+index+'\')">',
    	                '编辑',
    	            '</a>',
    	            '<a href="#" data-toggle="modal" data-target="#delModal" onclick="del(\''+row.id+'\')">',
    	                '删除',
    	            '</a>'
    	        ].join('');
    	}        
	    
	</script>
</html>
