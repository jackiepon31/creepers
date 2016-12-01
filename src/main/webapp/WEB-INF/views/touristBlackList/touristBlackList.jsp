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
				<i class="icon-table"></i> 导游旅行社黑名单信息管理
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
								<div class="pull-left">导游旅行社黑名单信息爬取进度列表</div>
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
										action="${ctx}/touristBlackList/list" method="post">
										<div class="form-group">
											<div class="col-md-12"></div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">执业证号</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="guideNo"
													name="guideNo" value="${guideNo}">
											</div>
											<label class="col-md-2 control-label">导游名称或企业法人</label>
											<div class="col-md-3">
												<input class="form-control" type="text" id="name"
													name="name" value="${name}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">黑名单类型</label>
											<div class="col-md-3">
												<select id="type" name="type" class="form-control">
													<c:forEach items="${typeList}" var="type"
														varStatus="index">
														<option value="${type.value}"
															<c:if test="${type.value == type}"> selected</c:if>>
															${type.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<div class="col-md-3">
												<button type="submit" class="btn btn-default">查询</button>
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
			<div class="container">

				<!-- Table -->
				<div class="row">
					<div class="col-md-12">
						<div class="widget">
							<div class="widget-head">
								<div class="pull-left">导游旅行社黑名单信息爬取进度列表</div>
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
												<th>导游名称或企业法人</th>
												<th>单位名称</th>
												<th>档案号</th>
												<th>执业证号</th>
												<th>违法事项</th>
												<th>行政处罚决定</th>
												<th>起止日期</th>
												<th>明细链接</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach items="${resultList.content}" var="result"
												varStatus="index">
												<tr>
													<td>${index.count}</td>
													<td>${result.name}</td>
													<td>${result.agentName}</td>
													<td>${result.docNo}</td>
													<td>${result.guideNo}</td>
													<td>${result.issue}</td>
													<td>${result.decision}</td>
													<td>${result.period}</td>
													<td><a href="${result.detailUrl}"  target="_blank">明细链接</a> </td>
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