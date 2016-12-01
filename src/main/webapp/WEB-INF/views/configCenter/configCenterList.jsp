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
				<i class="icon-table"></i> 爬虫配置中心
			</h2>

			<!-- Breadcrumb -->
			<div class="bread-crumb pull-right">
				<a href="${ctx}"><i class="icon-home"></i> Home</a> <span
					class="divider">/</span> <a href="${ctx}" class="bread-current">Dashboard</a>
			</div>

			<div class="clearfix"></div>

		</div>
		<!-- Page heading ends -->
		<div id="addItem"></div>
		<!-- Matter -->
		<div class="matter">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="widget wgreen">
							<div class="widget-head">
								<div class="pull-left">爬虫配置查询</div>
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
											<div class="col-md-3">
												<button type="button" onclick="javascript:doShowList()"
													class="btn btn-default">查询</button>
												<button type="reset" class="btn btn-warning">重置</button>
												<button type="button" onclick="javascript:doRefresh()"
													class="btn btn-default">刷新全部配置缓存</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="widget-foot"></div>
					</div>
				</div>


			</div>
			<!-- Matter ends -->
		</div>
		<!-- Mainbar ends -->
		<div class="container">

			<!-- Table -->
			<div class="row">
				<div class="col-md-12">
					<div class="widget">
						<div class="widget-head">
							<div class="pull-left">爬虫配置列表</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
								<a href="#" class="wclose"><i class="icon-remove"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<c:if test="${not empty resultList}">
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>编号</th>
											<th>爬虫任务类型</th>
											<th>开关状态</th>
											<th>创建者</th>
											<th>创建日期</th>
											<th>修改者</th>
											<th>修改日期</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${resultList.content}" var="result"
											varStatus="index">
											<tr>
												<td>${index.count}</td>
												<td>${result.requestType}</td>
												<td>${result.switchFlag}</td>
												<td>${result.createdBy}</td>
												<td><fmt:formatDate value="${result.createdDt}"
														pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td>${result.updatedBy}</td>
												<td><fmt:formatDate value="${result.updatedDt}"
														pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td>
													<button class="btn btn-xs btn-success" title="详情"
														onclick="javascript:doShowDetail('${result.requestType}')">
														<i class="icon-list"></i>
													</button>
<!-- 													<button class="btn btn-xs btn-success" title="修改" -->
<!-- 														onclick="javascript:entryModifyDetail()"> -->
<!-- 														<i class="icon-pencil"></i> -->
<!-- 													</button> -->
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="widget-foot">
									<!-- paginationSize:前台页面最多能显示的页数 -->
									<tags:pagination page="${resultList}" paginationSize="5" />
									<div class="clearfix"></div>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>

			<div class="clearfix"></div>
		</div>
		<!-- Content ends -->


	</div>
</div>
</div>

<script type="text/javascript">
	function doShowDetail(requestType) {
		$.ajax({
			cache : false,
			type : 'GET',
			url : '${ctx}/configCenter/showDetail',
			data : {
				requestType : requestType
			},
			success : function(data) {
				$("body").html(data);
			},
			error : function(data) {
			}
		});
	}
	function doShowList() {
		$.ajax({
			cache : false,
			type : 'GET',
			url : '${ctx}/configCenter/queryList',
			data : $("#queryForm").serializeArray(),
			success : function(data) {
				$("body").html(data);
			},
			error : function(data) {
			}
		});
	}
	function doRefresh() {
		$.ajax({
			cache : true,
			type : 'GET',
			url : '${ctx}/configCenter/doRefresh',
			success : function(data) {
				alert(data.result);
			},
			error : function(data) {
			}
		});
	}
</script>