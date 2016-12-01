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
				<i class="icon-table"></i> 批处理任务新增
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
								<div class="pull-left">批处理任务新增</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="icon-remove"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>

							<div class="widget-content">
								<div class="padd">
									<!-- Form starts.  -->
									<form id="queryForm" class="form-horizontal">
										<div class="form-group">
											<table class="table table-striped table-bordered table-hover">
												<tbody>
													<tr>
														<td>任务名称:</td>
														<td></td>
														<td>任务类型:</td>
														<td></td>
													</tr>
													<tr>
														<td>任务类名:</td>
														<td></td>
														<td>用户浏览器代理设置:</td>
														<td>${userAgent}<input type="hidden" id="userAgent"
															name="userAgent" value="${userAgent}"></td>
													</tr>
													<tr>
														<td>网关系统接口URL:</td>
														<td>${cdUrl}<input type="hidden" id="cdUrl"
															name="cdUrl" value="${cdUrl}"></td>
														<td>网关系统接口类型:</td>
														<td>${cdRequestCode}<input type="hidden"
															id="cdRequestCode" name="cdRequestCode"
															value="${cdRequestCode}"></td>
													</tr>
													<tr>
														<td>线程数:</td>
														<td>${threadNum}<input type="hidden" id="threadNum"
															name="threadNum" value="${threadNum}"></td>
														<td>连接超时时间(ms):</td>
														<td>${timeOut}<input type="hidden" id="timeOut"
															name="timeOut" value="${timeOut}"></td>
													</tr>
													<tr>
														<td>任务开关:</td>
														<td>${switchFlag}<input type="hidden" id="switchFlag"
															name="switchFlag" value="${switchFlag}"></td>
														<td>动态代理开关:</td>
														<td>${agentFlag}<input type="hidden" id="agentFlag"
															name="agentFlag" value="${agentFlag}"></td>
													</tr>
													<tr>
														<td>重试连接次数:</td>
														<td>${retryTimes}<input type="hidden" id="retryTimes"
															name="retryTimes" value="${retryTimes}"></td>
														<td>头信息:</td>
														<td>${headers}<input type="hidden" id="headers"
															name="headers" value="${headers}"></td>
													</tr>
												</tbody>
											</table>


										</div>
										<div class="form-group">
											<div class="col-md-3">
												<button onclick="entryModifyDetail()" type="button"
													class="btn btn-default">修改</button>
											</div>
											<div class="col-md-3">
												<button onclick="returnConfigCenter()" type="button"
													class="btn btn-default">返回配置管理</button>
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
	function entryModifyDetail() {
		$.ajax({
			cache : false,
			type : "GET",
			url : '${ctx}/configCenter/doModify',
			data : $("#queryForm").serializeArray(),
			error : function(request) {
			},
			success : function(data) {
				$("body").html(data);
			}
		});
	}
	function returnConfigCenter(){
		window.location.href = "${ctx}/configCenter/init";
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