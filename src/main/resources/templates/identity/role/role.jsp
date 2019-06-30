<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-角色管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/fkjava.ico" rel="shortcut icon" type="image/x-icon" />
	<link rel="stylesheet"
	href="${ctx }/resources/bootstrap/css/bootstrap.min.css" />
	<script type="text/javascript"
	src="${ctx }/resources/jquery/jquery-1.11.0.min.js"></script>
	<script type="text/javascript"
	src="${ctx }/resources/jquery/jquery-migrate-1.2.1.min.js"></script>
	<!-- 导入bootStrap的库 -->
	<script type="text/javascript"
	src="${ctx}/resources/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
	src="${ctx}/resources/easyUI/jquery.easyui.min.js"></script>
	<script type="text/javascript"
	src="${ctx}/resources/easyUI/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" href="${ctx}/resources/easyUI/easyui.css">
	<script type="text/javascript">
	

	</script>
</head>
<body style="overflow: hidden;width: 100%;height: 100%;padding: 5px;">
	<div>
		<div class="panel panel-primary">
			<!-- 工具按钮区 -->
			<div style="padding-top: 4px;padding-bottom: 4px;">
				<a  id="addRole" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加</a>
				<a  id="deleteRole" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</a>
			</div>
			
			<div class="panel-body">
				<table class="table table-bordered" style="float: right;">
					<thead>
					    <tr>
						<th style="text-align: center;"><input type="checkbox" id="checkAll"/></th>
						<th style="text-align: center;">名称</th>
						<th style="text-align: center;">备注</th>
						<th style="text-align: center;">操作</th>
						<th style="text-align: center;">创建日期</th>
						<th style="text-align: center;">创建人</th>
						<th style="text-align: center;">修改日期</th>
						<th style="text-align: center;">修改人</th>
						<th style="text-align: center;">操作</th>
						</tr>
					</thead>
					<c:forEach items="${roles}" var="role" varStatus="stat">
				         <tr align="center" id="dataTr_${stat.index}">
							<td><input type="checkbox" name="box" id="box_${stat.index}" value="${role.id}"/></td>
							<td>${role.name}</td>
							<td>${role.remark}</td>
							<td><span class="label label-success"><a href="${ctx}/identity/role/selectRoleUser.jspx?id=${role.id}" style="color: white;">绑定用户</a></span>&nbsp;
								<span class="label label-info"><a href="${ctx}/identity/popedom/mgrPopedom.jspx?id=${role.id}" style="color: white;">绑定操作</a></span></td>
							<td><fmt:formatDate value="${role.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${role.creater.name}</td>
							<td><fmt:formatDate value="${role.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${role.modifier.name}</td>
							<td>   <span class="label label-info"><a href="javascript:updateRole('${role.id}')">修改</a></span></td>
						</tr>
		   			 </c:forEach>
				</table>
				<!-- 分页标签区 -->
				<fkjava:pager pageIndex="${pageModel.pageIndex}" 
				  pageSize="${pageModel.pageSize}" 
				  recordCount="${pageModel.recordCount}" 
				  submitUrl="${ctx}/identity/role/selectRole.jspx?pageIndex={0}"/>
			</div>
			
		</div>
	</div>
    <!-- div作为弹出窗口 -->
    <div id="divDialog" style="overflow: hidden;">
		<iframe id="iframe" scrolling="no" frameborder="0" width="100%" height="100%" style="display:none;"></iframe>
	</div>
	
</body>
</html>