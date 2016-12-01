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
				<i class="icon-table"></i> 批处理任务编辑
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
									<form id="queryForm" class="form-horizontal"
										action="${ctx}/job/doSave" method="get">
										<div class="form-group">
											<div class="col-md-12"></div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">任务名称</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="jobName"
													name="jobName" value="${jobName}">
											</div>

											<label class="col-md-2 control-label">任务类型</label>
											<div class="col-md-3">
												<c:choose>
													<c:when test="${jobGroup != null }">
														<input class="form-control" type="text" id="jobGroup"
															name="jobGroup" value="${jobGroup}">
													</c:when>
													<c:otherwise>
														<select onchange="javascript:cv();" id="jobGroup"
															name="jobGroup" class="form-control">
															<c:forEach items="${taskTypeList }" var="item">
																<option value="${item.value }" desc="${item.name }">${item.value }</option>
															</c:forEach>
														</select>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">任务类</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="jobClass"
													name="jobClass" value="${jobClass}">
											</div>
											<label class="col-md-2 control-label">任务方法</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="methodName"
													name="methodName" value="${methodName}">
											</div>

										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">任务运行时间表达式</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="cronExpression"
													name="cronExpression" value="${cronExpression}">
											</div>
											<label class="col-md-2 control-label">任务是否有状态（并发）</label>
											<div class="col-md-3">
												<select id="isConcurrent" name="isConcurrent"
													class="form-control">
													<option value='0'
														<c:if test="${isConcurrent == '0'}"> selected</c:if>>否</option>
													<option value='1'
														<c:if test="${isConcurrent == '1'}"> selected</c:if>>是</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">任务描述</label>
											<!-- 											<div class="col-md-3"> -->
											<!-- 												<select id="description" name="description" -->
											<!-- 													class="form-control"> -->
											<%-- 													<c:forEach items="${taskTypeList }" var="item"> --%>
											<%-- 														<option value="${item.name }">${item.name }</option> --%>
											<%-- 													</c:forEach> --%>
											<!-- 												</select> -->
											<!-- 											</div> -->
											<div class="col-md-3">
												<c:choose>
													<c:when test="${description != null }">
														<input class="form-control" type="text" id="description"
															name="description" value="${description}">
													</c:when>
													<c:otherwise>
														<input class="form-control" type="text" id="description"
															name="description" value="${taskTypeList[0].name}">
													</c:otherwise>
												</c:choose>
											</div>
											<label class="col-md-2 control-label">启动时间</label>
											<div class="col-md-3">
												<input type="text" id="startDt" name="startDt"
													class="form-control form_date" value="${startDt}"
													data-date-format="yyyy-mm-dd">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">指针URL</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="indexUrl"
													name="indexUrl" value="${indexUrl}"> <input
													type="hidden" id="status" name="status" value="NORMAL">
											</div>
											<label class="col-md-2 control-label">线程数</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="threadNum"
													name="threadNum" value="${threadNum}"
													placeholder="任务启动的线程数最少为1,目前只支持单线程">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">断点续传request</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="memo"
													name="memo" value="${memo}">
											</div>
										</div>
										<div class="form-group">
											<div class="col-md-3">
												<button type="submit" class="btn btn-default">提交</button>
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
	function cv() {
		var jobGroupDesc = $('#jobGroup option:selected').attr('desc');
		$('#description').attr("value", jobGroupDesc);
		$('#description').html(jobGroupDesc);
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