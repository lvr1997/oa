<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>OA办公管理系统-用户管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
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
<script type="text/javascript" src="${ctx}/resources/blockUI/jquery.blockUI.js"></script>
<script type="text/javascript">
  /** 文档加载完成*/
     $(function(){
	    	//$(document).ajaxStart($.blockUI({ css: { backgroundColor: '#11a9e2', color: '#fff' } , //message: '<h6>正在加载..</h6>'})).ajaxStop($.unblockUI);

			 /** 激活用户操作*/
    	  $("input[id^='checkUser_']").switchbutton({
              onChange: function(checked){
            	    var status = checked?"1":"0";
            		window.location = "${ctx}/identity/user/activeUser.jspx?userId="+this.value+"&status="+status
						+"&pageIndex=${pageModel.pageIndex}&name=${user.name}&phone=${user.phone}&dept.id=${user.dept.id}&job.code=${user.job.code}";
              }
          });

     })
</script>
</head>
<body style="overflow: hidden; width: 98%; height: 100%;" >
   	<!-- 工具按钮区 -->
	<form class="form-horizontal" 
			action="${ctx }/identity/user/selectUser.jspx" method="post" style="padding-left: 5px;" >
			<table class="table-condensed">
					<tbody>
					<tr>
					   <td>
						<input name="name" type="text" class="form-control"
							placeholder="姓名" value="${user.name}" >
						</td>
						<td>	
						<input type="text" name="phone" class="form-control"
							placeholder="手机号码" value="${user.phone}" >
						</td>
<!-- 						<td>	 -->
<!-- 						   <input type="text" class="form-control" placeholder="状态"> -->
<!-- 						</td> -->
						<td>	
						<select class="btn btn-default"
							placeholder="部门" id="deptSelect" name="dept.id">
							<option value="0">==请选择部门==</option>
						</select>
						</td>
						<td>	
						<select class="btn btn-default"
							placeholder="职位" id="jobSelect" name="job.code">
							<option value="0">==请选择职位==</option>
						</select>
						</td>
						<td>	
						<button type="submit" id="selectUser" class="btn btn-info"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
						<a  id="addUser" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加</a>
					    <a  id="deleteUser" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</a>
					 </td>
					</tr>
					</tbody>
				</table>
		</form>
 		<div class="panel panel-primary" style="padding-left: 10px;">
 			<div class="panel-heading" style="background-color: #11a9e2;">
				<h3 class="panel-title">用户信息列表</h3>
			</div>
			<div class="panel-body" >
				<table class="table table-bordered">
					<thead>
						<tr style="font-size: 12px;" align="center">
							<th style="text-align: center;"><input id="checkAll"
								type="checkbox" /></th>
							<th style="text-align: center;">账户</th>
							<th style="text-align: center;">姓名</th>
							<th style="text-align: center;">性别</th>
							<th style="text-align: center;">部门</th>
							<th style="text-align: center;">职位</th>
							<th style="text-align: center;">手机号码</th>
							<th style="text-align: center;">邮箱</th>
							<th style="text-align: center;">激活状态</th>
							<th style="text-align: center;">审核人</th>
							<th style="text-align: center;">操作</th>
						</tr>
					</thead>
					<c:forEach items="${requestScope.users}" var="user"
						varStatus="stat">
						<tr id="dataTr_${stat.index}" align="center">
							<td><input type="checkbox" name="box" id="box_${stat.index}"
								value="${user.userId}" /></td>
							<td>${user.userId}</td>
							<td>${user.name}</td>
							<td>${user.sex == 1 ? '男' : '女' }</td>
							<td>${ user.dept.name}</td>
							<td>${ user.job.name}</td>
							<td>${user.phone}</td>
							<td>${user.email}</td>
							<td>
								<c:if test="${user.status == 1 }">
									<input id="checkUser_${user.userId }" value="${user.userId }" name="status" class="easyui-switchbutton" data-options="onText:'激活',offText:'冻结'" checked>
								</c:if>
								<c:if test="${user.status != 1 }">
									<input id="checkUser_${user.userId }" value="${user.userId }" name="status" class="easyui-switchbutton" data-options="onText:'激活',offText:'冻结'" >
								</c:if>
						    </td>
								<td>${user.checker.name}</td>
							<td>
							   <span id="updateUser_${stat.index}"  class="label label-info"><a href="javascript:updateUser('${user.userId}')" style="color: white;">修改</a></span>
							   <span id="preUser_${stat.index}" class="label label-success"><a href="javascript:preUser('${user.userId}')"  style="color: white;">预览</a></span>
							</td>
						</tr>
					</c:forEach>
				</table>
				<!-- 分页标签区 -->
				<fkjava:pager pageIndex="${pageModel.pageIndex}"
					pageSize="${pageModel.pageSize}"
					recordCount="${pageModel.recordCount}"
					submitUrl="${ctx}/identity/user/selectUser.jspx?pageIndex={0}&name=${user.name}&phone=${user.phone}&dept.id=${user.dept.id}&job.code=${user.job.code}" />
			</div>

		</div>
		
		<div id="divDialog" style="display: none;" >
			 <!-- 放置一个添加用户的界面  -->
			 <iframe id="iframe" frameborder="0" style="width: 100%;height: 100%;"></iframe>
		</div>
	
</body>
</html>