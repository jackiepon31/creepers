<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- Main content starts -->

<div class="content">


	<!-- Main bar -->
	<div class="mainbar">

		<!-- Page heading -->
		<div class="page-head">
			<h2 class="pull-left">
				<i class="icon-table"></i> 爬虫配置管理中心
			</h2>

			<!-- Breadcrumb -->
			<div class="bread-crumb pull-right">
				<a href="${ctx}"><i class="icon-home"></i> Home</a> <span
					class="divider">/</span> <a href="${ctx}" class="bread-current">Dashboard</a>
			</div>

			<div class="clearfix"></div>

		</div>
		<!-- Page heading ends -->

		<!-- Matter -->
		<div class="matter">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="widget wgreen">
							<div class="widget-head">
								<div class="pull-left">爬虫配置新增/修改</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="icon-remove"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>

							<div class="widget-content">
								<div class="padd">
									<!-- Form starts.  -->
									<form id="queryForm" class="form-horizontal" >
										<div class="form-group">
											<div class="col-md-12"></div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">爬虫任务类型</label>
											<div class="col-md-3">
												<select id="requestType" name="requestType"
													class="form-control">
													<c:forEach items="${taskTypeList}" var="taskType"
														varStatus="index">
														<option value="${taskType.value}"
															<c:if test="${taskType.value == requestType}"> selected</c:if>>
															${taskType.name}</option>
													</c:forEach>
												</select>
											</div>

											<label class="col-md-2 control-label">种子URL</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="rootUrl"
													name="rootUrl" value="${rootUrl}">
											</div>

										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">域名</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="domain"
													name="domain" value="${domain}">
											</div>
											<label class="col-md-2 control-label">用户浏览器代理设置</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="userAgent"
													name="userAgent" value="${userAgent}">
											</div>

										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">网关系统接口URL</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="cdUrl"
													name="cdUrl" value="${cdUrl}">
											</div>
											<label class="col-md-2 control-label">网关系统接口类型</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="cdRequestCode"
													name="cdRequestCode" value="${cdRequestCode}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">线程数</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="threadNum"
													name="threadNum" value="${threadNum}">
											</div>
											<label class="col-md-2 control-label">连接超时时间(ms)</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="timeOut"
													name="timeOut" value="${timeOut}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">任务开关</label>
											<div class="col-md-3">
												<select id="switchFlag" name="switchFlag"
													class="form-control">
													<option value='0' <c:if test="${switchFlag == '0'}"> selected</c:if>>关闭</option>
													<option value='1' <c:if test="${switchFlag == '1'}"> selected</c:if>>开启</option>
												</select>
											</div>
											<label class="col-md-2 control-label">动态代理开关</label>
											<div class="col-md-3">
												<select id="agentFlag" name="agentFlag" class="form-control">
													<option value='0' <c:if test="${agentFlag == '0'}"> selected</c:if>>关闭</option>
													<option value='1' <c:if test="${agentFlag == '1'}"> selected</c:if>>开启</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">重试连接次数</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="retryTimes"
													name="retryTimes" value="${retryTimes}">
											</div>
											<label class="col-md-2 control-label">头信息</label>
											<div class="col-md-3">
												<input class="form-control" type="textArea" id="headers"
													name="headers" value="${headers}">
											</div>
										</div>
										<div class="form-group">
											<div class="col-md-3">
												<button onclick="javascript:doModifyDetail()" type="button"
													class="btn btn-default">新增/修改</button>
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
				<!-- Matter ends -->
			</div>
			<!-- Mainbar ends -->

		</div>
	</div>
</div>

<script type="text/javascript">
	function doModifyDetail() {
		$.ajax({
			cache : false,
			type : "GET",
			url : '${ctx}/configCenter/saveAndUpdate',
			data : $("#queryForm").serializeArray(),
			error : function(request) {
			},
			success : function(data) {
				$("body").html(data);
			}
		});
	}
</script>

<div id="container"
	style="min-width: 310px; height: 400px; margin: 0 auto" hidden=""></div>
<div id="container_columnar"
	style="min-width: 310px; height: 400px; margin: 0 auto" hidden=""></div>
<div id="container_gauge"
	style="min-width: 310px; height: 400px; margin: 0 auto" hidden=""></div>
<div id="container_line"
	style="min-width: 310px; height: 400px; margin: 0 auto" hidden=""></div>
<div id="container_columnar_1"
	style="min-width: 310px; height: 400px; margin: 0 auto" hidden=""></div>