<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>办公管理系统-更新职位</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
		<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
		<meta name="Description" content="网页信息的描述" />
		<meta name="Author" content="fkjava.org" />
		<meta name="Copyright" content="All Rights Reserved." />
   <link href="${ctx}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx }/resources/jquery/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="${ctx }/resources/jquery/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${ctx }/resources/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript"
	src="${ctx}/resources/easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/easyUI/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${ctx}/resources/easyUI/easyui.css">
		<script type="text/javascript">
			$(function(){
				
				// 如果有提示就弹出来 
				if("${tip}"){
					$.messager.show({
						title:'操作提示',
						msg:'<span style="color: #11a9e2;">${tip}</span>',
						timeout:3000,
						showType:'show'
					});
				}
				
				$("#btn_submit").click(function(){
					var name = $("#name");
					var url = $("#url");
					var remark = $("#remark");
					var msg = "";
					if ($.trim(name.val()) == ""){
						msg = "名称不能为空！";
						name.focus();
					}

					if (msg != ""){
						alert(msg);
					}else{
						$("#updateJobForm").submit();
					}
				});
			});
		</script>
	</head>
<body>
		<div class="container">
			<form id="updateJobForm" style="padding: 30px;" class="form-horizontal" action="${ctx }/hrm/job/updateJob.jspx" method="post">
				<input style="display: none;" name="code" value="${job.code}">
				<div class="form-group">
					<div>
						<input id="name" name="name" value="${job.name }" type="text" class="form-control" placeholder="职位名称" />
					</div>
				</div>

				<div class="form-group">
					<div >
					     <textarea id="remark" name="remark" class="form-control" rows="3" placeholder="备注信息">${job.remark}</textarea>
					</div>
				</div>
				
				<div class="form-group text-center">
						<a id="btn_submit" class="btn btn-info">修改</a>
						<button type="reset"  class="btn btn-danger">重置</button>
					   <div >
					  </div>
				</div>
			</form>
		</div>
</body>
</html>	