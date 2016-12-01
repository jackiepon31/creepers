<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="content">


	<!-- Main bar -->
	<div class="mainbar">

		<!-- Page heading -->
		<div class="page-head">
			<h2 class="pull-left">
				<i class="icon-table"></i> 爬取任务管理
			</h2>

			<!-- Breadcrumb -->
			<div class="bread-crumb pull-right">
				<a href="${ctx}"><i class="icon-home"></i> Home</a> <span
					class="divider">/</span> <a href="${ctx}" class="bread-current">Dashboard</a>
			</div>

			<div class="clearfix"></div>

		</div>
		<!-- Matter -->
		<div class="matter">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="widget wgreen">
							<div class="widget-head">
								<div class="pull-left">新建爬取任务</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="icon-remove"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd">
									<!-- Form starts.  -->
									<form id="queryForm" class="form-horizontal"
										action="#" method="get">
										<div class="form-group">
											<div class="col-md-12"></div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">商户名称</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="merName"
													name="merName" value="${merName}">
											</div>
											<label class="col-md-2 control-label">任务类型</label>
											<div class="col-md-3">
												<select id="taskType" name="taskType"
													class="form-control">
													<c:forEach items="${taskTypeList }" var="item">
														<option value="${item.value }">${item.name }</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<div class="col-md-3">
												<a herf="#" onclick="addTask()" class="btn btn-default">增加任务</a>
												<button type="reset" class="btn btn-warning">重置</button>
											</div>
										</div>
									</form>
								</div>
							</div>
							<div class="widget-foot"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Matter ends -->
		<!-- Matter -->
		<div class="matter">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="widget wgreen">
							<div class="widget-head">
								<div class="pull-left">账号类爬取任务</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="icon-remove"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="widget-content">
								<div class="padd">
									<!-- Form starts.  -->
									<form id="accountQueryForm" class="form-horizontal"
										action="#" method="post">
										<div class="form-group">
											<div class="col-md-12"></div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">账号</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="loginName"
													name="loginName" value="${loginName}">
											</div>
											<label class="col-md-2 control-label">密码</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="password"
													name="password" value="${password}">
											</div>
											<label class="col-md-2 control-label">任务类型</label>
											<div class="col-md-3">
												<select id="accountTaskType" name="taskType"
													class="form-control">
													<c:forEach items="${loginTaskTypeList }" var="item" varStatus="index">
														<option value="${item.value }">${item.name }</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<div class="col-md-3">
												<a herf="#" onclick="addAccountTask()" class="btn btn-default">增加任务</a>
												<button type="reset" class="btn btn-warning">重置</button>
											</div>
										</div>
									</form>
								</div>
							</div>
							<div class="widget-foot"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Matter ends -->
	</div>
</div>
<script type="text/javascript">
//把字符串前后的空格去掉
function wipeOffBlankSpace(val){
   return (val.replace( /^\s*/,'')).replace( /\s*$/,'');
}
function addTask() {
	var merName=$("#merName").val();
	var taskType=$("#taskType").val();
	var url = encodeURI('${ctx}/creeperTask/addTask?merName=' + merName+'&taskType='+taskType,"UTF-8");
	merName = wipeOffBlankSpace(merName);
	if(merName.length==0 ){
		alert("请输入商户名称！");
		return;
	}
	$.ajax({
		cache : true,
		type : "GET",
		url : url,
		success : function(data) {
			alert(data.message);
		},
		error : function() {
			alert("设置重新爬取失败!");
		},
	});
}
function addAccountTask() {
	var loginName=$("#loginName").val();
	var password=$("#password").val();
	var taskType=$("#accountTaskType").val();
	loginName = wipeOffBlankSpace(loginName);
	password = wipeOffBlankSpace(password);
	if(loginName.length==0 ){
		alert("请输入账号！");
		return;
	}
	if(password.length==0 ){
		alert("请输入密码！");
		return;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : '${ctx}/creeperTask/addAccountTask',
		data : {'taskType':taskType,'loginName':loginName,'password':password},
		success : function(data) {
			alert(data.message);
		},
		error : function() {
			alert("设置重新爬取失败!");
		},
	});
}
</script>